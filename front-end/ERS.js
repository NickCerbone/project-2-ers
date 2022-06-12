function getEmployee(username, password) {
    let employeeLogin = {
        empUserName: username,
        empHashedPassword: password
    }

    fetch("http://localhost:7474/employees",
        {
            method: 'post',
            body: JSON.stringify(employeeLogin)
        })
        .then(response => response.json())
        .then(responseJson => {

            if (responseJson.empUserName == username && responseJson.rolesPojo.role == "employee") {
                sessionStorage.setItem("currEmpId", responseJson.empId)
                window.location.replace("EmployeeHomePage.html")

            } else if (responseJson.empUserName == username && responseJson.rolesPojo.role == "manager") {
                sessionStorage.setItem("currManId", responseJson.empId)
                window.location.replace("ManagerHomePage.html")
            }
        })
        .catch(
            (error => document.getElementById("invalid login message").innerHTML = `invalid login credentials`)
        )
}

function empLogOut() {
    sessionStorage.setItem("currEmpId", null)
    window.location.replace("LoginPage.html")
}

function manLogout() {
    sessionStorage.setItem("currManId", null)
    window.location.replace("LoginPage.html")
}

function getAllRequests() {

    console.log("data printed on console");

    fetch("http://localhost:7474/reimbursement")
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allRequestData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>Requested by: First Name</th>
                                <th>Requested by: Last Name</th>
                                <th>Reimbursement ID</th>
                                <th>Requested Reimbursement Amount</th>
                                <th>Reimbursement Status</th>
                                <th>Aprroved by: First Name</th>
                                <th>Approved by: Last Name</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let request of responseJson) {
                allRequestData += `<tr>
                                    <td>${request.requester.empId}</td>
                                    <td>${request.requester.empFirstName}</td>
                                    <td>${request.requester.empLastName}</td>
                                    <td>${request.reimbId}</td>
                                    <td>${request.reimbAmt}</td>
                                    <td>${request.statusPojo.status}</td>
                                    <td>${request.approver.empFirstName}</td>
                                    <td>${request.approver.empLastName}</td>
                                  </tr>`;
            }
            allRequestData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allRequestData;
        })


        .catch(error => console.log(error));

}

function displaySubmitRequestForm() {
    let requestForm = `<div class="container">
                        <form>
                            <div class="mb-3 mt-3">
                                <label for="reqAmt" class="form-label">Request Amount:</label>
                                <input type="text" class="form-control" id="reqAmt" placeholder="enter request amount" name="requestAmount">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="submitRequest()">submit request</button>
                        </form>
                        </div>
                        `;
    document.getElementById("content").innerHTML = requestForm;
}

function submitRequest() {

    console.log(sessionStorage.getItem("currEmpId"))
    let newRequest = {
        reimbAmt: document.getElementById("reqAmt").value,
        statusPojo: {
            statusId: 1,
        },
        requester: {
            empId: sessionStorage.getItem("currEmpId")
        }
    }

    fetch("http://localhost:7474/reimbursement", {
        method: 'post',
        body: JSON.stringify(newRequest)
    })
        .then(response => displayPendingByEmpId())
}

function displayAllRequestsByEmployee(empId) {
    console.log(empId);
    fetch("http://localhost:7474/reimbursement/" + empId)
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allRequestsByEmployeeData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Requested by: First Name</th>
                                <th>Requested by: Last Name</th>
                                <th>Reimbursement ID</th>
                                <th>Requested Reimbursement Amount</th>
                                <th>Reimbursement Status</th>
                                <th>Aprroved by: First Name</th>
                                <th>Aprroved by: Last Name</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let requestsByEmployeeData of responseJson) {
                allRequestsByEmployeeData += `<tr>
                                    <td>${requestsByEmployeeData.requester.empFirstName}</td>
                                    <td>${requestsByEmployeeData.requester.empLastName}</td>
                                    <td>${requestsByEmployeeData.reimbId}</td>
                                    <td>${requestsByEmployeeData.reimbAmt}</td>
                                    <td>${requestsByEmployeeData.statusPojo.status}</td>
                                    <td>${requestsByEmployeeData.approver.empFirstName}</td>
                                    <td>${requestsByEmployeeData.approver.empLastName}</td>
                                  </tr>`;
            }
            allRequestsByEmployeeData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allRequestsByEmployeeData;
        })
        .catch(error => console.log(error));
}

function approveRequest(reimbId) {
    let newApproveRequest = {
        reimbId: reimbId,
        statusPojo: {
            statusId: 2,
        },
        approver: {
            empId: sessionStorage.getItem("currManId")
        }
    }

    fetch("http://localhost:7474/updateRequest", {
        method: 'put',
        body: JSON.stringify(newApproveRequest)
    })
        .then(response => displayAllPending())
}

function rejectRequest(reimbId) {
    let newRejectRequest = {
        reimbId: reimbId,
        statusPojo: {
            statusId: 3,
        },
        approver: {
            empId: sessionStorage.getItem("currManId")
        }
    }

    fetch("http://localhost:7474/updateRequest/" + reimbId, {
        method: 'put',
        body: JSON.stringify(newRejectRequest)
    })
        .then(response => displayAllPending())
}

function displayAccountInformation() {
    console.log(sessionStorage.getItem("currEmpId"));
    fetch("http://localhost:7474/employees/" + sessionStorage.getItem("currEmpId"))
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allAccountInformation = `<div class="container mt-3">
                                            <h2>My information</h2>
                                                <ul class="list-group">
                                                    <li class="list-group-item">Employee ID: ${responseJson.empId}</li>
                                                    <li class="list-group-item">First Name: ${responseJson.empFirstName}</li>
                                                    <li class="list-group-item">Last Name: ${responseJson.empLastName}</li>
                                                    <li class="list-group-item">Username: ${responseJson.empUserName}</li>
                                                    <li class="list-group-item">Password: ${responseJson.empHashedPassword}</li>
                                                    <li class="list-group-item">Role: ${responseJson.rolesPojo.role}</li>
                                                </ul>
                                        </div>`

            document.getElementById("content").innerHTML = allAccountInformation;
        })


        .catch(error => console.log(error));
}

function displayUpdateAccountForm() {
    let updateAccountForm = `<div class="container">
                        <form>
                            <div class="mb-3 mt-3">
                                <label for="fname" class="form-label">First Name:</label>
                                <input type="text" class="form-control" id="fname" placeholder="enter first name here" name="firstName">
                            </div>
                            <div class="mb-3 mt-3">
                                <label for="lname" class="form-label">Last Name:</label>
                                <input type="text" class="form-control" id="lname" placeholder="enter last name here" name="lastName">
                            </div>
                            <div class="mb-3 mt-3">
                                <label for="uname" class="form-label">Username:</label>
                                <input type="text" class="form-control" id="uname" placeholder="enter username here" name="username">
                            </div>
                            <div class="mb-3 mt-3">
                                <label for="pword" class="form-label">Password:</label>
                                <input type="text" class="form-control" id="pword" placeholder="enter password here" name="password">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="updateEmpInfo()">update</button>
                        </form>
                        </div>
                        `;
    document.getElementById("content").innerHTML = updateAccountForm;
}

function updateEmpInfo() {

    let newEmpInfo = {
        empId: 0,
        empFirstName: document.getElementById("fname").value,
        empLastName: document.getElementById("lname").value,
        empUserName: document.getElementById("uname").value,
        empHashedPassword: document.getElementById("pword").value,
    }



    fetch("http://localhost:7474/reimbursement/" + sessionStorage.getItem("currEmpId"), {
        method: 'put',
        body: JSON.stringify(newEmpInfo)
    })
        .then(response => empLogOut())
}

function displayPendingByEmpId() {

    fetch("http://localhost:7474/pending/" + sessionStorage.getItem("currEmpId"))
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allPendingData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>Requested by: First Name</th>
                                <th>Requested by: Last Name</th>
                                <th>Reimbursement ID</th>
                                <th>Requested Reimbursement Amount</th>
                                <th>Reimbursement Status</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let pending of responseJson) {
                allPendingData += `<tr>
                                    <td>${pending.requester.empId}</td>
                                    <td>${pending.requester.empFirstName}</td>
                                    <td>${pending.requester.empLastName}</td>
                                    <td>${pending.reimbId}</td>
                                    <td>${pending.reimbAmt}</td>
                                    <td>${pending.statusPojo.status}</td>
                                  </tr>`;
            }
            allPendingData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allPendingData;
        })


        .catch(error => console.log(error));

}

function displayResolvedByEmpId() {
    fetch("http://localhost:7474/resolved/" + sessionStorage.getItem("currEmpId"))
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allResolvedData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>Requested by: First Name</th>
                                <th>Requested by: Last Name</th>
                                <th>Reimbursement ID</th>
                                <th>Requested Reimbursement Amount</th>
                                <th>Reimbursement Status</th>
                                <th>Aprroved by: First Name</th>
                                <th>Approved by: Last Name</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let resolved of responseJson) {
                allResolvedData += `<tr>
                                    <td>${resolved.requester.empId}</td>
                                    <td>${resolved.requester.empFirstName}</td>
                                    <td>${resolved.requester.empLastName}</td>
                                    <td>${resolved.reimbId}</td>
                                    <td>${resolved.reimbAmt}</td>
                                    <td>${resolved.statusPojo.status}</td>
                                    <td>${resolved.approver.empFirstName}</td>
                                    <td>${resolved.approver.empLastName}</td>
                                  </tr>`;
            }
            allResolvedData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allResolvedData;
        })


        .catch(error => console.log(error));

}

function displayAllEmployees() {
    fetch("http://localhost:7474/allEmployees/")
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allEmployeeData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Role</th>
                                <th>View Requests By This Employee</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let employee of responseJson) {
                allEmployeeData += `<tr>
                                    <td>${employee.empId}</td>
                                    <td>${employee.empFirstName}</td>
                                    <td>${employee.empLastName}</td>
                                    <td>${employee.rolesPojo.role}</td>
                                    <td><button 
                                            type="button" 
                                            class="btn btn-info"
                                            onclick="displayAllRequestsByEmployee(${employee.empId})">View</button></td>
                                  </tr>`;
            }
            allEmployeeData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allEmployeeData;
        })


        .catch(error => console.log(error));
}

function displayAllPending() {
    console.log("data printed on console");

    fetch("http://localhost:7474/allPending")
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allPendingData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>Requested by: First Name</th>
                                <th>Requested by: Last Name</th>
                                <th>Reimbursement ID</th>
                                <th>Requested Reimbursement Amount</th>
                                <th>Reimbursement Status</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let allPending of responseJson) {
                allPendingData += `<tr>
                                    <td>${allPending.requester.empId}</td>
                                    <td>${allPending.requester.empFirstName}</td>
                                    <td>${allPending.requester.empLastName}</td>
                                    <td>${allPending.reimbId}</td>
                                    <td>${allPending.reimbAmt}</td>
                                    <td>${allPending.statusPojo.status}</td>
                                    <td><button 
                                            type="button" 
                                            class="btn btn-primary"
                                            onclick="approveRequest(${allPending.reimbId})">approve</button></td>
                                    <td><button 
                                            type="button" 
                                            class="btn btn-danger"
                                            onclick="rejectRequest(${allPending.reimbId})">reject</button></td>
                                  </tr>`;
            }
            allPendingData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allPendingData;
        })


        .catch(error => console.log(error));
}

function displayAllResolved() {
    console.log("data printed on console");

    fetch("http://localhost:7474/allResolved")
        .then(response => response.json())
        .then(responseJson => {
            console.log(responseJson)
            let allResolvedData = `<table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>Requested by: First Name</th>
                                <th>Requested by: Last Name</th>
                                <th>Reimbursement ID</th>
                                <th>Requested Reimbursement Amount</th>
                                <th>Reimbursement Status</th>
                                <th>Aprroved by: First Name</th>
                                <th>Approved by: Last Name</th>
                            </tr>
                            </thead>
                            <tbody>`;
            for (let allResolved of responseJson) {
                allResolvedData += `<tr>
                                    <td>${allResolved.requester.empId}</td>
                                    <td>${allResolved.requester.empFirstName}</td>
                                    <td>${allResolved.requester.empLastName}</td>
                                    <td>${allResolved.reimbId}</td>
                                    <td>${allResolved.reimbAmt}</td>
                                    <td>${allResolved.statusPojo.status}</td>
                                    <td>${allResolved.approver.empFirstName}</td>
                                    <td>${allResolved.approver.empLastName}</td>
                                  </tr>`;
            }
            allResolvedData += `</tbody></table>`;
            document.getElementById("content").innerHTML = allResolvedData;
        })


        .catch(error => console.log(error));

}

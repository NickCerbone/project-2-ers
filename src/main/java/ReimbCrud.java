import java.util.List;

import io.javalin.Javalin;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ReimbService;
import service.ReimbServiceImpl;

public class ReimbCrud {

	public static void main(String[] args) {

		ReimbService reimbService = new ReimbServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();

		Javalin server = Javalin.create((config) -> config.enableCorsForAllOrigins());
		server.start(7474);

		// endpoint view all requests - NICK
		server.get("/reimbursement", (ctx) -> {
			List<ReimbursementPojo> allRequests = reimbService.viewAllRequests();
			ctx.json(allRequests);
		});

		// endpoint submit a request - NICK
		server.post("/reimbursement", (ctx) -> {
			ReimbursementPojo newReimbursementPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			ReimbursementPojo returnReimbursementPojo = reimbService.submitRequest(newReimbursementPojo);
			ctx.json(returnReimbursementPojo);
		});

		// endpoint view all requests by specific requester - NICK
		server.get("/reimbursement/{requesterId}", (ctx) -> {
			List<ReimbursementPojo> allRequestsByRequester = reimbService
					.viewAllRequestsByRequester(Integer.parseInt(ctx.pathParam("requesterId")));
			ctx.json(allRequestsByRequester);
		});

		// endpoint view employee where username == username && password == password -
		// LOGAN
		server.post("/employees", (ctx) -> {
			EmployeePojo employeePojo = ctx.bodyAsClass(EmployeePojo.class);
			EmployeePojo returnEmployeePojo = employeeService.getEmployee(employeePojo);
			ctx.json(returnEmployeePojo);
		});

		// endpoint employee "view my account" NAVDEEP
		server.get("/employees/{empid}", (ctx) -> {
			EmployeePojo returnedEmployee = employeeService.empViewInfo(Integer.parseInt(ctx.pathParam("empid")));
			ctx.json(returnedEmployee);
		});

		// endpoint update accnt info - NAVDEEP
		server.put("/reimbursement/{empid}", (ctx) -> {
			int empIdInteger = Integer.parseInt(ctx.pathParam("empid"));
			EmployeePojo newEmployeePojo = ctx.bodyAsClass(EmployeePojo.class);
			EmployeePojo returnEmployeePojo = employeeService.empUpdateInfo(newEmployeePojo, empIdInteger);
			ctx.json(returnEmployeePojo);
		});

		// endpoint view all pending by empId - LOGAN
		server.get("/pending/{empid}", (ctx) -> {
			List<ReimbursementPojo> allPendingRequestsByRequester = reimbService
					.viewPendingRequestsByRequester(Integer.parseInt(ctx.pathParam("empid")));
			ctx.json(allPendingRequestsByRequester);
		});

		// endpoint view all resolved by empId - LOGAN
		server.get("/resolved/{empid}", (ctx) -> {
			List<ReimbursementPojo> allResolvedRequestsByRequester = reimbService
					.viewResolvedRequestsByRequester(Integer.parseInt(ctx.pathParam("empid")));
			ctx.json(allResolvedRequestsByRequester);
		});

		// endpoint view all employees - NAVDEEP
		server.get("/allEmployees", (ctx) -> {
			List<EmployeePojo> allEmployees = employeeService.manViewAll();
			ctx.json(allEmployees);
		});

		// endpoint view all pending from manager - KASSANDRA
		server.get("/allPending", (ctx) -> {
			List<ReimbursementPojo> allPending = reimbService.viewAllPendingRequests();
			ctx.json(allPending);
		});

		// endpoint view all resolved from manager - KASSANDRA
		server.get("/allResolved", (ctx) -> {
			List<ReimbursementPojo> allResolved = reimbService.viewAllResolvedRequests();
			ctx.json(allResolved);
		});

		// approver/deny request - KASSANDRA
		server.put("/updateRequest", (ctx) -> {
			ReimbursementPojo newReimbursementPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			ReimbursementPojo returnReimbursementPojo = reimbService.manUpdateRequest(newReimbursementPojo);
			ctx.json(returnReimbursementPojo);
		});
	
	}

}

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
		
		// endpoint view all requests
		server.get("/reimbursement", (ctx) -> {
			List<ReimbursementPojo> allRequests = reimbService.viewAllRequests();
			ctx.json(allRequests);
		});

		// endpoint submit a request
		server.post("/reimbursement", (ctx) -> {
			ReimbursementPojo newReimbursementPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			ReimbursementPojo returnReimbursementPojo = reimbService.submitRequest(newReimbursementPojo);
			ctx.json(returnReimbursementPojo);
		});
		
		// endpoint view all requests by specific requester
		server.get("/reimbursement/{requesterId}", (ctx) -> {
			List<ReimbursementPojo> allRequestsByRequester = reimbService.viewAllRequestsByRequester(Integer.parseInt(ctx.pathParam("requesterId")));
			ctx.json(allRequestsByRequester);
		});
		
		// endpoint view employee where username == username && password == password
		server.get("/employees/{empUserNme}/{empHashPswd}", (ctx) -> {
			String empUserName = ctx.pathParam("empUserNme");
			String empHashedPassword = ctx.pathParam("empHashPswd");
			//int bookIdInteger = Integer.parseInt(bookId);
			ctx.json(employeeService.getEmployee(empUserName, empHashedPassword));
		});
		
		// endpoint employee "view my account"
		server.get("/employees/{empid}/", (ctx) -> {
			EmployeePojo returnedEmployee = employeeService.empViewInfo(Integer.parseInt(ctx.pathParam("empid")));
			ctx.json(returnedEmployee);
		});
		
		// endpoint update accnt info
		server.put("/reimbursement/{empid}", (ctx) -> {
			int empIdInteger = Integer.parseInt(ctx.pathParam("empid"));
			EmployeePojo newEmployeePojo = ctx.bodyAsClass(EmployeePojo.class);
			EmployeePojo returnEmployeePojo = employeeService.empUpdateInfo(newEmployeePojo, empIdInteger);
			ctx.json(returnEmployeePojo);
		});
		
		// endpoint view all pending by empId
		server.get("/pending/{empid}", (ctx) -> {
			List<ReimbursementPojo> allPendingRequestsByRequester = reimbService.viewPendingRequestsByRequester(Integer.parseInt(ctx.pathParam("empid")));
			ctx.json(allPendingRequestsByRequester);
		});
		
		// endpoint view all pending by empId
		server.get("/resolved/{empid}", (ctx) -> {
			List<ReimbursementPojo> allResolvedRequestsByRequester = reimbService.viewResolvedRequestsByRequester(Integer.parseInt(ctx.pathParam("empid")));
			ctx.json(allResolvedRequestsByRequester);
		});
		
		// endpoint view all requests
		server.get("/allEmployees", (ctx) -> {
			List<EmployeePojo> allEmployees = employeeService.manViewAll();
			ctx.json(allEmployees);
		});
		
		// endpoint view all pending from manager
		server.get("/allPending", (ctx) -> {
			List<ReimbursementPojo> allPending = reimbService.viewAllPendingRequests();
			ctx.json(allPending);
		});
		
		// endpoint view all resolved from manager
		server.get("/allResolved", (ctx) -> {
			List<ReimbursementPojo> allResolved = reimbService.viewAllResolvedRequests();
			ctx.json(allResolved);
		});
		
		server.put("/updateRequest/{reimbid}", (ctx) -> {
			int reimbIdInteger = Integer.parseInt(ctx.pathParam("reimbid"));
			ReimbursementPojo newReimbursementPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			ReimbursementPojo returnReimbursementPojo = reimbService.manUpdateRequest(newReimbursementPojo, reimbIdInteger);
			ctx.json(returnReimbursementPojo);
		});
	}

}

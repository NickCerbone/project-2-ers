
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.javalin.Javalin;
import service.EmployeeService;
import model.EmployeePojo;
import model.ReimbursementPojo;
import model.RolesPojo;
import exception.ApplicationException;
import io.javalin.Javalin;
import model.EmployeePojo;

import service.EmployeeServiceImpl;
import service.ReimbursementService;
import service.ReimbursementServiceImpl;

public class ExpenseCrud {
	public static void main(String[] args) {
		final Logger LOG = LogManager.getLogger(ExpenseCrud.class);

		EmployeeService employeeService = new EmployeeServiceImpl();
		ReimbursementService reimbursementService = new ReimbursementServiceImpl();

		//create service object to call
		EmployeeServiceImpl service = new EmployeeServiceImpl(); 
		Javalin app = Javalin.create((config) -> config.enableCorsForAllOrigins());
		LOG.info("Starting server on port 8082");
		app.start(8082);
       
        //can access this endpoint through postman
        //lets create other endpoints
        //CRUD 
        // login
        app.post("/login/{username}/{password}", (ctx)->{
        	LOG.info("starting post rout /login/{username}/{password}");
        	System.out.println("post rout");
        	String username = ctx.pathParam("username");
        	String password = ctx.pathParam("password");
        	try {
        		
        		EmployeePojo info =service.login(username, password);
        		if (info == null) {
        			throw new ApplicationException("invalid username or password");
        		}
        		LOG.info("returning from /login/{username}/{password}");
            	ctx.json(info);
        	} catch(ApplicationException e) {
        		LOG.info("returning from /login/{username}/{password} with error");
        		ctx.json(e);
        	}
        });

        app.post("/changePass/{newPassword}", (ctx)->{
        	LOG.info("Starting post route /changePass/{newPassword}");
        	System.out.println("post rout");	
        	String newPassword = ctx.pathParam("newPassword");
        	int newPass = Integer.parseInt(newPassword);
        	try {
        		
        		EmployeePojo info =service.changePassword(newPass);
        		if (info == null) {
        			LOG.info("returning from /changePass/{newPassword} with error");
        			throw new ApplicationException("invalid username or password");
        		}
        		LOG.info("returning from /changePass/{newPassword}");
            	ctx.json(info);
            	
        	} catch(ApplicationException e) {
        		LOG.info("returning from /changePass/{newPassword} with error");
        		ctx.json(e);
        	}
        	
        });
    
        //endpoint manager can view all employees    
        app.get("/emps",(ctx)->{
        	LOG.info("starting get route /employees");
        	System.out.println("All Employees details");
        	
        	List<EmployeePojo> allEmployees = employeeService.manViewAll(); 
        	LOG.info("returning from /employees");
        	ctx.json(allEmployees);
        });  
        
        //endpoint manager can view specific employee manViewRequest
        app.get("/empSpecific/{eid}",(ctx)->{
        	LOG.info("starting get route /emps");
        	System.out.println("Specific Employees details");
        	// here we retrieve the eId from the path/url
        	String empId = ctx.pathParam("eid");
        	// convert String to int
        	int empIdInteger = Integer.parseInt(empId);
        	ReimbursementPojo specificEmployee = reimbursementService.manViewRequest(empIdInteger); 
        	LOG.info("returning from /employees");
        	ctx.json(specificEmployee);
        });   
        
        //endpoint employee can view his details empViewInfo
        app.get("/empOwn/{eid}",(ctx)->{
        	LOG.info("starting get route /employees");
        	System.out.println("Employee own details");
        	// here we retrieve the eId from the path/url
        	String empId = ctx.pathParam("eid");
        				
        	// convert String to int
        	int empIdInteger = Integer.parseInt(empId);
        	EmployeePojo employeeInfo = employeeService.empViewInfo(empIdInteger); 
        	LOG.info("returning from /employees");
        	ctx.json(employeeInfo);
        });   
        

        //endpoint put employee can update his details
        app.put("/empUpdate/{currPass}",(ctx)->{
        	LOG.info("starting put route /employees");
        	System.out.println(ctx.bodyAsClass(EmployeePojo.class));
        	String empPass = ctx.pathParam("currPass");
        	EmployeePojo returnEmpPojo = employeeService.empUpdateInfo(ctx.bodyAsClass(EmployeePojo.class),empPass);
        	
        	
        	// convert String to int
        	
        	
        	LOG.info("returning from /employees");
        	ctx.json(returnEmpPojo);
        });  
        
	//endpoint manager can view all resolved requests ---start
	app.get("/empResolved",(ctx)->{
		LOG.info("starting get route /employees");	
		System.out.println("View all resolved requests");
        	List<ReimbursementPojo> allResolvedRequests = reimbursementService.manViewAllResolved(); 
        	LOG.info("returning from /employees");
        	ctx.json(allResolvedRequests);
        });   
		
	//endpoint manager can view all pending requests
	app.get("/empPendings",(ctx)->{
		LOG.info("starting get route /employees");	
        	System.out.println("View all pending requests");
        	List<ReimbursementPojo> allPendingRequests = reimbursementService.manViewAllPending(); 
        	LOG.info("returning from /employees");
        	ctx.json(allPendingRequests);
        });   	
		
	//endpoint employee can view all resolved requests
	app.get("/empAllRequests/{eid}",(ctx)->{
		LOG.info("starting get route /employees");	
        	System.out.println("View all resolved request");
        	String empId = ctx.pathParam("eid");
        	int empIdInteger = Integer.parseInt(empId);
        	ReimbursementPojo employeeResolved = reimbursementService.empViewResolved(empIdInteger); 
        	LOG.info("returning from /employees");
        	ctx.json(employeeResolved);
        });   	
	//gets employees information
	app.get("/empRoleId/{id}", (ctx)->{
		LOG.info("starting get route /empRoleId/{id}");	
		
		String roleEmpId = ctx.pathParam("id");
		RolesPojo rolesPojo = service.getRole(Integer.parseInt(roleEmpId));
		ctx.json(rolesPojo);
		LOG.info("returning get route /empRoleId/{id}");	
	});
	}
}

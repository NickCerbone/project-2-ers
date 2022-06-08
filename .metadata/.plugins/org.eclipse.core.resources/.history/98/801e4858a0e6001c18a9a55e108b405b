CREATE TABLE employees(
   emp_id serial,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   user_name VARCHAR(255) NOT NULL,
   hashed_password VARCHAR(255) NOT NULL,
   emp_role_id INT,
   PRIMARY KEY(emp_id)
);

CREATE TABLE roles(
role_id serial,
role VARCHAR(50) NOT NULL,
PRIMARY KEY(role_id)
);
CREATE TABLE status(
status_id serial,
status VARCHAR(255) NOT NULL,
PRIMARY KEY(status_id)
);
CREATE TABLE reimbursements(
  reimb_id serial,
  reimb_amt double precision,
reimb_status_id INT,
requester_id int,
approver_id int,
PRIMARY KEY(reimb_id),
      FOREIGN KEY(reimb_status_id) 
      REFERENCES status(status_id),
FOREIGN KEY(requester_id) 
      REFERENCES employees(emp_id),
FOREIGN KEY(approver_id) 
      REFERENCES employees(emp_id)
);



INSERT INTO roles(role) VALUES ('Manager');
INSERT INTO roles(role) VALUES ('Employee');
INSERT INTO status(status) VALUES ('Pending');
INSERT INTO employees(first_name, last_name, user_name, hashed_password, emp_role_id) VALUES ('logan', 'lastName', 'cat', '$2a$10$HwO.e2gax/jJuW49MfLbvujyUQu8Wr6yppRHXFkLp11./Hnaj74Nu', 1);

INSERT INTO reimbursements(reimb_amt, reimb_status_id, requester_id) values (200.00, 1, 2);


create view employee_table as select employees.emp_id, employees.first_name, employees.last_name, employees.user_name, employees.hashed_password, roles.role from employees inner join roles on employees.emp_role_id=roles.role_id;
create view view_all_requests as 
	select employees.emp_id, employees.first_name, employees.last_name, reimbursements.reimb_id, reimbursements.reimb_amt, reimbursements.reimb_status_id, status.status, reimbursements.approver_id 
		from employees 
			inner join reimbursements on reimbursements.requester_id=employees.emp_id 
			inner join status on reimbursements.reimb_status_id=status.status_id;

create view approver_info as select emp_id, first_name, last_name from employees where emp_role_id=2;
select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id;
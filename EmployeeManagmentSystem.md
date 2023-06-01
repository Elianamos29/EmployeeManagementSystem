### FUNCTIONAL REQUIRMENTS

#### Database managment

The system would allow users to add, edit, and delete employee records.
When a user enters a new employee record, the system would validate the data to ensure that all required fields are filled in and that the data is valid (e.g. name, contact information, job title, etc.).The data would then be stored in the database using JDBC.
When a user edits or deletes an existing employee record, the system would retrieve the record from the database, update or delete it, and then save the changes back to the database

#### Attendance Tracking

The system would allow employees to clock in and out, view their attendance history, and generate attendance reports
When an employee clocks in, the system would record the time and date in the database using JDBC. When the employee clocks out, the system would calculate the time worked and store it in the database.
The system would then allow users to view their attendance history and generate reports based on the data stored in the database.

#### Payroll Managment

The system would allow HR managers to manage employee salaries, tax withholdings, and deductions.
The system would use the attendance data stored in the database to calculate each employee's pay, taking into account any tax withholdings or deductions.
The system would use the attendance data stored in the database to calculate each employee's pay, taking into account any tax withholdings or deductions.

#### Leave managment

The system would allow employees to request time off and HR managers to manage employee leave requests.
When an employee requests time off, the system would validate the request and store it in the database using JDBC
HR managers would then be able to view and manage leave requests, approve or reject requests, and manage vacation/sick leave policies.

The system would allow HR managers to track employee performance and conduct performance evaluations.
The system would allow managers to set goals and objectives, track employee progress, and generate performance reports.
Performance data would be stored in the database using JDBC, allowing HR managers to easily track and manage employee performance.

### ROLES AND PERMISSIONS

The functionalities of an employee management system can be performed by different types of users depending on their roles and permissions.

#### Adminstrator

The administrator can perform all functionalities of the system, including adding, editing, and deleting employee records, tracking attendance, managing payroll, approving or rejecting leave requests, and tracking employee performance.

#### HR Manager

responsible for managing employee-related tasks such as hiring, onboarding, training, and performance management.
The HR manager can perform most of the functionalities of the system, including adding, editing, and deleting employee records, tracking attendance, managing payroll, approving or rejecting leave requests, and tracking employee performance.
However, some functionalities such as managing system settings and user accounts may be restricted to the administrator.

#### Employee

The employee can access the system to view their attendance history, submit leave requests, and view their pay stubs.
The employee can also update their personal information such as contact details and emergency contacts.
However, most of the functionalities such as managing employee records and payroll are restricted to the administrator and HR manager.

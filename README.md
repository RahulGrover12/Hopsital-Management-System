#CODE STRUCTURE

We have 5 Classes - 
Patient, 
Doctor, 
Appointments, 
Main Class, 
Database Connection

Patient class methods are:- 
add_patient(), 
view_patient(), 
check_patient()

Doctor class methods are:- 
view_doctors(), 
check_doctors()

Appointments class methods are:- 
view_appointments(), 
book_appointment(), 
check doctor Availability

Main class includes:- 
Driver code, 
Main Menu, 
Exit

#DATABASE SCHEMAS

WE have 3 Schemas - 
Patients, 
Doctors, 
Appointments

Patient me Attributes honge:- 
ID (Primary Key), 
Name, 
Age, 
Gender, 

Doctors me Attributes honge:- 
ID (Primary Key), 
Name, 
Specialization, 
Gender

Appointments me Attributes honge:- 
ID (Primary Key), 
Patient ID (Foreign Key), 
Doc ID (Foreign Key), 
App. Date

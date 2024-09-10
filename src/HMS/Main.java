// https://github.com/RahulGrover12/
// Rahul Grover
package HMS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DBConnection conn = new DBConnection();
        Patient patient = new Patient(conn.getConnection(), sc);
        Doctor doctor = new Doctor(conn.getConnection());
        Appointments appointment = new Appointments(conn.getConnection());
        while(true){
            System.out.println("HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("1. Add Patient");
            System.out.println("2. Book Appointment");
            System.out.println("3. View Doctors");
            System.out.println("4. View Patient");
            System.out.println("5. View Appointments");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    patient.add_patient();
                    System.out.println();
                    break;
                case 2:
                    appointment.book_appointment(patient, doctor, conn, sc);
                    System.out.println();
                    break;
                case 3:
                    doctor.view_doctors();
                    System.out.println();
                    break;
                case 4:
                    patient.view_patients();
                    System.out.println();
                    break;
                case 5:
                    appointment.view_appointments();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                    return;
                default:
                    System.out.println("Please Enter a valid choice!!");
                    break;

            }
        }
    }
}

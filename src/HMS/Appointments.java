// https://github.com/RahulGrover12/
// Rahul Grover
package HMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Appointments {

    private final Connection connection;
    public Appointments(Connection connection){
        this.connection = connection;
    }

    public void view_appointments(){
        String query = "select * from appointments";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement((query));
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Booked Appointments: ");
            System.out.println("+------------+------------------+----------------------+------------------+");
            System.out.println("| ID         | Patient ID       | Doctor ID            | Appointment Date |");
            System.out.println("+------------+------------------+----------------------+------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int p_id = resultSet.getInt("patient_id");
                int d_id = resultSet.getInt("doctor_id");
                String date = resultSet.getString("appointment_date");
                System.out.printf("| %-10s | %-16s | %-20s | %-16s |\n", id , p_id , d_id, date);
                System.out.println("+------------+------------------+----------------------+------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void book_appointment(Patient patient, Doctor doctor, DBConnection conn , Scanner sc){
        System.out.print("Enter Patient ID: ");
        int patient_id = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctor_id = sc.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointment_date = sc.next();

        if(patient.check_patient(patient_id) && doctor.check_doctor(doctor_id)){
            if(checkAvailability(doctor_id, appointment_date, conn)){
                String query = "Insert into appointments(patient_id, doctor_id, appointment_date) values (?, ?, ?)";
                try{
                    PreparedStatement preparedStatement = conn.getConnection().prepareStatement(query);
                    preparedStatement.setInt(1,patient_id);
                    preparedStatement.setInt(2,doctor_id);
                    preparedStatement.setString(3,appointment_date);
                    int affectedRows = preparedStatement.executeUpdate();
                    if(affectedRows>0){
                        System.out.println("Appointment Booked!!");
                    }else{
                        System.out.println("Failed to book appointment");
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor no Available on this date, book another appointment!!!");
            }
        }else{
            System.out.println("Either Doctor or Patient doesn't exist!!!");
        }
    }

    public  boolean checkAvailability(int doctor_id, String appointment_date, DBConnection connection){
        String query = "Select Count(*) from appointments where doctor_id= ? and appointment_date= ?";

        try{
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,doctor_id);
            preparedStatement.setString(2,appointment_date);
            ResultSet  resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int count = resultSet.getInt(1);
                return count == 0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

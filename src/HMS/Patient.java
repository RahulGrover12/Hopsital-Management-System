// https://github.com/RahulGrover12/
// Rahul Grover
package HMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private final Connection connection;
    private final Scanner sc;

    public Patient(Connection connection, Scanner sc){
        this.connection = connection;
        this.sc = sc;
    }

    public void add_patient(){
        System.out.print("Enter Patient Name: ");
        String name = sc.next();
        System.out.print("Enter Patient Age: ");
        int age = sc.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender = sc.next();

        try{
            String query = "Insert Into patients(name, age, gender) values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows>0){
                System.out.println("Patient Registered Successfully!");
            }else{
                System.out.println("Failed to register Patient!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void view_patients(){
        String query = "select * from patients";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement((query));
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Patients: ");
            System.out.println("+------------+------------------+-----------+-----------+");
            System.out.println("| Patient ID | Name             | Age       | Gender    |");
            System.out.println("+------------+------------------+-----------+-----------+");
            while(resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               int age = resultSet.getInt("age");
               String gender = resultSet.getString("gender");

                System.out.printf("| %-10s | %-16s | %-9s | %-9s |\n", id , name, age, gender);
                System.out.println("+------------+------------------+-----------+-----------+");
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean check_patient(int id){
        String query = "select * from patients where id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
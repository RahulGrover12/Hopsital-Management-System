// https://github.com/RahulGrover12/
// Rahul Grover
package HMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private final Connection connection;

    public Doctor(Connection connection){
        this.connection = connection;
    }

    public void view_doctors(){
        String query = "select * from doctors";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement((query));
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Doctors: ");
            System.out.println("+------------+------------------+----------------------+-----------+");
            System.out.println("| Doctor ID | Name              | Specialization       | Gender    |");
            System.out.println("+------------+------------------+----------------------+-----------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-9s | %-17s | %-20s | %-9s |\n", id , name , specialization, gender);
                System.out.println("+------------+------------------+----------------------+-----------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean check_doctor(int id){
        String query = "select * from doctors where id = ?";

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

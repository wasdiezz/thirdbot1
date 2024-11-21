
package org.example.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegistrationsRepository {
    private Connection connection = null;

    public RegistrationsRepository() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addNew(Registration registration) throws SQLException {
        Statement statement = connection.createStatement();

        String insertQuery = String.format("INSERT INTO registrations (team_name, number_of_participants, selected_task) VALUES ('%s',%d,%d);", registration.getTeamName(), registration.getNumberOfParticipants(), registration.getSelectedTask());

        statement.executeUpdate(insertQuery);

        statement.close();
    }

    public ArrayList<Registration> getAllRegistrations() {
        ArrayList<Registration> registrations = new ArrayList<>();


        try {
            Statement statement = connection.createStatement();

            String selectQuery = String.format("SELECT * FROM registrations ORDER BY id ASC");

            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String teamName = resultSet.getString("team_name");
                int numberOfParticipants = resultSet.getInt("number_of_participants");
                int selectedTask = resultSet.getInt("selected_task");

                registrations.add(new Registration(id, teamName, numberOfParticipants, selectedTask));
            }

            resultSet.close();

            statement.close();
        } catch (Exception e) {
            System.out.println("Ошибка запроса к базе данных");
        }

        return registrations;
    }


}

package gestiunebug.repository.bugs;

import gestiunebug.model.Bug;
import gestiunebug.model.StatusBug;
import gestiunebug.model.User;
import gestiunebug.repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BugRepository implements IBugRepository{
    private JdbcUtils dbUtils;

    public BugRepository(Properties properties){
        dbUtils = new JdbcUtils(properties);
    }
    @Override
    public Bug getOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Bug> getAll() {
        return null;
    }

    @Override
    public Bug add(Bug bug) {
        return null;
    }

    @Override
    public Bug delete(Integer integer) {
        return null;
    }

    @Override
    public Bug update(Bug bug) {
        return null;
    }


    @Override
    public Iterable<Bug> findAllActiveBugs() {
        Connection connection = dbUtils.getConnection();
        List<Bug> bugs = new ArrayList<Bug>();
        String sql = "select * from bugs where status = 'pending'";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String denumire = resultSet.getString("denumire");
                String descriere = resultSet.getString("descriere");
                String verificatorUsername = resultSet.getString("verificator");
                Bug bug = new Bug(denumire, descriere, findUserByUsername(verificatorUsername));
                bug.setCodBug(id);
                bug.setStatus(StatusBug.PENDING);
                bugs.add(bug);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return bugs;
    }

    @Override
    public Iterable<Bug> findAllBugsByVerificator(String verificator) {

        Connection connection = dbUtils.getConnection();
        List<Bug> bugs = new ArrayList<Bug>();
        String sql = "select * from bugs where verificator = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, verificator);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String denumire = resultSet.getString("denumire");
                String descriere = resultSet.getString("descriere");
                String status = resultSet.getString("status");
                Bug bug = new Bug(denumire, descriere, findUserByUsername(verificator));
                bug.setCodBug(id);
                if(status.equals("pending"))
                    bug.setStatus(StatusBug.PENDING);
                else
                    bug.setStatus(StatusBug.REJECTED);
                bugs.add(bug);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return bugs;
    }

    @Override
    public User findUserByUsername(String username) {
        Connection connection = dbUtils.getConnection();
        String sql = "select * from users where username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String password = resultSet.getString("password");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            User user = new User(username, password);
            user.setFirstName(firstName);user.setLastName(lastName);user.setRole(role);
            return user;
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}

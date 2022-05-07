package gestiunebug.repository.user;

import gestiunebug.model.User;
import gestiunebug.repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository implements IUserRepository{
    private JdbcUtils dbUtils;

    public UserRepository(Properties properties){
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public User getOne(String s) {
        Connection connection = dbUtils.getConnection();
        System.out.println(connection);
        String sql = "select * from users where username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String password = resultSet.getString("password");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            User user = new User(s, password);
            user.setFirstName(firstName);user.setLastName(lastName);user.setRole(role);
            return user;
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Iterable<User> getAll() {
        return null;
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User delete(String s) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}

package dao;

import model.User;
import utils.PasswordUtils;

import java.sql.*;

public class UserDAO {


    public boolean register(User user) throws SQLException {
        String sql = "INSERT INTO users(username, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());

            stmt.setString(3, PasswordUtils.hashPassword(user.getPassword()));
            stmt.setString(4, user.getRole());
            return stmt.executeUpdate() > 0;
        }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                String inputHash = PasswordUtils.hashPassword(password);

                System.out.println("Stored Hash: " + storedHash);
                System.out.println("Input Hash: " + inputHash);

                if (storedHash.equals(inputHash)) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            storedHash,
                            rs.getString("role")
                    );
                } else {
                    System.out.println("Password mismatch");
                }
            } else {
                System.out.println("No user found with username: " + username);
            }
        } catch (SQLException e) {
            System.out.println(" SQL error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(" Other error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}

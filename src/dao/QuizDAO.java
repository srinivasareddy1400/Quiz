package dao;

import model.Quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {


    public boolean addQuiz(String title) throws SQLException {
        String sql = "INSERT INTO quizzes(title) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            return stmt.executeUpdate() > 0;
        }
    }


    public List<Quiz> getAllQuizzes() throws SQLException {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Quiz(rs.getInt("id"), rs.getString("title")));
            }
        }
        return list;
    }


    public boolean updateQuizTitle(int quizId, String newTitle) throws SQLException {
        String sql = "UPDATE quizzes SET title = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newTitle);
            stmt.setInt(2, quizId);
            return stmt.executeUpdate() > 0;
        }
    }


    public boolean deleteQuiz(int quizId) throws SQLException {
        String sql = "DELETE FROM quizzes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            return stmt.executeUpdate() > 0;
        }
    }
}

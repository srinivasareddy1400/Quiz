package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public boolean saveResult(int userId, int quizId, int score, int totalQuestions) throws SQLException {
        String sql = "INSERT INTO results (user_id, quiz_id, score, total_questions) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.setInt(4, totalQuestions);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<String> getUserResults(int userId) throws SQLException {
        List<String> results = new ArrayList<>();
        String sql = """
            SELECT q.title, r.score, r.total_questions, r.attempt_time
            FROM results r
            JOIN quizzes q ON r.quiz_id = q.id
            WHERE r.user_id = ?
            ORDER BY r.attempt_time DESC
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String line = String.format(
                        " %s | Score: %d/%d | %s",
                        rs.getString("title"),
                        rs.getInt("score"),
                        rs.getInt("total_questions"),
                        rs.getTimestamp("attempt_time").toLocalDateTime().toString()
                );
                results.add(line);
            }
        }
        return results;
    }

    public List<String> getLeaderboard() throws SQLException {
        List<String> leaderboard = new ArrayList<>();
        String sql = """
        SELECT u.username, SUM(r.score) AS total_score
        FROM results r
        JOIN users u ON r.user_id = u.id
        GROUP BY u.username
        ORDER BY total_score DESC
        LIMIT 10
    """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int rank = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                int score = rs.getInt("total_score");
                leaderboard.add(rank + ". " + username + " - " + score + " pts");
                rank++;
            }
        }
        return leaderboard;
    }

}

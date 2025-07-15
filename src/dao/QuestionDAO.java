package dao;

import model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {


    public boolean addQuestion(Question q) throws SQLException {
        String sql = """
            INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, q.getQuizId());
            stmt.setString(2, q.getQuestionText());
            stmt.setString(3, q.getOptionA());
            stmt.setString(4, q.getOptionB());
            stmt.setString(5, q.getOptionC());
            stmt.setString(6, q.getOptionD());
            stmt.setString(7, q.getCorrectOption());
            return stmt.executeUpdate() > 0;
        }
    }


    public List<Question> getQuestionsByQuizId(int quizId) throws SQLException {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE quiz_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuizId(rs.getInt("quiz_id"));
                q.setQuestionText(rs.getString("question_text"));
                q.setOptionA(rs.getString("option_a"));
                q.setOptionB(rs.getString("option_b"));
                q.setOptionC(rs.getString("option_c"));
                q.setOptionD(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_option"));
                list.add(q);
            }
        }

        return list;
    }
}

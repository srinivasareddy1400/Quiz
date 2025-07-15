package ui;

import dao.QuestionDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Question;
import model.Quiz;

public class AddQuestionUI {

    private final Quiz quiz;

    public AddQuestionUI(Quiz quiz) {
        this.quiz = quiz;
    }

    public void show(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label titleLabel = new Label("Quiz: " + quiz.getTitle());
        Label qLabel = new Label("Question:");
        TextField qField = new TextField();

        Label aLabel = new Label("Option A:");
        TextField aField = new TextField();
        Label bLabel = new Label("Option B:");
        TextField bField = new TextField();
        Label cLabel = new Label("Option C:");
        TextField cField = new TextField();
        Label dLabel = new Label("Option D:");
        TextField dField = new TextField();

        Label correctLabel = new Label("Correct Option (A/B/C/D):");
        TextField correctField = new TextField();

        Button saveBtn = new Button("Save Question");
        Label statusLabel = new Label();

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(qLabel, 0, 1); grid.add(qField, 1, 1);
        grid.add(aLabel, 0, 2); grid.add(aField, 1, 2);
        grid.add(bLabel, 0, 3); grid.add(bField, 1, 3);
        grid.add(cLabel, 0, 4); grid.add(cField, 1, 4);
        grid.add(dLabel, 0, 5); grid.add(dField, 1, 5);
        grid.add(correctLabel, 0, 6); grid.add(correctField, 1, 6);
        grid.add(saveBtn, 1, 7);
        grid.add(statusLabel, 1, 8);

        saveBtn.setOnAction(e -> {
            String questionText = qField.getText();
            String a = aField.getText();
            String b = bField.getText();
            String c = cField.getText();
            String d = dField.getText();
            String correct = correctField.getText().toUpperCase();

            if (questionText.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || correct.isEmpty()) {
                statusLabel.setText(" Fill all fields.");
                return;
            }

            if (!correct.matches("[ABCD]")) {
                statusLabel.setText("❌ Correct Option must be A, B, C, or D.");
                return;
            }

            Question q = new Question(quiz.getId(), questionText, a, b, c, d, correct);
            try {
                new QuestionDAO().addQuestion(q);
                statusLabel.setText("✅ Question added!");
                qField.clear(); aField.clear(); bField.clear(); cField.clear(); dField.clear(); correctField.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
                statusLabel.setText("❌ Failed to add question.");
            }
        });

        stage.setScene(new Scene(grid, 500, 400));
        stage.setTitle("Add Question - Quiz App");
        stage.show();
    }
}

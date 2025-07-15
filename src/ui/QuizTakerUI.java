package ui;

import dao.QuestionDAO;
import dao.QuizDAO;
import dao.ResultDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Question;
import model.Quiz;
import model.User;

import java.util.List;

public class QuizTakerUI {

    private final User user;
    private Quiz quiz;
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    public QuizTakerUI(User user) {
        this.user = user;
    }

    public void show(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label selectQuizLabel = new Label("Select a Quiz:");
        ComboBox<Quiz> quizBox = new ComboBox<>();
        Label messageLabel = new Label();

        try {
            quizBox.getItems().addAll(new QuizDAO().getAllQuizzes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button startButton = new Button("Start Quiz");
        Button backButton = new Button("Back");

        startButton.setOnAction(e -> {
            quiz = quizBox.getValue();
            if (quiz == null) {
                messageLabel.setText("⚠️ Please select a quiz first.");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }

            try {
                questions = new QuestionDAO().getQuestionsByQuizId(quiz.getId());
                if (questions.isEmpty()) {
                    messageLabel.setText("⚠️ No questions found for this quiz.");
                    messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    return;
                }

                currentIndex = 0;
                score = 0;
                showQuestion(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
                messageLabel.setText(" Failed to load quiz questions.");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
        });

        backButton.setOnAction(e -> {
            stage.close();
            Stage dashboardStage = new Stage();
            dashboardStage.setMaximized(true);
            new UserDashboardUI(user).show(dashboardStage);
        });

        HBox buttonBox = new HBox(10, startButton, backButton);
        buttonBox.setPadding(new Insets(10));

        root.getChildren().addAll(selectQuizLabel, quizBox, messageLabel, buttonBox);
        stage.setScene(new Scene(root, 400, 200));
        stage.setTitle("Take Quiz");
        stage.setMaximized(true);
        stage.show();
    }

    private void showQuestion(Stage stage) {
        if (currentIndex >= questions.size()) {
            showResultScreen(stage);
            return;
        }

        Question q = questions.get(currentIndex);
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label qLabel = new Label("Q" + (currentIndex + 1) + ": " + q.getQuestionText());
        ToggleGroup optionsGroup = new ToggleGroup();
        RadioButton optA = new RadioButton("A. " + q.getOptionA());
        RadioButton optB = new RadioButton("B. " + q.getOptionB());
        RadioButton optC = new RadioButton("C. " + q.getOptionC());
        RadioButton optD = new RadioButton("D. " + q.getOptionD());

        optA.setToggleGroup(optionsGroup);
        optB.setToggleGroup(optionsGroup);
        optC.setToggleGroup(optionsGroup);
        optD.setToggleGroup(optionsGroup);

        Label messageLabel = new Label();

        Button nextButton = new Button(currentIndex == questions.size() - 1 ? "Submit" : "Next");
        Button closeButton = new Button("Close");

        nextButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
            if (selected != null) {
                String selectedText = selected.getText().substring(0, 1);
                boolean isCorrect = selectedText.equalsIgnoreCase(q.getCorrectOption());

                if (isCorrect) score++;

                Alert feedback = new Alert(Alert.AlertType.INFORMATION);
                feedback.setTitle("Answer Feedback");
                feedback.setHeaderText(null);
                feedback.setContentText(isCorrect
                        ? "✅ Correct Answer!"
                        : "❌ Incorrect. Correct answer: " + q.getCorrectOption());
                feedback.showAndWait();

                currentIndex++;
                showQuestion(stage);
            } else {
                messageLabel.setText("⚠️ Please select an answer.");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
        });

        closeButton.setOnAction(e -> show(stage));

        HBox buttonBox = new HBox(10, nextButton, closeButton);
        buttonBox.setPadding(new Insets(10));

        root.getChildren().addAll(qLabel, optA, optB, optC, optD, messageLabel, buttonBox);
        stage.setScene(new Scene(root, 600, 300));
        stage.setTitle("Quiz - Question " + (currentIndex + 1));
        stage.setMaximized(true);
        stage.show();
    }

    private void showResultScreen(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label resultLabel = new Label("🎉 Quiz Completed!");
        Label scoreLabel = new Label("You scored " + score + " out of " + questions.size());

        try {
            boolean success = new ResultDAO().saveResult(user.getId(), quiz.getId(), score, questions.size());
            if (!success) {
                new Alert(Alert.AlertType.ERROR, "Failed to save result.").showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving result.").showAndWait();
        }

        Button backToListButton = new Button("Back to Quiz List");
        backToListButton.setOnAction(e -> show(stage));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            stage.close();
            Stage dashboardStage = new Stage();
            dashboardStage.setMaximized(true);
            new UserDashboardUI(user).show(dashboardStage);
        });

        HBox buttonBox = new HBox(10, backToListButton, closeButton);
        buttonBox.setPadding(new Insets(10));

        root.getChildren().addAll(resultLabel, scoreLabel, buttonBox);
        stage.setScene(new Scene(root, 400, 200));
        stage.setTitle("Quiz Result");
        stage.setMaximized(true);
        stage.show();
    }
}

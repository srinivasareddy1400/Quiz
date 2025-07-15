package ui;

import dao.QuizDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Quiz;
import model.User;

import java.sql.SQLException;

public class AdminDashboardUI {
    private final User admin;
    private final ObservableList<Quiz> quizzes = FXCollections.observableArrayList();
    private final ListView<Quiz> quizListView = new ListView<>();
    private final Label messageLabel = new Label();

    public AdminDashboardUI(User admin) {
        this.admin = admin;
    }

    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f0f0;");
        root.setAlignment(Pos.TOP_CENTER);

        Label welcomeLabel = new Label("Welcome Admin: " + admin.getUsername());
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        quizListView.setItems(quizzes);
        quizListView.setPrefHeight(200);
        refreshQuizList();

        messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        Button addQuizBtn = createStyledButton("➕ Add Quiz");
        Button addQuestionBtn = createStyledButton("➕ Add Questions");
        Button editQuizBtn = createStyledButton("✏️ Edit Title");
        Button deleteQuizBtn = createStyledButton("🗑️ Delete Quiz");
        Button backToLoginBtn = createStyledButton("🔙 Logout");

        HBox buttonBox = new HBox(10, addQuizBtn, addQuestionBtn, editQuizBtn, deleteQuizBtn);
        buttonBox.setAlignment(Pos.CENTER);


        addQuizBtn.setOnAction(e -> {
            new AddQuizUI(this::refreshQuizList).start(new Stage());
        });


        addQuestionBtn.setOnAction(e -> {
            Quiz selectedQuiz = quizListView.getSelectionModel().getSelectedItem();
            if (selectedQuiz == null) {
                showInlineMessage(Alert.AlertType.WARNING, "Please select a quiz first.");
                return;
            }
            new AddQuestionUI(selectedQuiz).show(new Stage());
        });


        editQuizBtn.setOnAction(e -> {
            Quiz selectedQuiz = quizListView.getSelectionModel().getSelectedItem();
            if (selectedQuiz == null) {
                showInlineMessage(Alert.AlertType.WARNING, "Select a quiz to edit.");
                return;
            }

            TextInputDialog dialog = new TextInputDialog(selectedQuiz.getTitle());
            dialog.setTitle("Edit Quiz Title");
            dialog.setHeaderText("Enter new title:");
            dialog.setContentText("Title:");

            dialog.showAndWait().ifPresent(newTitle -> {
                try {
                    boolean updated = new QuizDAO().updateQuizTitle(selectedQuiz.getId(), newTitle);
                    if (updated) {
                        refreshQuizList();
                        showInlineMessage(Alert.AlertType.INFORMATION, "Quiz updated.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    showInlineMessage(Alert.AlertType.ERROR, "Error updating quiz.");
                }
            });
        });


        deleteQuizBtn.setOnAction(e -> {
            Quiz selectedQuiz = quizListView.getSelectionModel().getSelectedItem();
            if (selectedQuiz == null) {
                showInlineMessage(Alert.AlertType.WARNING, "Select a quiz to delete.");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this quiz?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        boolean deleted = new QuizDAO().deleteQuiz(selectedQuiz.getId());
                        if (deleted) {
                            refreshQuizList();
                            showInlineMessage(Alert.AlertType.INFORMATION, "Quiz deleted.");
                        } else {
                            showInlineMessage(Alert.AlertType.ERROR, "Failed to delete quiz.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        showInlineMessage(Alert.AlertType.ERROR, "Cannot delete quiz: it has linked questions.");
                    }
                }
            });
        });


        backToLoginBtn.setOnAction(e -> {
            stage.close();
            Stage loginStage = new Stage();
            loginStage.setMaximized(true);
            new LoginUI().show(loginStage);
        });

        root.getChildren().addAll(
                welcomeLabel,
                new Label("Quizzes:"),
                quizListView,
                messageLabel,
                buttonBox,
                backToLoginBtn
        );

        stage.setScene(new Scene(root, 550, 520));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }

    private void refreshQuizList() {
        try {
            quizzes.setAll(new QuizDAO().getAllQuizzes());
            messageLabel.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            showInlineMessage(Alert.AlertType.ERROR, "Failed to load quizzes.");
        }
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #28a745;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );
        return button;
    }

    private void showInlineMessage(Alert.AlertType type, String message) {
        messageLabel.setText(message);
        switch (type) {
            case INFORMATION -> messageLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            case WARNING, ERROR -> messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            default -> messageLabel.setStyle("-fx-text-fill: black;");
        }
    }
}

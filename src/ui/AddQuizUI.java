
package ui;

import dao.QuizDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddQuizUI {

    private final Runnable onQuizAdded;

    public AddQuizUI(Runnable onQuizAdded) {
        this.onQuizAdded = onQuizAdded;
    }

    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TextField quizTitleField = new TextField();
        quizTitleField.setPromptText("Enter Quiz Title");

        Button createButton = new Button("Create Quiz");

        createButton.setOnAction(e -> {
            String title = quizTitleField.getText().trim();
            if (title.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Quiz title cannot be empty").showAndWait();
                return;
            }

            try {
                boolean created = new QuizDAO().addQuiz(title);
                if (created) {
                    new Alert(Alert.AlertType.INFORMATION, "Quiz added successfully!").showAndWait();
                    stage.close();
                    if (onQuizAdded != null) onQuizAdded.run();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add quiz").showAndWait();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Database error").showAndWait();
            }
        });

        root.getChildren().addAll(new Label("Quiz Title:"), quizTitleField, createButton);

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Add New Quiz");
        stage.show();
    }
}

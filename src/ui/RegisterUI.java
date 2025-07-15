package ui;

import dao.UserDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class RegisterUI {

    public void show(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Register");
        Button backButton = new Button("Back to Login");

        Label messageLabel = new Label();

        String blueStyle = "-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;";
        registerButton.setStyle(blueStyle);
        backButton.setStyle(blueStyle);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(registerButton, 1, 3);
        grid.add(backButton, 1, 4);
        grid.add(messageLabel, 1, 5);

        registerButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                messageLabel.setText("⚠️ All fields are required.");
                return;
            }

            try {
                User newUser = new User(username, email, password, "USER");
                boolean success = new UserDAO().register(newUser);

                if (success) {
                    messageLabel.setText("✅ Registration successful! Redirecting to login...");
                    registerButton.setDisable(true);

                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ignored) {}
                        javafx.application.Platform.runLater(() -> {
                            Stage loginStage = new Stage();
                            loginStage.setMaximized(true);
                            new LoginUI().show(loginStage);
                            stage.close();
                        });
                    }).start();

                } else {
                    messageLabel.setText("❌ Registration failed. Try another username.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                String errorMessage = ex.getMessage();

                if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                    if (errorMessage.contains("for key 'users.username'")) {
                        messageLabel.setText("⚠️ Username already exists.");
                    } else if (errorMessage.contains("for key 'users.email'")) {
                        messageLabel.setText("⚠️ Email already registered.");
                    } else {
                        messageLabel.setText("⚠️ Duplicate entry error.");
                    }
                } else {
                    messageLabel.setText("⚠️ An unexpected database error occurred.");
                }
            }
        });

        backButton.setOnAction(e -> {
            Stage loginStage = new Stage();
            loginStage.setMaximized(true);
            new LoginUI().show(loginStage);
            stage.close();
        });

        Scene scene = new Scene(grid, 400, 300);
        stage.setTitle("Register - Quiz App");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}

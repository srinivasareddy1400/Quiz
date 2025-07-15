
package ui;
import dao.UserDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class LoginUI {

    public void show(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");


        loginButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");
        registerButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");

        Label messageLabel = new Label();

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(registerButton, 1, 3);
        grid.add(messageLabel, 1, 4);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText(" Please enter username and paasword fields.");
                return;
            }

            try {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.login(username, password);

                if (user != null) {
                    messageLabel.setText("✅ Welcome, " + user.getUsername() + "!");

                    Stage nextStage = new Stage();
                    nextStage.setMaximized(true);

                    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                        new AdminDashboardUI(user).start(nextStage);
                    } else {
                        new UserDashboardUI(user).show(nextStage);
                    }
                    stage.close();
                } else {
                    messageLabel.setText(" Invalid credentials. Try again.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                messageLabel.setText(" Database error. Check console.");
            }
        });

        registerButton.setOnAction(e -> {
            Stage regStage = new Stage();
            regStage.setMaximized(true);
            new RegisterUI().show(regStage);
            stage.close();
        });

        Scene scene = new Scene(grid, 400, 250);
        stage.setTitle("Login - Quiz App");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
}

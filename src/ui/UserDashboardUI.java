package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;

public class UserDashboardUI {

    private final User user;

    public UserDashboardUI(User user) {
        this.user = user;
    }

    public void show(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome, " + user.getUsername() + "!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");


        Button startQuizBtn = new Button("Take a Quiz");
        Button viewScoresBtn = new Button("View My Results");
        Button leaderboardBtn = new Button("Leaderboard");
        Button logoutBtn = new Button("Logout");


        String blueStyle = "-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;";
        startQuizBtn.setStyle(blueStyle);
        viewScoresBtn.setStyle(blueStyle);
        leaderboardBtn.setStyle(blueStyle);
        logoutBtn.setStyle(blueStyle);


        HBox row1 = new HBox(15, startQuizBtn, viewScoresBtn);
        HBox row2 = new HBox(15, leaderboardBtn, logoutBtn);
        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER);


        startQuizBtn.setOnAction(e -> {
            Stage nextStage = new Stage();
            nextStage.setMaximized(true);
            new QuizTakerUI(user).show(nextStage);
        });

        viewScoresBtn.setOnAction(e -> {
            Stage nextStage = new Stage();
            nextStage.setMaximized(true);
            new UserResultsUI(user).show(nextStage);
        });

        leaderboardBtn.setOnAction(e -> {
            Stage nextStage = new Stage();
            nextStage.setMaximized(true);
            new LeaderboardUI(user).show(nextStage);

        });

        logoutBtn.setOnAction(e -> {
            stage.close();
            Stage loginStage = new Stage();
            loginStage.setMaximized(true);
            new LoginUI().show(loginStage);
        });


        root.getChildren().addAll(welcomeLabel, row1, row2);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("User Dashboard - Quiz App");
        stage.setFullScreen(true);

        stage.show();
    }
}

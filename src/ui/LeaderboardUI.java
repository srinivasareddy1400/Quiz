package ui;

import dao.ResultDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.util.List;

public class LeaderboardUI {

    private final User user;

    public LeaderboardUI(User user) {
        this.user = user;
    }

    public void show(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("🏆 Leaderboard - Top Scorers");
        ListView<String> listView = new ListView<>();

        try {
            ResultDAO dao = new ResultDAO();
            List<String> scores = dao.getLeaderboard();
            listView.getItems().addAll(scores);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> {
            stage.close();
            Stage dashboardStage = new Stage();
            dashboardStage.setMaximized(true);
            new UserDashboardUI(user).show(dashboardStage);
        });

        root.getChildren().addAll(title, listView, closeBtn);
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("Leaderboard");

        stage.show();
    }
}

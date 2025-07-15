package ui;

import dao.ResultDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.util.List;

public class UserResultsUI {

    private final User user;

    public UserResultsUI(User user) {
        this.user = user;
    }

    public void show(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("📊 My Quiz Attempts - " + user.getUsername());
        ListView<String> listView = new ListView<>();

        try {
            ResultDAO dao = new ResultDAO();
            List<String> results = dao.getUserResults(user.getId());

            if (results.isEmpty()) {
                listView.getItems().add("⚠ No quiz attempts found.");
            } else {
                listView.getItems().addAll(results);
            }
        } catch (Exception e) {
            e.printStackTrace();
            listView.getItems().add(" Failed to load results.");
        }

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> {
            stage.close();
            Stage dashboardStage = new Stage();
            dashboardStage.setMaximized(true);
            new UserDashboardUI(user).show(dashboardStage);
        });

        root.getChildren().addAll(title, listView, closeBtn);
        stage.setScene(new Scene(root, 500, 400));
        stage.setTitle("My Results");
        stage.show();
    }
}

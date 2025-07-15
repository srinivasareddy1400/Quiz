import javafx.application.Application;
import javafx.stage.Stage;
import ui.LoginUI;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        new LoginUI().show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

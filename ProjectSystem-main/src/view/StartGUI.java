package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class StartGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

            primaryStage.setTitle("Project Management Application");
            primaryStage.setScene(new javafx.scene.Scene(root));
            primaryStage.setMinHeight(650);
            primaryStage.setMinWidth(900);
            primaryStage.setMaxHeight(650);
            primaryStage.setMaxWidth(900);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

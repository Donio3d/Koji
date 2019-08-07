package Koji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("ConfigWindow.fxml"));

        AnchorPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Config Window");
        primaryStage.show();

    }
}

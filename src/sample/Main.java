package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();
        primaryStage.setTitle("Algoritmy");
        //primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.sizeToScene();
        primaryStage.setScene(new Scene(controller, 1000, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

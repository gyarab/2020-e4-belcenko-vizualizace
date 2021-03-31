package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    //ikona ze stranky https://material.io/resources/icons/?icon=sort&style=baseline
    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();

        primaryStage.setTitle("Algoritmy");
        //primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.sizeToScene();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        Scene scene = new Scene(controller, controller.a_window_width, controller.window_height);
        scene.getStylesheets().add("sample/algStyle.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

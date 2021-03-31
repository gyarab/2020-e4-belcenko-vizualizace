package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Node extends Rectangle {

    private int value;
    public static int movement_duration = 100;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    //pouziva se k inicializaci custom Node[]
    public void setValue(int arrLenght, int order, int max){

        setX(order * (Controller.b_window_width / arrLenght));
        setFill(Color.GOLD);
        setWidth(Controller.b_window_width /arrLenght  - Controller.node_gap);
        setHeight(((Controller.window_height - Controller.button_boundary) / max) * value);
        setY(Controller.window_height - Controller.button_boundary - this.getHeight() + 25);
    }

    //umoznuje transition za urcity cas, presouva se na pozici x
    public TranslateTransition move(int x) {

        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.millis(movement_duration));
        t.setNode(this);
        t.setByX(x);

        return t;
    }

}


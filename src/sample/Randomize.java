package sample;

import javafx.scene.paint.Color;

import java.util.Random;

public class Randomize {

    public static Node[] randomize(int n){
        Random rnd = new Random();
        Node[] arr = new Node[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Node(1 + rnd.nextInt(n));
            arr[i].setX(i * ((Controller.b_window_width) / n));
            arr[i].setFill(Color.GOLD);
            arr[i].setWidth(Controller.b_window_width /n  - Controller.node_gap);
            arr[i].setHeight(((Controller.window_height - Controller.button_boundary) / n) * arr[i].getValue());
            arr[i].setY(Controller.window_height - Controller.button_boundary - arr[i].getHeight() +20);
        }
        return arr;
    }
}

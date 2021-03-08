package sample;

import javafx.scene.paint.Color;

import java.util.Random;

public class Randomize {
    
    public Randomize() {
    }

    public static Node[] randomize(int n){
        Random rnd = new Random();
        Node[] arr = new Node[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Node(1 + rnd.nextInt(n));
            arr[i].setX(i * (Controller.window_width / n));
            arr[i].setFill(Color.CRIMSON);
            arr[i].setWidth(Controller.window_width / n - Controller.XGAP);
            arr[i].setHeight(((Controller.window_height - Controller.BUTTONROW_BOUNDARY) / n) * arr[i].getValue());
        }
        return arr;
    }

    private static void setCNodeDim(Node node, int length) {
    }
}

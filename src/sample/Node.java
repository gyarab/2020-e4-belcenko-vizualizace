package sample;

import javafx.scene.shape.Rectangle;

public class Node extends Rectangle {

    private int value;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


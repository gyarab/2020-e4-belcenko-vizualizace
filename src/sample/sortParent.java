package sample;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;


public abstract class sortParent {

    public static int nodeWidth = Controller.b_window_width / Controller.number_of_nodes; //velikost node
    final Color basic_Color = Color.GOLD;
    final Color selected_color = Color.RED;
    final Color compare_color = Color.BLUEVIOLET;
    final Color sorted_color = Color.GREEN;


    public abstract SequentialTransition startSort(Node[] arr);

    public abstract Node[] worstCase();

    public abstract String getInfo();

    //metoda k prohozeni 2 prvku
    public ParallelTransition swap(Node[] arr, int i, int j) {
        //graficky swap
        ParallelTransition pt = new ParallelTransition();
        int difference = j - i; //vzdalenost mezi node
        pt.getChildren().addAll(arr[i].move(nodeWidth * difference), arr[j].move(-nodeWidth * difference)); //samotny posun

        //swap v array
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        return pt;
    }

    //metoda k zmene barvy
    public FillTransition changeclr(Node n, Color clr) {
        FillTransition ft = new FillTransition(Duration.millis(20), n);
        ft.setToValue(clr);
        return ft;
    }

}

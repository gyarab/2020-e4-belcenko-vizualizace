package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

import java.util.ArrayList;

public class mergeSort extends sortParent {

    //potrebny pro animace
    private Node[] OGArray;

    private ParallelTransition merge(Node[] arr, int beg, int mid, int end) {
        ParallelTransition pt = new ParallelTransition();

        //naplnime array z originalu
        for (int i = beg; i <= end; i++) {
            OGArray[i] = arr[i];
        }

        //k: index pro prochazeni aktualniho pole, i,j: indexy pro originalni pole
        int i = beg, j = mid + 1, k = beg;

        //spojime 2 pole do jednoho
        while (i <= mid && j <= end) {
            if (OGArray[i].getValue() <= OGArray[j].getValue()) {
                arr[k] = OGArray[i];
                i++;
            }else{
                arr[k] = OGArray[j];
                j++;
            }
            k++;
        }

        //doplnime zbyvajici prvky
        while (i <= mid) {
            arr[k] = OGArray[i];
            i++;
            k++;
        }
        while (j <= end) {
            arr[k] = OGArray[j];
            j++;
            k++;
        }

        //porovname co vyslo s originalem a pridame potrebne animace pro merge do ParallelTransition
        for (int x = beg; x <= end; x++) {
            for (int y = beg; y <= end; y++) {
                if (OGArray[x].equals(arr[y])) {
                    pt.getChildren().add(OGArray[x].move(nodeWidth * (y - x)));
                }
            }
        }


        return pt;
    }

    /*
    * Kvuli neznamym duvodum mi u teto classy nefungovala metoda SequentialTransition, ale dala se ke stesti nahradit ArrayList<Transition>, ktery pak jde konvertovat
    * Obvykla rekurzivni forma
     */
    private ArrayList<Transition> mergeSort(Node[] arr, int beg, int end) {
        ArrayList<Transition> transitions = new ArrayList<>();

        if (beg < end) {
            int mid = (beg + end) / 2;
            transitions.addAll(mergeSort(arr, beg, mid));
            transitions.addAll(mergeSort(arr, mid + 1, end));
            transitions.add(merge(arr, beg, mid, end));
        }

        return transitions;
    }

    @Override
    public SequentialTransition startSort(Node[] arr) {

        this.OGArray = new Node[arr.length];

        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(mergeSort(arr, 0, arr.length - 1));

        return sq;
    }
}

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
    private ArrayList<Transition> mergesort(Node[] arr, int beg, int end) {
        ArrayList<Transition> transitions = new ArrayList<>();

        if (beg < end) {
            int mid = (beg + end) / 2;
            transitions.addAll(mergesort(arr, beg, mid));
            transitions.addAll(mergesort(arr, mid + 1, end));
            transitions.add(merge(arr, beg, mid, end));
        }

        return transitions;
    }

    @Override
    public SequentialTransition startSort(Node[] arr) {

        this.OGArray = new Node[arr.length];

        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(mergesort(arr, 0, arr.length - 1));

        return sq;
    }

    @Override
    public Node[] worstCase() {
        int num = Controller.number_of_nodes;
        Node[] arr = new Node[num];;
        int mid = num/2;
        int j = 0;
        for (int i = 1; i <= num; i++) {
            if(i % 2 != 0){
                arr[mid] = new Node(i);
                arr[mid].setValue(num, mid, num);
                mid++;
            }else{
                arr[j] = new Node(i);
                arr[j].setValue(num, j, num);
                j++;
            }
        }
        return arr;
    }

    @Override
    public String getInfo() {
        return "\u2022 Stable\n" +
                "\u2022 Average performance: O(n*log(n))\n" +
                "\u2022 Worst-case performance: O(n*log(n))\n" +
                "The algorithm works on recursion and subdivides itself\n" +
                "There are basically 2 steps:\n" +
                "\u2022 Divide the unsorted list into n sublists, each containing one element (a list of one element is considered sorted)\n" +
                "\u2022 Repeatedly merge sublists into sorted sublists until there is only one sublist remaining. This will be the sorted list\n";
    }
}

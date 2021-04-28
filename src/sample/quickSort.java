package sample;

import javafx.animation.SequentialTransition;

public class quickSort extends sortParent{

    private SequentialTransition transitions;

    //jako pivot bereme posledni prvek, sice existuje lepsi zpusob volby pivotu, nevim jak ho graficky znazornit
    int partition(Node arr[], int beg, int end)
    {
        int index = beg;
        int pivot = end;
        transitions.getChildren().add(changeclr(arr[pivot], compare_color));

        for (int i = beg; i < end; i++) {

            transitions.getChildren().add(changeclr(arr[pivot], selected_color));

            if (arr[i].getValue() < arr[pivot].getValue()) {
                transitions.getChildren().add(swap(arr, index, i));
                transitions.getChildren().add(changeclr(arr[index], basic_Color));
                index++;
            } else {
                transitions.getChildren().add(changeclr(arr[pivot], basic_Color));
            }
        }
        transitions.getChildren().add(swap(arr, index, end));
        transitions.getChildren().add(changeclr(arr[index], basic_Color));


        return index;
    }
    //obvykla rekurze
    void quickSort(Node a[], int beg, int end) {

        int pivot;
        if (beg < end) {
            pivot = partition(a, beg, end);
            quickSort(a, beg, pivot - 1);
            quickSort(a, pivot + 1, end);
        }
    }

    @Override
    public SequentialTransition startSort(Node[] arr) {
        this.transitions = new SequentialTransition();

        quickSort(arr, 0, arr.length - 1);

        return transitions;
    }

    @Override
    public Node[] worstCase() {
        int num = Controller.number_of_nodes;
        Node[] arr = new Node[num];
        for (int i = 0; i < num; i++) {
            arr[i] = new Node(i+1);
            arr[i].setValue(num, i, num);
        }
        return arr;
    }

    @Override
    public String getInfo() {
        return "\u2022 Unstable\n" +
                "\u2022 Average performance: O(n*log(n))\n" +
                "\u2022 Worst-case performance: O(n\u00B2)\n" +
                "The algorithm works on recursion and subdivides itself\n" +
                "The basic principle is:\n" +
                "\u2022 Choose a random or a previously selected item, this will be called a pivot\n" +
                "\u2022 Move very item greater than the pivot to the right of the pivot\n" +
                "\u2022 Move every item smaller than the pivot to the left of the pivot\n" +
                "\u2022 The pivot is now considered sorted\n" +
                "\u2022 Do all of the above again for the left and right side of the pivot while the size of the sublist is greater than 2\n";
    }
}

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
}

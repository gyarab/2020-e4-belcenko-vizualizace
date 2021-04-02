package sample;

import javafx.animation.SequentialTransition;

public class heapSort extends sortParent {

    private SequentialTransition transitions;

    //seradi pole do haldy
    private void heapify(Node[] arr, int i, int size) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size &&  arr[left].getValue() > arr[largest].getValue()) {
            largest = left;
        }
        if (right < size && arr[right].getValue() > arr[largest].getValue()) {
                largest = right;
        }

        if (largest != i) {
            transitions.getChildren().add(swap(arr, i, largest));
            heapify(arr, largest, size);
        }
    }

    //nekdriv zavola heapify dokud pole neni serazene, pote bere prvni prvek a zbytek pole zase preradi do haldy
    private void heapSort(Node[] arr, int size) {

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr, i, size);
        }

        for (int i = size - 1; i >= 0; i--) {
            transitions.getChildren().add(changeclr(arr[0], compare_color));
            transitions.getChildren().add(swap(arr, 0, i));
            heapify(arr, 0, i);;
        }
    }

    @Override
    public SequentialTransition startSort(Node[] arr) {
        this.transitions = new SequentialTransition();

        heapSort(arr, arr.length);

        return transitions;
    }

    @Override
    public Node[] worstCase() {
        return new Node[0];
    }
}

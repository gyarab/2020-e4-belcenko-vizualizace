package sample;

import javafx.animation.SequentialTransition;

public class bubbleSort extends sortParent {

    private SequentialTransition transitions;

    //bubblesort, co jineho, prochazi pole a porovnava vedlejsi elementy
    public void bubblesort(Node[] arr){
        boolean sorted = true;

        for (int i = 0; i < arr.length; i++) {

            sorted = true;

            for (int j = 0; j < arr.length -1 -i; j++) {
                transitions.getChildren().addAll(changeclr(arr[j], selected_color), changeclr(arr[j+1], selected_color));
                if (arr[j].getValue() > arr[j+1].getValue()) {
                    transitions.getChildren().add(swap(arr, j, j+1));
                    sorted = false;
                }
                transitions.getChildren().addAll(changeclr(arr[j], basic_Color), changeclr(arr[j+1], basic_Color));
            }

            if(sorted == true){
                break;
            }
        }
    }

    @Override
    public SequentialTransition startSort(Node[] arr) {
        transitions = new SequentialTransition();

        bubblesort(arr);

        return transitions;
    }

}

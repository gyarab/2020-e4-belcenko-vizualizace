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

    @Override
    public Node[] worstCase() {
        int num = Controller.number_of_nodes;
        Node[] arr = new Node[num];;
        int j = num;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node(j);
            arr[i].setValue(num, i, num);
            j--;
        }
        return arr;
    }

    @Override
    public String getInfo() {
        return "\u2022 Stable\n" +
                "\u2022 Average performance: O(n\u00B2)\n" +
                "\u2022 Worst-case performance: O(n\u00B2)\n" +
                "The algorithm repeatedly goes through the list and compares adjacent elements,\nthen swaps them if they are in the wrong order.\nThis is repeated until the list is sorted.\n";
    }

}

package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;

public class insertSort extends sortParent  {

    private SequentialTransition transitions;

    //postupuje po poli a kazdy dalsi prvek zaradi do setrizenenho pole
    public void insertSort(Node[] arr){

        for(int k=1; k < arr.length; k++){

            ParallelTransition pt = new ParallelTransition();

            Node temp = arr[k];
            int j = k-1;

            transitions.getChildren().add(changeclr(arr[k], selected_color));

            while(j>=0 && arr[j].getValue() >= temp.getValue()) {
                pt.getChildren().add(arr[j].move(nodeWidth));
                arr[j+1] = arr[j];
                j = j-1;
            }
            pt.getChildren().add(temp.move(nodeWidth * (j + 1 - k)));

            transitions.getChildren().add(pt);

            arr[j+1] = temp;

            transitions.getChildren().add(changeclr(arr[j+1], basic_Color));

        }

    }

    @Override
    public SequentialTransition startSort(Node[] arr) {

        this.transitions = new SequentialTransition();

        insertSort(arr);

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
                "The algorithm divides the input list into two parts:\n" +
                "\u2022 a sorted sublist of items\n" +
                "\u2022 and a sublist of the remaining unsorted items\n" +
                "Initially, the sorted sublist is empty and the unsorted sublist is the entire input list.\n" +
                "The algorithm then always takes the first item of the unsorted sublist and puts it into its correct place in the sorted sublist\n";
    }
}

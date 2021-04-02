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
}

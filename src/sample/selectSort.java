package sample;

import javafx.animation.SequentialTransition;

public class selectSort extends sortParent  {

    private SequentialTransition transitions;

    //najde nejmentsi prvek
    public int smallest(Node[] arr, int n, int i)
    {
        Node small;
        int pos;
        small = arr[i];
        pos = i;
        for(int j = i + 1; j < arr.length; j++){
            transitions.getChildren().add(changeclr(arr[j], selected_color));
            if(arr[j].getValue() < small.getValue()) {
                transitions.getChildren().add(changeclr(arr[pos], basic_Color));
                small = arr[j];
                pos = j;
                transitions.getChildren().add(changeclr(arr[pos], compare_color));
            }else{
                transitions.getChildren().add(changeclr(arr[j], basic_Color));
            }
        }
        transitions.getChildren().add(changeclr(arr[pos], basic_Color));
        return pos;
    }

    //hleda nejmensiho a pote ho dava na zacatek pole dokud neni pole serazene
    public void selectSort(Node[] arr){
        int pos;
        Node temp;
        for(int i = 0; i < arr.length; i++) {
            pos = smallest(arr,arr.length,i);
            transitions.getChildren().addAll(changeclr(arr[i], selected_color), changeclr(arr[pos], selected_color));
            transitions.getChildren().add(swap(arr, i, pos));
            transitions.getChildren().addAll(changeclr(arr[i], basic_Color), changeclr(arr[pos], basic_Color));
        }
    }

    @Override
    public SequentialTransition startSort(Node[] arr) {
        this.transitions = new SequentialTransition();

        selectSort(arr);

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
        return "\u2022 Unstable\n" +
                "\u2022 Average performance: O(n\u00B2)\n" +
                "\u2022 Worst-case performance: O(n\u00B2)\n" +
                "The algorithm divides the input list into two parts:\n" +
                "\u2022 a sorted sublist of items\n" +
                "\u2022 and a sublist of the remaining unsorted items\n" +
                "Initially, the sorted sublist is empty and the unsorted sublist is the entire input list.\n" +
                "The algorithm finds the smallest element in the unsorted sublist, and swaps it with the leftmost unsorted element.\n";
    }

}

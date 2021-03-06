package sample;


import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;
/*
Kont
 */
public class Controller extends BorderPane {

    //nepodarilo se mi udelat dynamicky resize, takze ke kontrole animaci spoleham na velikost obrazovky, ktera je zde zapsana
    public static int a_window_width = 1920;
    public static int b_window_width = a_window_width - 50;
    public static int window_height = 900;

    //oznacuje mezeru mezi prvky, aby nesplyvaly
    public static final int node_gap = 5;

    //dela misto pro interaktivni prvky
    public static final int button_boundary = 100;

    //ovlada pocet prvku a jejich pole
    public static int number_of_nodes = 30;
    private Node[] nodeArr;

    public Controller() {

        Pane display = new Pane();
        HBox buttonRow = new HBox();

        this.setCenter(display);
        this.setBottom(buttonRow);

        setAlignment(display, Pos.CENTER);

        this.nodeArr = Randomize.randomize(number_of_nodes);

        setAlignment(display, Pos.CENTER);
        setMargin(display, new Insets(0,0,0,25));
        setMargin(buttonRow, new Insets(0, 0, 20, 0));

        display.getChildren().addAll(nodeArr);


        HBox interactBox = new HBox();

        Button sortButton = new Button("Sort");
        sortButton.setMinWidth(a_window_width /11);

        Button randomButton = new Button("Random");
        ChoiceBox<sortParent> choiceBox = new ChoiceBox<>();

        Button submitButton = new Button("Submit");
        Button customSubmit = new Button("Submit");

        Button worstCase = new Button("Worst case");

        /*
         * zavola metodu vybranou v choiceboxu, ktera pote vrati SequentialTransition k provedeni
         * vypne random button a sort button, aby se nedalo v prubehu animace neco zkazit
         * pro choicebox to neni potreba
         */
        sortButton.setOnAction(Event ->{
            sortButton.setDisable(true);
            randomButton.setDisable(true);
            submitButton.setDisable(true);
            customSubmit.setDisable(true);
            sortParent sort = choiceBox.getSelectionModel().getSelectedItem();
            SequentialTransition sq = sort.startSort(nodeArr);
            sq.setOnFinished(e -> {
                randomButton.setDisable(false);
                submitButton.setDisable(false);
                customSubmit.setDisable(false);
            });
            sq.play();
        });

        //zameni array nodu za novy random array
        randomButton.setOnAction(event -> {
            sortButton.setDisable(false);
            display.getChildren().clear();
            nodeArr = Randomize.randomize(number_of_nodes);
            display.getChildren().addAll(nodeArr);
        });

        //Arraylist vsech sortu v choiceboxu
        List<sortParent> sortList = new ArrayList<>();
        sortList.add(new bubbleSort());
        sortList.add(new insertSort());
        sortList.add(new selectSort());
        sortList.add(new mergeSort());
        sortList.add(new quickSort());
        sortList.add(new heapSort());

        VBox setNodeNumber = new VBox();
        TextField numberField = new TextField();
        numberField.setPromptText("Number of random nodes");


        numberField.setPrefWidth(a_window_width /5);
        submitButton.setMinWidth(a_window_width /5);

        //zajistuje aby se nedalo zapsat nic jineho nez cisla
        numberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        //dovoluje zadat pocet objektu v poli
        submitButton.setOnAction(event -> {
            try{
                this.number_of_nodes = Integer.parseInt(numberField.getText());
            }catch(Exception e){
                System.out.println("Couldn't parse Integer");
                return;
            }
            sortButton.setDisable(false);
            display.getChildren().clear();
            for (sortParent sort: sortList) {
                sort.nodeWidth = b_window_width / number_of_nodes;
            }
            nodeArr = Randomize.randomize(number_of_nodes);
            display.getChildren().addAll(nodeArr);
        });

        VBox customBox = new VBox();
        TextField customInput = new TextField();
        customInput.setPromptText("Custom input: 1 1 2 3 5 8 13 21");

        customInput.setMinWidth(a_window_width / 5);
        customSubmit.setMinWidth(a_window_width/5);

        customSubmit.setAlignment(Pos.BOTTOM_CENTER);

        //ovlada custom input do pole, Input je formou: 1 2 3
        customSubmit.setOnAction(event -> {
            String in = customInput.getText();
            String[] sArr = in.split("[, ?.@]+");
            int[] iArr = new int[sArr.length];
            try{
                for (int i = 0; i < sArr.length; i++) {
                    iArr[i] = Integer.parseInt(sArr[i]);
                }
            }catch(Exception e){
                System.out.println("Couldn't parse Integer");
                return;
            }
            sortButton.setDisable(false);
            display.getChildren().clear();
            int max = 0;
            for(int i: iArr){
                if (i > max){
                    max = i;
                }
            }
            Node[] temp = new Node[sArr.length];
            for (int i = 0; i < sArr.length; i++){
                temp[i] = new Node(iArr[i]);
                temp[i].setValue(sArr.length, i, max);
            }
            this.nodeArr = temp;
            this.number_of_nodes = sArr.length;
            for (sortParent sort: sortList) {
                sort.nodeWidth = b_window_width / number_of_nodes;
            }
            display.getChildren().addAll(nodeArr);
        });

        //vraci nejhorsi pole pro dany al
        worstCase.setOnAction(event -> {
            sortButton.setDisable(false);
            display.getChildren().clear();
            nodeArr = choiceBox.getSelectionModel().getSelectedItem().worstCase();
            display.getChildren().addAll(nodeArr);
        });

        choiceBox.setOnAction(event -> {
            if(choiceBox.getSelectionModel().getSelectedItem().getClass().getSimpleName().equals("heapSort")){
                worstCase.setDisable(true);
            }else{
                worstCase.setDisable(false);
            }
        });

        customBox.getChildren().addAll(customInput, customSubmit);

        setNodeNumber.getChildren().addAll(numberField, submitButton);

        choiceBox.setItems(FXCollections.observableArrayList(sortList));
        choiceBox.getSelectionModel().select(0);

        //konvertuje jmeno classy do choiceboxu
        choiceBox.setConverter(new StringConverter<sortParent>() {
            @Override
            public String toString(sortParent sortParent) {
                    return sortParent.getClass().getSimpleName();
            }
            @Override
            public sortParent fromString(String string) {
                return null;
            }
        });

        interactBox.getChildren().addAll(sortButton, randomButton, choiceBox);
        interactBox.setSpacing(5);

        ImageView view = new ImageView(new Image(getClass().getResourceAsStream("baseline_info_black_24dp.png")));
        view.setPreserveRatio(true);
        Button infoButton = new Button();
        infoButton.setGraphic(view);

        infoButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                final Stage myDialog = new Stage();
                myDialog.initModality(Modality.WINDOW_MODAL);

                Button okButton = new Button("CLOSE");
                okButton.setOnAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent arg0) {
                        myDialog.close();
                    }

                });

                Scene myDialogScene = new Scene(VBoxBuilder.create()
                        .children(new Text(choiceBox.getSelectionModel().getSelectedItem().getInfo()), okButton)
                        .alignment(Pos.CENTER)
                        .padding(new Insets(20,20,20,20))
                        .build());

                myDialog.setScene(myDialogScene);
                myDialog.show();
            }
        });

        buttonRow.getChildren().addAll(infoButton, setNodeNumber, interactBox, customBox, worstCase);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(20);





    }
}


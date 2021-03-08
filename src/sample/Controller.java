package sample;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Kont
 */
public class Controller extends BorderPane {

    public static int window_width;
    public static int window_height;
    public static final int XGAP = 10;
    public static final int BUTTONROW_BOUNDARY = 100;

    public static int number_of_nodes = 20;
    private Node[] nodeArr;

    private Pane display;
    private HBox buttonRow;
    private HBox insertRow;
    private ChoiceBox<sortParent> choiceBox;
    private Button sortButton;
    private Button randomButton;
    private TextArea input;
    private Button submitButton;

    public Controller() {

        this.display = new Pane();
        this.buttonRow = new HBox();
        this.insertRow = new HBox();
        insertRow.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.setCenter(display);
        this.setBottom(buttonRow);
        this.setTop(insertRow);



        this.sortButton = new Button("Sort");
        this.randomButton = new Button("Random");
        this.choiceBox = new ChoiceBox<>();

        this.input = new TextArea();
        input.setPromptText("Insert values: 1, 2, 3");
        input.setPrefWidth(buttonRow.getWidth());
        this.submitButton = new Button("Submit");
        submitButton.setPrefWidth(buttonRow.getWidth()/4);

        buttonRow.getChildren().addAll(sortButton, randomButton, choiceBox);
        buttonRow.setAlignment(Pos.CENTER);

        for (javafx.scene.Node b : buttonRow.getChildren()) {
            buttonRow.setMargin(b, new Insets(5, 5, 20, 5));
        }

        insertRow.getChildren().addAll(input, submitButton);
        insertRow.setAlignment(Pos.CENTER);

        List<sortParent> sortParentArrayList = new ArrayList<>();
        sortParentArrayList.add(new sortParent());
        sortParentArrayList.add(new sortParent());
        sortParentArrayList.add(new sortParent());

        choiceBox.setItems(FXCollections.observableArrayList(sortParentArrayList));

        display.getChildren().addAll(Arrays.asList(nodes));
    }
}


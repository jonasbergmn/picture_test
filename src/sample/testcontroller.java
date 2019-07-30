package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class testcontroller {

    public AnchorPane ap;

    public void buttonClick(ActionEvent actionEvent) {
        GridPane gr = new GridPane();
        gr.setGridLinesVisible(true);
        for(int i = 1; i<=10; i++){
            gr.addRow(i);
            gr.addRow(i);
        }
        gr.addRow(1);
        gr.addColumn(1);
        ap.getChildren().add(gr);



        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++) {
                Label l = new Label("Hello" + i + " " + j);
                gr.add(l, i, j);
            }
        }



    }
}

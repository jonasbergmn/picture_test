package sample;

import com.sun.javafx.scene.ImageViewHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import sample.Main;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Controller {
    public Button button;
    public GridPane gr;
    public AnchorPane ap;
    public String path;

    public void buttonClick(ActionEvent actionEvent) throws IOException {
        Label l = new Label("hallo");
        Label l2 = new Label("hallo2");
        Label l3 = new Label("hallo3");
        for(int i = 0; i<10; i++){
            gr.addRow(i);
            gr.addColumn(i);
        }

        gr.add(l, 9,9);



    }

    public void fillGrid(String path, String path2) throws FileNotFoundException {
        //Import imp = new Import("C:\\Users\\joebe\\IdeaProjects\\picture_test\\src\\sample\\bacsv.CSV");
        Import imp = new Import(path2);
        Objekt o = imp.createObjekt(1);
        String[] ebene = imp.importEbene();
        Objekt[] objekte = {o};
        Betrachtungsebenen e = imp.createEbene(objekte,ebene);



        for(int i = 0; i<e.getWerteEbene().length;i++){
            Label label = new Label(e.getWerteEbene()[i]);
            gr.add(label,0,i+1);
        }


        //Image image = new Image(new FileInputStream("C:/Users/joebe/IdeaProjects/picture_test/src/sample/symbolkombination1.png"));
        Image image = new Image(new FileInputStream(path));
        int[] x = o.werteToInt();

        for (int i = 0; i < o.werteToInt().length; i++) {
            for (int j = 0; j < o.werteToInt()[i]; j++) {
                ImageView iview = new ImageView(image);
                gr.add(iview, j+1, i+1);
            }
        }
    }

    public int[] conc (){
        
        return null;
    }


    public String getValues(String v){
        return v;
    }
}

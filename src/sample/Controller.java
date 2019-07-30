package sample;

//import com.sun.javafx.scene.ImageViewHelper;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
    public Pane paneLeft;
    public Pane paneRight;
    public Pane paneEbene;

    public void buttonClick(ActionEvent actionEvent) throws IOException {
        Label l = new Label("hallo");
        Label l2 = new Label("hallo2");
        Label l3 = new Label("hallo3");
        for (int i = 0; i < 10; i++) {
            gr.addRow(i);
            gr.addColumn(i);
        }

        gr.add(l, 9, 9);

        for (int i = 0; i < conc("C:\\Users\\joebe\\Desktop").length; i++) {
            System.out.println(conc("C:\\Users\\joebe\\Desktop")[i]);
        }


    }

    public void fillGrid(String path, String path2) throws FileNotFoundException {
        //Import imp = new Import("C:\\Users\\joebe\\IdeaProjects\\picture_test\\src\\sample\\bacsv.CSV");
        Import imp = new Import(path2);
        Objekt o = imp.createObjekt(1);
        String[] ebene = imp.importEbene();
        Objekt[] objekte = {o};
        Betrachtungsebenen e = imp.createEbene(objekte, ebene);


        for (int i = 0; i < e.getWerteEbene().length; i++) {
            Label label = new Label(e.getWerteEbene()[i]);
            gr.add(label, 0, i + 1);
        }


        //Image image = new Image(new FileInputStream("C:/Users/joebe/IdeaProjects/picture_test/src/sample/symbolkombination1.png"));
        Image image = new Image(new FileInputStream(path));
        int[] x = o.werteToInt();

        for (int i = 0; i < o.werteToInt().length; i++) {
            for (int j = 0; j < o.werteToInt()[i]; j++) {
                ImageView iview = new ImageView(image);
                gr.add(iview, j + 1, i + 1);
            }
        }

    }

    public int[] conc(String path) {


        Import i = new Import(path);
        Objekt o = i.createObjekt(1);
        Objekt o2 = i.createObjekt(2);
        int[] conc = new int[o.werteToInt().length * 2];
        int counter = 0;

        for (int j = 0; j < o.getWerte().length * 2; j++) {
            if (j < o.getWerte().length) {
                conc[j] = o.werteToInt()[j];
            } else {
                conc[j] = o2.werteToInt()[counter];
            }
            if (j >= o.getWerte().length) {
                counter += 1;
            }

        }

        return conc;
    }


    public String getValues(String v) {
        return v;
    }

    public void fillGrid2(String path, String path2, int anzObjekte) throws FileNotFoundException {

        int counterRow = 0;
        int counterColumn = 0;

        //Import imp = new Import("C:\\Users\\joebe\\IdeaProjects\\picture_test\\src\\sample\\bacsv.CSV");
        Import imp = new Import(path2);
        Objekt[] oListe = new Objekt[anzObjekte];
        for (int i = 0; i < anzObjekte; i++) {
            oListe[i] = imp.createObjekt(i + 1);
        }

        String[] ebene = imp.importEbene();
        Objekt[] objekte = oListe;
        Betrachtungsebenen e = imp.createEbene(objekte, ebene);

        GridPane grEbene = new GridPane();

        for (int i = 0; i < e.getWerteEbene().length; i++) {
            Label label = new Label(e.getWerteEbene()[i]);
            label.setMinHeight(50);
            grEbene.addRow(i);
            grEbene.addColumn(i);
            grEbene.add(label, 0, i + 1);
        }

        paneEbene.getChildren().add(grEbene);

        Image image = new Image(new FileInputStream(path));
        System.out.println(image.getHeight());
        System.out.println(image.getWidth());

/*
        for (int i = 0; i < oListe[0].werteToInt().length; i++) {
            for (int j = 0; j < oListe[0].werteToInt()[i]; j++) {
                ImageView iview = new ImageView(image);
                gr.addRow(counterRow);
                gr.addColumn(counterColumn);
                gr.add(iview, j + 1, i + 1);
                counterColumn += 1;
                counterRow += 1;
            }
        }*/

        for(int k = 0; k<anzObjekte; k++) {
            boolean b=false;
            GridPane grx = new GridPane();
            int counterRow2 = 0;
            int counterColum2 = 0;
            for(int i = 0; i < oListe[k].werteToInt().length; i++){
                for (int j = 0; j < oListe[k].werteToInt()[i]; j++) {
                    ImageView iview = new ImageView(image);
                    grx.addRow(counterRow2);
                    grx.addColumn(counterColum2);
                    grx.add(iview, j+1, i+1);
                    counterColum2+=1;
                    counterRow2+=1;
                }
            }
            if(k==1){
                paneLeft.getChildren().add(grx);
            }else{
                paneRight.getChildren().add(grx);
            }

        }
        for(int i = 0; i<ap.getChildren().sorted().size();i++) {
            System.out.println(ap.getChildren().sorted().get(i));
        }
    }
}

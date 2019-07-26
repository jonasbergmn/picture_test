package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Controller {
    public ImageView image;
    public Button button;
    public ImageView image1;
    public ImageView image2;

    public void buttonClick(ActionEvent actionEvent) throws FileNotFoundException {

        //Image i = new Image(new FileInputStream("C:/Users/joebe/IdeaProjects/picture_test/src/sample/halbermensch.png"));
        //image.setImage(i);
        Image i2 = new Image(new FileInputStream("C:/Users/joebe/IdeaProjects/picture_test/src/sample/symbolkombination1.png"));
        image.setImage(i2);
        image2.setImage(i2);
        image1.setImage(i2);


    }
}

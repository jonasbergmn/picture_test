package sample;

import com.sun.jndi.toolkit.url.UrlUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class imageController {


    public ListView<String> list;
    public Button button;
    public ImageView iview;
    public String path;
    public String path1;
    public ListView list1;
    public ImageView iview1;
    public Button button1;
    public TextField textfield;
    public ImageView iview2;


    public void generateList() {

        ObservableList<String> sList = FXCollections.observableArrayList(getFilesInDirPrim());
        list.setItems(sList);

        ObservableList<String> sList1 = FXCollections.observableArrayList(getFilesInDirSek());
        list1.setItems(sList1);


        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Selected item: " + newValue);
                path = newValue;
                path = path.replace("\\", "\\\\");
                System.out.println(path);
                try {
                    Image image = new Image(new FileInputStream(path));
                    iview.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        list1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Selected item: " + newValue);
                path1 = newValue;
                path1 = path1.replace("\\", "\\\\");
                System.out.println(path1);
                try {
                    Image image = new Image(new FileInputStream(path1));
                    iview1.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    public void buttonClick(ActionEvent actionEvent) {
        generateList();
    }

    public String[] getFilesInDirPrim() {

        final String extension = ".png";
        final File currentDir = new File("C:\\Users\\joebe\\Desktop\\images\\prim");
        File[] files = currentDir.listFiles((File pathname) -> pathname.getName().endsWith(extension));
        String[] fileArray = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileArray[i] = files[i].getPath();
        }
        return fileArray;
    }

    public String[] getFilesInDirSek() {

        final String extension = ".png";
        final File currentDir = new File("C:\\Users\\joebe\\Desktop\\images\\sek");
        File[] files = currentDir.listFiles((File pathname) -> pathname.getName().endsWith(extension));
        String[] fileArray = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileArray[i] = files[i].getPath();
        }
        return fileArray;
    }

    public void buttonClick1(ActionEvent actionEvent) throws IOException {
        mergeImages();
    }

    public void mergeImages() throws IOException {

        boolean imageSet = false;
        boolean nameset = false;


        if ((path == null) || (path1 == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bild Fehler");
            alert.setHeaderText("Fehler: ");
            alert.setContentText("Bitte Bilder zum Kombinieren auswählen.");
            alert.showAndWait();
        } else {
            imageSet = true;
        }

        if (textfield.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Name Reference");
            alert.setHeaderText("Fehler: ");
            alert.setContentText("Bitte Namen für das kombinierte Bild eingeben, da es sonst nicht gespeichert werden kann.");
            alert.showAndWait();

        } else {
            nameset = true;
        }

        if(imageSet && nameset){
            BufferedImage overlay = ImageIO.read(new File(path1));
            BufferedImage tmpImg = new BufferedImage(overlay.getWidth(), overlay.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) tmpImg.getGraphics();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.4f));
            // set the transparency level in range 0.0f - 1.0f
            g2d.drawImage(overlay, 0, 0, null);
            overlay = tmpImg;

            // load source images
            BufferedImage image = ImageIO.read(new File(path));
            //BufferedImage overlay = ImageIO.read(new File( "C:\\Users\\joebe\\Desktop\\overlay.png"));

            // create the new image, canvas size is the max. of both image sizes
            int w = Math.max(image.getWidth(), overlay.getWidth());
            int h = Math.max(image.getHeight(), overlay.getHeight());

            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            // paint both images, preserving the alpha channels
            Graphics g = combined.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.drawImage(overlay, 0, 0, null);

            // Save as new image
            String x = textfield.getText();
            ImageIO.write(combined, "PNG", new File("C:\\Users\\joebe\\Desktop\\images\\comb\\"+x+".png"));
            Image imageComb = new Image(new FileInputStream("C:\\Users\\joebe\\Desktop\\images\\comb\\"+x+".png"));
            iview2.setImage(imageComb);
        }
    }
}

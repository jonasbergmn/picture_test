package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class importController {
    private static final String COMMA_DELIMITER = ";";
    public TextField textfield;
    public Button buttonImage2;
    public TextField textFaktor;
    public Label labelAnz;
    public Label labelEmpf;
    String pathFile;
    String pathImage;
    String pathImage2;

    public AnchorPane apImport;

    public void buttonFileClick(ActionEvent actionEvent) {
        this.pathFile = getPath();
        System.out.println(this.pathFile);
        List<List<String>> x = getData(this.pathFile);
        labelAnz.setText(Integer.toString(x.size()-1));
        if(x.size()-1 < 2){
            buttonImage2.setDisable(true);
        } else {
            buttonImage2.setDisable(false);
        }

        Import i = new Import("C:\\Users\\joebe\\Desktop\\bignumbers.CSV");
        Objekt o1 = i.createObjekt(1, null);
        Objekt o2 = i.createObjekt(2, null);
        int[] i1 = o1.werteToInt();
        int[] i2 = o2.werteToInt();
        labelEmpf.setText(Integer.toString(ggt(concatenate(i1,i2))) + " ist ggT");


    }

    public void buttonImageClick(ActionEvent actionEvent) {
        this.pathImage = getPath();
        System.out.println(this.pathImage);
    }

    public void buttonImage2Click(ActionEvent actionEvent) {
        this.pathImage2 = getPath();
        System.out.println(this.pathImage2);
    }

    public void buttonGoClick(ActionEvent actionEvent) throws IOException {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            Controller scene2Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here

            System.out.println(scene2Controller.getValues(this.pathFile));
            if(textFaktor.equals("")){
                scene2Controller.fillGrid2(this.pathImage, this.pathImage2, this.pathFile, Integer.parseInt(labelAnz.getText()));
            } else {
                scene2Controller.fillGrid2(this.pathImage, this.pathImage2, this.pathFile, Integer.parseInt(labelAnz.getText()), Integer.parseInt(textFaktor.getText()));
            }

            //scene2Controller.mergeImages();
//            int[] test = {60,70,80,80,90,50,50,50,50,50};
//            System.out.println("GGT: " + scene2Controller.ggt(test));
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Second Window");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public String getPath(){
        String path = "";
        Stage stage = (Stage) apImport.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle die Datei");
        String currentDir = "C:/Users/joebe/Desktop/images/comb";
        File file = new File(currentDir);
        fileChooser.setInitialDirectory(file);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("CSV", "*.CSV"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"));
        File file2 = fileChooser.showOpenDialog(stage);
        if (file2 != null) {
            String fileAsString = file2.toString();
            //System.out.println(fileAsString);
            path = fileAsString;
        }
        return path;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }


    public List<List<String>> getData(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return records;

    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public String getPathImage2() {
        return pathImage2;
    }

    public void setPathImage2(String pathImage2) {
        this.pathImage2 = pathImage2;
    }

    public void buttonCombClick(ActionEvent actionEvent) {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("images.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            imageController iController = loader.getController();
            iController.generateList();
            //Pass whatever data you want. You can have multiple method calls here

            //scene2Controller.mergeImages();
//            int[] test = {60,70,80,80,90,50,50,50,50,50};
//            System.out.println("GGT: " + scene2Controller.ggt(test));
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Second Window");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public int ggt (int values[]){

        int a = values[0];
        int b = 0;
        for(int i = 1; i<values.length;i++){
            b = values[i];
            while(b!=0){
                b = a % (a=b);
            }
        }
        return a;
    }

    public int[] concatenate(int[] i1, int[] i2) {
        int[] array1 = i1;
        int[] array2 = i2;
        int aLen = array1.length;
        int bLen = array2.length;
        int[] result = new int[aLen + bLen];
        System.arraycopy(array1, 0, result, 0, aLen);
        System.arraycopy(array2, 0, result, aLen, bLen);
        System.out.println(Arrays.toString(result));
        return result;
    }

}

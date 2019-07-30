package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class importController {
    private static final String COMMA_DELIMITER = ";";
    public TextField textfield;
    public Button buttonImage2;
    String pathFile;
    String pathImage;
    String pathImage2;

    public AnchorPane apImport;

    public void buttonFileClick(ActionEvent actionEvent) {
        this.pathFile = getPath();
        System.out.println(this.pathFile);
        List<List<String>> x = getData(this.pathFile);
        textfield.setText(Integer.toString(x.size()-1));
        if(x.size()-1 < 2){
            buttonImage2.setDisable(true);
        }

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
            scene2Controller.fillGrid2(this.pathImage, this.pathFile, Integer.parseInt(textfield.getText()));

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
        String currentDir = "C:/Users/joebe/Desktop/";
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
}

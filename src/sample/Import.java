package sample;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Import {

    private static final String COMMA_DELIMITER = ";";
    String path;
    //"C:\\Users\\joebe\\IdeaProjects\\BA\\src\\sample\\bacsv.CSV"

    public Import(String path){
        this.path = path;
    }

    public String[] importEbenen(){
        List<List<String>> x = getData();
        x.get(0).remove(0);
        String[] ebene = new String[x.get(0).size()];
        for(int i = 0; i<x.get(0).size();i++){
            ebene[i]=x.get(0).get(i);
            //System.out.println("Wert: " + ebene[i]);
        }
        return ebene;
    }

    public String[] importWerte(int objectId){
        List<List<String>> x = getData();
        String[] werte = new String[x.get(objectId).size()];
        for(int i = 0; i<x.get(objectId).size(); i++){
            werte[i] = x.get(objectId).get(i);
            System.out.println("Objektwerte: " + werte[i]);
        }
        return werte;
    }

    public String[] importEbene(){
        List<List<String>> x = getData();
        String[] ebene = new String[x.get(0).size()];
        for(int i = 1; i<x.get(0).size(); i++){
            ebene[i-1] = x.get(0).get(i);
            System.out.println(ebene[i-1]);
        }
        return ebene;
    }

    public Objekt createObjekt(int objectId, String path){

        String[] werte = importWerte(objectId);
        String name = werte[0];
        Double[] d = new Double[werte.length-1];
        for(int i = 0; i<werte.length; i++){
            try{
                d[i-1] = Double.valueOf(werte[i]);
            } catch (NumberFormatException e) {
               d[i] = 0.0;
            }
        }
        Objekt o = new Objekt(name, d,path );
        return o;
    }

    public Objekt createObjekt(String name, int[] werte, String path){
        Objekt o = new Objekt(name, werte, path);
        return o;
    }

    public Betrachtungsebenen createEbene(Objekt[] o, String[] s){

        Betrachtungsebenen b = new Betrachtungsebenen(s, o);
        return b;
    }

    public List<List<String>> getData() {
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

}

package sample;

public class Objekt {

    private String name;
    private Double[] werte;
    private int[] werteInt;
    private String path;
    private int faktor;

    public Objekt(String name, Double[] werte, String path){
        this.name = name;
        this.werte = werte;
        this.path = path;
    }

    public Objekt(String name, int[] werteInt, String path){
        this.werteInt = werteInt;
        this.name = name;
        this.path = path;
    }

    public int[] werteToInt(){
        werteInt = new int[werte.length];
        for(int i = 0; i<werte.length; i++){
            werteInt[i] = werte[i].intValue();
        }
        return werteInt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getWerte() {
        return werte;
    }

    public void setWerte(Double[] werte) {
        this.werte = werte;
    }

    public int[] getWerteInt() {
        return werteInt;
    }

    public void setWerteInt(int[] werteInt) {
        this.werteInt = werteInt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFaktor() {
        return faktor;
    }

    public void setFaktor(int faktor) {
        this.faktor = faktor;
    }
}

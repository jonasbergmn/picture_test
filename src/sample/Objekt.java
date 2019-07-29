package sample;

public class Objekt {

    private String name;
    private Double[] werte;
    private int[] werteInt;

    public Objekt(String name, Double[] werte){
        this.name = name;
        this.werte = werte;
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
}

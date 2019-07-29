package sample;

public class Betrachtungsebenen {

    public String[] getWerteEbene() {
        return werteEbene;
    }

    public void setWerteEbene(String[] werteEbene) {
        this.werteEbene = werteEbene;
    }

    public Objekt[] getObjektListe() {
        return objektListe;
    }

    public void setObjektListe(Objekt[] objektListe) {
        this.objektListe = objektListe;
    }

    private String[] werteEbene;
    private Objekt[] objektListe;

    public Betrachtungsebenen(String[] werteEbene, Objekt[] objektListe){
        this.werteEbene = werteEbene;
        this.objektListe = objektListe;
    }


}

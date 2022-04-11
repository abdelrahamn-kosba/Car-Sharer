package de.unidue.inf.is.domain;



public class Fahrt {
    // attributes
    private int Fid;
    private String startort;
    private String zielort;
    private String fahrtdatumzeit;
    private int maxPlaetze;
    private float fahrtkosten;
    private String status;
    private int anbieter;
    private int transportmittel;
    private String beschreibung;
    private int freierPlaetze;


    //general Constructor
    public Fahrt(String startort, String zielort, String fahrtdatumzeit, int maxPlaetze, float fahrtkosten, String status, int anbieter, int transportmittel, String beschreibung) {

        this.startort = startort;
        this.zielort = zielort;
        this.fahrtdatumzeit = fahrtdatumzeit;
        this.maxPlaetze = maxPlaetze;
        this.fahrtkosten = fahrtkosten;
        this.status = status;
        this.anbieter = anbieter;
        this.transportmittel = transportmittel;
        this.beschreibung = beschreibung;
    }
    // Constructor for meine reservierte fahrten
    public Fahrt(int fid, String startort, String zielort, String status,int transportmittel) {
        this.Fid = fid;
        this.startort = startort;
        this.zielort = zielort;
        this.status = status;
        this.transportmittel= transportmittel;
    }

    //constructor for offene fahrten
    public Fahrt(int fid, String startort, String zielort, float fahrtkosten, int freierPlaetze,int transportmittel) {
        this.Fid = fid;
        this.startort = startort;
        this.zielort = zielort;
        this.fahrtkosten = fahrtkosten;
        this.freierPlaetze = freierPlaetze;
        this.transportmittel=transportmittel;
    }
    // constructor for fahrt details
    public Fahrt(int fid,String startort, String zielort, String fahrtdatumzeit, float fahrtkosten, String status, String beschreibung, int freierPlaetze,int Transportmittel) {
        this.Fid=fid;
        this.startort = startort;
        this.zielort = zielort;
        this.fahrtdatumzeit = fahrtdatumzeit;
        this.fahrtkosten = fahrtkosten;
        this.status = status;
        this.beschreibung = beschreibung;
        this.freierPlaetze = freierPlaetze;
        this.transportmittel=Transportmittel;
    }
// constr. for fartSuche
    public Fahrt(int fid,String startort, String zielort, float fahrtkosten,int transportmittel) {
        this.Fid = fid;
        this.startort = startort;
        this.zielort = zielort;
        this.fahrtkosten = fahrtkosten;
        this.transportmittel=transportmittel;
    }


    // constr for best driver fahrt ** I am saving durchschnittlich v

    public Fahrt(int fid,int transportmittel ,String startort, String zielort) {
        this.Fid=fid;
        this.transportmittel=transportmittel;
        this.startort = startort;
        this.zielort = zielort;
    }

    // getter and setter
    public int getFid() {
        return Fid;
    }

    public void setFid(int fid) {
        Fid = fid;
    }

    public String getStartort() {
        return startort;
    }

    public void setStartort(String startort) {
        this.startort = startort;
    }

    public String getZielort() {
        return zielort;
    }

    public void setZielort(String zielort) {
        this.zielort = zielort;
    }

    public String getFahrtdatumzeit() {
        return fahrtdatumzeit;
    }

    public void setFahrtdatumzeit(String fahrtdatumzeit) {
        this.fahrtdatumzeit = fahrtdatumzeit;
    }

    public int getMaxPlaetze() {
        return maxPlaetze;
    }

    public void setMaxPlaetze(int maxPlaetze) {
        this.maxPlaetze = maxPlaetze;
    }

    public float getFahrtkosten() {
        return fahrtkosten;
    }

    public void setFahrtkosten(float fahrtkosten) {
        this.fahrtkosten = fahrtkosten;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(int anbieter) {
        this.anbieter = anbieter;
    }

    public int getTransportmittel() {
        return transportmittel;
    }

    public void setTransportmittel(int transportmittel) {
        this.transportmittel = transportmittel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getFreierPlaetze() {
        return freierPlaetze;
    }

    public void setFreierPlaetze(int freierPlaetze) {
        this.freierPlaetze = freierPlaetze;
    }
}

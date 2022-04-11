package de.unidue.inf.is.domain;

import de.unidue.inf.is.stores.FahrtReservierenStore;

public class reservieren {
    int kunde;
    int fahrt;
    int anzPlaetze;

    public reservieren(int kunde, int fahrt, int anzPlaetze) {
        this.kunde = kunde;
        this.fahrt = fahrt;
        this.anzPlaetze = anzPlaetze;
    }

    public reservieren(int fid, int bid) {
        this.fahrt = fid;
        this.kunde = bid;
    }
    public void reserv(){
        FahrtReservierenStore fahrtReservierenStore= new FahrtReservierenStore();
    }
    public int getKunde() {
        return kunde;
    }

    public void setKunde(int kunde) {
        this.kunde = kunde;
    }

    public int getFahrt() {
        return fahrt;
    }

    public void setFahrt(int fahrt) {
        this.fahrt = fahrt;
    }

    public int getAnzPlaetze() {
        return anzPlaetze;
    }

    public void setAnzPlaetze(int anzPlaetze) {
        this.anzPlaetze = anzPlaetze;
    }
}

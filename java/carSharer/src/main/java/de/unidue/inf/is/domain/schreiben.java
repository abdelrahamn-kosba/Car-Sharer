package de.unidue.inf.is.domain;

public class schreiben {
    int benutzer;
    int fahrt;
    int bewertung;

    public schreiben(int benutzer, int fahrt) {
        this.benutzer = benutzer;
        this.fahrt = fahrt;
    }

    public int getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(int benutzer) {
        this.benutzer = benutzer;
    }

    public int getFahrt() {
        return fahrt;
    }

    public void setFahrt(int fahrt) {
        this.fahrt = fahrt;
    }

    public int getBewertung() {
        return bewertung;
    }

    public void setBewertung(int bewertung) {
        this.bewertung = bewertung;
    }
}

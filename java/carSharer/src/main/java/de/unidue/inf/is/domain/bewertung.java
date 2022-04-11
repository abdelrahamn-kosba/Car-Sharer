package de.unidue.inf.is.domain;

import de.unidue.inf.is.utils.DateTimeUtil;

public class bewertung {
    int beid;
    String textnachricht;
    String  erstellungsdatum;
    int rating;

    public bewertung(int beid, String textnachricht, String erstellungsdatum, int rating) {
        this.beid = beid;
        this.textnachricht = textnachricht;
        this.erstellungsdatum = erstellungsdatum;
        this.rating = rating;
    }

    public bewertung(String textnachricht, int rating) {
        this.textnachricht = textnachricht;
        this.rating = rating;
    }

    public int getBeid() {
        return beid;
    }

    public void setBeid(int beid) {
        this.beid = beid;
    }

    public String getTextnachricht() {
        return textnachricht;
    }

    public void setTextnachricht(String textnachricht) {
        this.textnachricht = textnachricht;
    }

    public String getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(String erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

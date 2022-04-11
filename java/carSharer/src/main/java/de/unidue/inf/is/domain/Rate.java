package de.unidue.inf.is.domain;

public class Rate {
    String benutzerEmail;
    String beschreibung;
    int Rate;

    public Rate(String benutzerEmail, String beschreibung, int rate) {
        this.benutzerEmail = benutzerEmail;
        this.beschreibung = beschreibung;
        this.Rate = rate;
    }

    public String getBenutzerEmail() {
        return benutzerEmail;
    }

    public void setBenutzerEmail(String benutzerEmail) {
        this.benutzerEmail = benutzerEmail;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }
}

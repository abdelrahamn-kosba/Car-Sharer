package de.unidue.inf.is.domain;

import de.unidue.inf.is.utils.DateTimeUtil;

public class fahrerlaubnis {
    int fahrer;
    int nummer;
    DateTimeUtil ablaufdatum;

    public fahrerlaubnis(int fahrer, int nummer, DateTimeUtil ablaufdatum) {
        this.fahrer = fahrer;
        this.nummer = nummer;
        this.ablaufdatum = ablaufdatum;
    }

    public fahrerlaubnis(int fahrer, int nummer) {
        this.fahrer = fahrer;
        this.nummer = nummer;
    }

    public int getFahrer() {
        return fahrer;
    }

    public void setFahrer(int fahrer) {
        this.fahrer = fahrer;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public DateTimeUtil getAblaufdatum() {
        return ablaufdatum;
    }

    public void setAblaufdatum(DateTimeUtil ablaufdatum) {
        this.ablaufdatum = ablaufdatum;
    }
}

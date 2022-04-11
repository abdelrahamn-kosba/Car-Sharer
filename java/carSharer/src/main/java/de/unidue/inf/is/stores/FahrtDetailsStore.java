package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.*;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.DateTimeUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class FahrtDetailsStore implements Closeable {
    private Connection connection;
    private boolean complete;
    public benutzer benutzer;
    public List<benutzer> benutzers=new ArrayList<>();

    public de.unidue.inf.is.domain.benutzer getBenutzer() {
        return benutzer;
    }




    public List<de.unidue.inf.is.domain.benutzer> getBenutzers() {
        return benutzers;
    }

    public FahrtDetailsStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public Fahrt getFahrtDetails(int Fid){
        Fahrt fahrt = null;
        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT f.transportmittel,f.FID,f.STARTORT,f.ZIELORT,f.FAHRTDATUMZEIT,f.MAXPLAETZE,r.anzPlaetze,f.FAHRTKOSTEN,f.STATUS,f.ANBIETER,b.email,f.BESCHREIBUNG from dbp109.fahrt f left JOIN (SELECT fahrt,sum(anzPlaetze)AS anzPlaetze FROM dbp109.reservieren r GROUP BY fahrt)r ON f.fid=r.fahrt INNER JOIN dbp109.benutzer b ON f.ANBIETER=b.bid where f.fid=?");
            preparedStatement.setInt(1,Fid);
            ResultSet Res =preparedStatement.executeQuery();
            try {
                Res.next();
                fahrt = new Fahrt(
                        Res.getInt("FID"),
                        Res.getString("STARTORT"),
                        Res.getString("ZIELORT"),
                        (DateTimeUtil.extractDateFromDB2DateTimeString (Res.getString("FAHRTDATUMZEIT"))+"    "+ DateTimeUtil.extractTimeFromDB2DateTimeString (Res.getString("FAHRTDATUMZEIT"))),
                        Res.getFloat("FAHRTKOSTEN"),
                        Res.getString("STATUS"),
                        Res.getString("BESCHREIBUNG"),
                        (Res.getInt("MAXPLAETZE")-Res.getInt("ANZPLAETZE")),
                        Res.getInt("Transportmittel"));
                System.out.println(fahrt.getBeschreibung());
                benutzer = new benutzer(Res.getString("EMAIL"));
                return fahrt;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fahrt;
    }

    // queries for bewertungen (email comment rate)
    public List<Rate> getbewertung(int fid){
        List<Rate> Rates=new ArrayList<>();
        try (PreparedStatement preparedStatement=connection.
                prepareStatement("SELECT RATING,EMAIL,TEXTNACHRICHT FROM dbp109.bewertung r INNER JOIN dbp109.schreiben s ON s.bewertung=r.beid INNER JOIN dbp109.benutzer b ON s.benutzer=b.bid where FAHRT=? order by bewertung DESC")){
            preparedStatement.setInt(1,fid);
            ResultSet Res=preparedStatement.executeQuery();
            while (Res.next()){
                Rate rate=new Rate(Res.getString("Email"), Res.getString("TEXTNACHRICHT"),Res.getInt("RATING"));
                Rates.add(rate);
            }
            return Rates;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Rates;
    }

    // query for durschschnittrating
    public float getAveragerate(int fid){
        float avg = 0;
        try (PreparedStatement preparedStatement=connection.
                prepareStatement("SELECT Avg(Cast(rating as decimal(31,2))) as average FROM dbp109.bewertung r INNER JOIN dbp109.schreiben s ON s.bewertung=r.beid  where FAHRT=?")){
            preparedStatement.setInt(1,fid);
            ResultSet Res=preparedStatement.executeQuery();
            Res.next();
            avg= Res.getFloat("average");
            return avg;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return avg;
    }



    public void complete() {
        complete = true;
    }



    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                    System.out.println("HI from Fahrt Details Store line 125, I committed your changes in database");

                }
                else {
                    connection.rollback();
                    System.out.println("HI from Fahrt Details Store line 130, I Rolled back your changes in database");

                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                    System.out.println("HI from Fahrt Erstellen Store line 140, The connection with database has been closed");

                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }

}

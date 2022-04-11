package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FahrtSucheStore implements Closeable {

    private Connection connection;
    private boolean complete;

    public FahrtSucheStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }




    public List<Fahrt> FahrtSuche(String StartOrt, String ZielOrt, String date){
        List<Fahrt> fahrtSuche  = new ArrayList<>();
        try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from dbp109.fahrt f where ((LCASE(f.startort) like ? or f.startort like ?) or (UCASE(f.startort) like ? or f.startort like ?)) and ((LCASE(f.zielort) like ? or f.zielort like ?) or (UCASE(f.zielort) like ? or f.zielort like ?)) and f.fahrtdatumzeit >=?" )){
            preparedStatement.setString(1,"%"+ StartOrt + "%");
            preparedStatement.setString(2,"%"+StartOrt + "%");
            preparedStatement.setString(3,"%"+StartOrt + "%");
            preparedStatement.setString(4,"%"+StartOrt + "%");
            preparedStatement.setString(5,"%"+ZielOrt + "%");
            preparedStatement.setString(6,"%"+ZielOrt + "%");
            preparedStatement.setString(7,"%"+ZielOrt + "%");
            preparedStatement.setString(8,"%"+ZielOrt + "%");
            preparedStatement.setString(9,date);
            ResultSet Res= preparedStatement.executeQuery();
            System.out.println("I am in fahrtsuche now");
            while (Res.next()){
                System.out.println("I am here");
                Fahrt fahrt= new Fahrt(Res.getInt("fid"), Res.getString("startort"), Res.getString("zielort"), Res.getInt("fahrtkosten"),Res.getInt("TRANSPORTMITTEL"));
                fahrtSuche.add(fahrt);
                System.out.println("Hello from suche store I did the search: I found sth ");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Hello from suche store I did the search:  nothing ");

        return fahrtSuche;
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
                    System.out.println("HI from FahrtSucheStore,I committed your changes in database");

                }
                else {
                    connection.rollback();
                    System.out.println("HI from FahrtSucheStore,I rolled back your changes in database");

                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                    System.out.println("HI from FahrtSucheStore, the connection with data base has benn closed");
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}

package de.unidue.inf.is.stores;

import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class FahrtBewrtenStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public FahrtBewrtenStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public boolean FahrtBewerten(int benutzerid, int fahrtid,String beschreibung,String erstllungsdatum ,int bewertung) {
        boolean check;

        /* check if the kunde is the ersteller or not , if not proceed*/

        try (PreparedStatement preparedStatement0=connection.prepareStatement("select f.fid from dbp109.fahrt f where f.anbieter=? and f.fid=?")){
            preparedStatement0.setInt(1,benutzerid);
            preparedStatement0.setInt(2,fahrtid);
            ResultSet Res0= preparedStatement0.executeQuery();

            if(! Res0.next()){
                // check if user rate before or not
                try (PreparedStatement preparedStatement1=connection.
                        prepareStatement("select benutzer from dbp109.schreiben where benutzer=? and fahrt=?")){
                    System.out.println(":"+benutzerid+"/"+fahrtid);
                    preparedStatement1.setInt(1,benutzerid);
                    preparedStatement1.setInt(2,fahrtid);
                    System.out.println("after check if user rate before or not");
                    ResultSet res1=preparedStatement1.executeQuery();
                    if(!res1.next()){
                        // insert beschreibung , date , rate in bewertung table and get bewertung id
                        try (PreparedStatement preparedStatement2= connection.
                                prepareStatement("SELECT beid from new table (insert into dbp109.bewertung (textnachricht,erstellungsdatum,rating) values (?,?,?))")){
                            preparedStatement2.setString(1,beschreibung);
                            preparedStatement2.setString(2,erstllungsdatum);
                            preparedStatement2.setInt(3,bewertung);
                            ResultSet Res2= preparedStatement2.executeQuery();
                            Res2.next();
                            int BewertungID= Res2.getInt("beid");
                            // insert bewertung in schreiben table
                            try (PreparedStatement preparedStatement3= connection.
                                    prepareStatement("insert into dbp109.schreiben (benutzer,fahrt,bewertung) values(?,?,?)")){
                                preparedStatement3.setInt(1,benutzerid);
                                preparedStatement3.setInt(2,fahrtid);
                                preparedStatement3.setInt(3,BewertungID);
                                preparedStatement3.executeUpdate();
                                System.out.println("insert bewertung in schreiben table succeed");
                                return true;

                            } catch (SQLException throwables) {
                                throwables.printStackTrace();}

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();}

                    }else

                        return false;

                } catch (SQLException throwables) {
                    System.out.println("die kunde hat schon einmal bewert ");
                    throwables.printStackTrace();


                }
            }

        } catch (SQLException throwables) {
            System.out.println("die kunde ist der ersteller , Sie konnen nicht bewertung machen");
            throwables.printStackTrace();
        }

        return false;
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
                    System.out.println("HI from Fahrt Bewerten Store line 108, I committed your changes in database");

                }
                else {
                    connection.rollback();
                    System.out.println("HI from Fahrt Bewerten Store line 108, I rolled back your changes in database");

                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                    System.out.println("hi from FahrtBewertenStore the connection with database has been closed");
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}

package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.utils.DBUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BestDriverStore implements Closeable {
    private Connection connection;
    private boolean complete;
    public String BestDriverEmail;
    private float average;

    public  String getBestDriverEmail() {
        System.out.println("HI FROM REturn func: "+ BestDriverEmail);
        return this.BestDriverEmail;
    }

    public float getAverage() {
        return average;
    }



    public BestDriverStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }



    public List<Fahrt> BestDriverData (){
        List<Fahrt> bestDriverFahrten = new ArrayList<>();
   // get average and email  and anbieter id of best driver
        try(PreparedStatement preparedStatement= connection.prepareStatement( "SELECT t2.average, t2.email,t2.anbieter FROM (SELECT t1.average, b.email,t1.anbieter FROM (select t.fahrt,t.average,f.anbieter,f.startort,f.zielort FROM (select s.fahrt,Avg(Cast(rating as decimal(31,2))) as Average  from dbp109.schreiben s inner join dbp109.bewertung b on s.bewertung=b.beid group by s.fahrt )t left join dbp109.fahrt f on t.fahrt=f.fid )t1 left join dbp109.benutzer b on b.bid=t1.anbieter)t2 WHERE t2.average=(select max(average3) FROM (select  Avg(Cast(rating as decimal(31,2))) as average3 from dbp109.schreiben s3 inner join dbp109.bewertung b3 ON s3.bewertung=b3.beid left join dbp109.fahrt f3 ON s3.fahrt=f3.fid group BY s3.fahrt)t2)")){
            ResultSet Res=preparedStatement.executeQuery();
            System.out.println("Hello after exe");
            /** Driver email **/
            Res.next();
            this.BestDriverEmail =Res.getString("EMAIL");
            System.out.println("benutzer email: " +Res.getString("EMAIL"));
            int bestdriverID=Res.getInt("ANBIETER");
             average= Res.getFloat("AVERAGE");
            System.out.println("Hello after BestDriverEmail");

            /** best driver Fahrt details  **/
           try (PreparedStatement preparedStatement2 =connection.prepareStatement("select f.fid,f.transportmittel, f.startort,f.zielort from dbp109.fahrt f where f.anbieter=?")){
               preparedStatement2.setInt(1,bestdriverID);
               ResultSet Res2=preparedStatement2.executeQuery();
               while (Res2.next()){
                   Fahrt  bestDriverFahrt = new Fahrt(Res2.getInt("fid"),Res2.getInt("TRANSPORTMITTEL"),Res2.getString("startort"),Res2.getString("zielort"));
                   bestDriverFahrten.add(bestDriverFahrt);
               }

           }catch (SQLException throwables) {
               throwables.printStackTrace();
           }

            System.out.println("Hello after bestDriverFahrten");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bestDriverFahrten;
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
                    System.out.println("hi BestDriverStore the connection with datatbase has been closed");

                }
                else {
                    connection.rollback();
                    System.out.println("hi BestDriverStore the connection with datatbase has been closed");

                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                    System.out.println("hi BestDriverStore the connection with datatbase has been closed");
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}

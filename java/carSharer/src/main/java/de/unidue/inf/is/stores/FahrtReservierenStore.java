package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.fahrerlaubnis;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FahrtReservierenStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public FahrtReservierenStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<fahrerlaubnis> getAnbieter (){
        List<fahrerlaubnis> fahrerlaubnise =new ArrayList<>();
        try (PreparedStatement preparedStatement=connection.prepareStatement("select fahrer,nummer from dbp109.fahrerlaubnis")){
            ResultSet Res=preparedStatement.executeQuery();
            while (Res.next()){
                fahrerlaubnis fahrerlaubnis= new fahrerlaubnis(Res.getInt("fahrer"),Res.getInt("nummer"));
                fahrerlaubnise.add(fahrerlaubnis);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fahrerlaubnise;
    }

    public boolean FahrtReservieren(int fid,int bid,int anzahl)  {

   /*  check if the kunde is ersteller or not  if not proceed , Ersteller kann nicht sein eigene fahrt erstellen */
       try (PreparedStatement preparedStatement0=connection.prepareStatement("select f.fid from dbp109.fahrt f where f.anbieter=? and f.fid=?")){
           preparedStatement0.setInt(1,bid);
           preparedStatement0.setInt(2,fid);
           System.out.println("1----bid: "+ bid+"/ fid: "+ fid);
           ResultSet RES0= preparedStatement0.executeQuery();
           if(!RES0.next()){

               /* check if the user"bid" already reserve this trip"fid" before.  the result will be saved in Res0 so if it empty is's mean that user didn't reserve before so I will proceed if not return false*/

               try(PreparedStatement preparedStatement1 = connection.prepareStatement("select kunde from dbp109.reservieren r where r.kunde=? and r.fahrt=?")){
                   preparedStatement1.setInt(1,bid);
                   preparedStatement1.setInt(2,fid);
                   ResultSet Res1=preparedStatement0.executeQuery();
                   if(!Res1.next()){

                       /* check if this trip"fid" is offen  if Res contains result it's mean that the conditions are fulfilled */
                       try (PreparedStatement preparedStatement2= connection.

                               prepareStatement("SELECT f.FID,f.MAXPLAETZE,r.anzPlaetze,f.STATUS from dbp109.fahrt f left JOIN (SELECT fahrt,sum(anzPlaetze)AS anzPlaetze FROM dbp109.reservieren r GROUP BY fahrt)r ON f.fid=r.fahrt where f.fid=?  AND f.status='offen'"))
                       {

                           preparedStatement2.setInt(1,fid);
                           ResultSet Res2= preparedStatement2.executeQuery();
                           connection.commit();
                           Res2.next();
                           /*check if the needed"anzahl" places equal or more than available if it return true will proceed otherwise will not*/

                           if(Res2.getInt("MAXPLAETZE")>=Res2.getInt("anzPlaetze")){

                               try (PreparedStatement preparedStatement3=connection.
                                       prepareStatement("insert into  dbp109.reservieren (kunde,fahrt,anzPlaetze) values (?,?,?)")){
                                   preparedStatement3.setInt(1,bid);
                                   preparedStatement3.setInt(2,fid);
                                   preparedStatement3.setInt(3,anzahl);
                                   preparedStatement3.executeUpdate();


                                   connection.commit();

                                   System.out.println("hello from fahert reservieren store,  insert new reserve : successed ");

                                   return true;
                               }

                           }

                       } catch (SQLException throwables) {
                           throwables.printStackTrace();
                       }
                   }
               } catch (SQLException throwables) {

                   throwables.printStackTrace();
               }
           }

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }


        System.out.println("Sie haben schon einaml reserviert , Sie konnen nicht reservieren oder Sie sind der ersteller ");
        System.out.println("hello from fahrt reservieren store,  insert new reserve : failed ");
        return false;
    }

    public boolean checkErsteller (int bid,int fid){
        try(PreparedStatement preparedStatement0=connection.prepareStatement("select * from dbp109.fahrt f where f.anbieter=? and f.fid=?")){
            preparedStatement0.setInt(1,bid);
            preparedStatement0.setInt(2,fid);
            ResultSet Res0= preparedStatement0.executeQuery();
            if(Res0.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    /**                löschen                            **/
    public boolean FahrtLoeschen(int fid,int bid){
        List<fahrerlaubnis> fahrerlaubnisse= new ArrayList<>();
        /* if the user already book the trip and rate it. it will delete the row which contains kunde,fahrt from reservieren and benutzer,fahrt from schreiben , as well as
         * is user booked the trip but didn't rate it , well delete row of kunde, fahrt from reservieren because we used left join
         * if he didn't find any row to delete will throw exception and return false
         *  */
        /* check if bid is the ersteller or not  */
    if(checkErsteller(bid,fid) ){

       try (PreparedStatement preparedStatement1=connection.
                prepareStatement("delete from dbp109.reservieren r where r.fahrt=?")) {
            preparedStatement1.setInt(1, fid);
            preparedStatement1.executeUpdate();
            System.out.println("1-the trip  deleted suc from reservieren table");

           /* check where this fahrt has entries in schreiben table if so delete it, schreiben table is child  */
           try(PreparedStatement preparedStatement2=connection.
                   prepareStatement("select * from dbp109.schreiben s where s.fahrt=?")){
               System.out.println("bid: "+bid+"/"+fid);
               preparedStatement2.setInt(1,fid);
               ResultSet Res3=preparedStatement2.executeQuery();
               System.out.println("2-the trip  has rates");

               /* delete entries  for this trip from schreiben table *******/
               if(Res3.next()){


                   try(PreparedStatement preparedStatement3=connection.
                           prepareStatement("select bewertung from old table (delete from dbp109.schreiben where fahrt=?)")){
                       preparedStatement3.setInt(1,fid);
                       ResultSet Res =preparedStatement3.executeQuery();

                       System.out.println("3-the trip  rate deleted suc from schreiben table");
                       /* delete the same entries for this trip from bewertung table */
                       while (Res.next()){

                           try (PreparedStatement preparedStatement4=connection.
                                   prepareStatement("delete from dbp109.bewertung b where b.beid=?")){
                               preparedStatement4.setInt(1,Res.getInt("bewertung"));
                               preparedStatement4.executeUpdate();
                               System.out.println("4-the trip  rate deleted suc from bewertung table");

                           } catch (SQLException throwables) {
                               throwables.printStackTrace();
                       }

                       }


                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               }

               /* delete fahrt from fahrt table */
               try (PreparedStatement preparedStatement5= connection.prepareStatement("delete from dbp109.fahrt f where f.anbieter=? and f.fid=?")){
                   preparedStatement5.setInt(1,bid);
                   preparedStatement5.setInt(2,fid);
                   System.out.println("user id="+  bid);
                   System.out.println("fahrt Id="+  fid);
                   preparedStatement5.executeUpdate();
                   connection.commit();
                   System.out.println("5-the trip  deleted suc from fahrt table");
                   return true;
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }


           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }

    }


        System.out.println("the deleting process failed, you are not the ersteller");
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
                    System.out.println("HI from Fahrt Reservieren Store line 227, I committed your changes in database");
                }
                else {
                    connection.rollback();
                    System.out.println("HI from Fahrt Reservieren Store line 231, I rolled back your changes in database");

                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                    System.out.println("HI from Reservieren Store line 241,the connection with data base has been closed");
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }

}

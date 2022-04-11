package de.unidue.inf.is;

import de.unidue.inf.is.domain.*;
import de.unidue.inf.is.stores.FahrtDetailsStore;
import de.unidue.inf.is.stores.FahrtReservierenStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FahrtDetailsServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static reservieren  neureservieren;
    public static reservieren  neureservieren2;


    /*do get responsible for show the content of fahrtdeatils page*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (FahrtDetailsStore fahrtDetailsStore=new FahrtDetailsStore()){
            int fid = 0;
            int bid = 0;
            /* get farht id and benutzer id and save them */

             // I fatch bid for aktionsliste part, and fid for fahrt details part
            System.out.println(request.getParameter("fid"));
            System.out.println(request.getParameter("bid"));
            try {
                 fid= (int)request.getAttribute("fid");
                 bid= (int)request.getAttribute("bid");
            } catch (Exception e) {
                e.printStackTrace();
            }
       if(fid !=0 && bid != 0){
           neureservieren = new reservieren(fid,bid);

           //Aktionenlist
           Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
           benutzer benutzer=fahrtDetailsStore.getBenutzer();
           //bewertung
           List<Rate> Rates=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

           // contains user's email in the same order as bewertungen
           // Durschschnittsrating for fart = fid
           float Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

           /*bewertung request**/
           request.setAttribute("average",Durschschnittrating);
           request.setAttribute("Rates",Rates);

           /*aktionliste request**/
           request.setAttribute("benutzer",benutzer);
           request.setAttribute("user", de.unidue.inf.is.domain.benutzer.getBid());
           /*Informationen request**/
           request.setAttribute("FahrtDetails",fahrt);

           /*forward all data to viewer**/
           request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
           fahrtDetailsStore.complete();
            }

         else
          {
              neureservieren = new reservieren(Integer.parseInt(request.getParameter("fid")),Integer.parseInt(request.getParameter("bid")));

              //Aktionenlist
              Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
              benutzer benutzer=fahrtDetailsStore.getBenutzer();
              //bewertung
              List<Rate> Rates=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

              // contains user's email in the same order as bewertungen
              // Durschschnittsrating for fart = fid
              float Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

              /*bewertung request**/
              request.setAttribute("average",Durschschnittrating);
              request.setAttribute("Rates",Rates );

              /*aktionliste request**/
              request.setAttribute("benutzer",benutzer);
              request.setAttribute("user", de.unidue.inf.is.domain.benutzer.getBid());
              /*Informationen request**/
              request.setAttribute("FahrtDetails",fahrt);

              /*forward all data to viewer**/
              request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
              fahrtDetailsStore.complete();
          }

        }
    }






    /*do post responsible for getting the attributes from fahrtdetails page and handling the (reservieren/loeschen) processes */
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        FahrtReservierenStore fahrtReservierenStore= new FahrtReservierenStore();


        /* test if view send valid data */
     if(request.getParameter("Anzahl") !=null  ){
             if(request.getParameter("action").equals("reservieren")){
                 /*
                  * set anzahl to object neureservieren
                  * **/

                 neureservieren.setAnzPlaetze(Integer.parseInt(request.getParameter("Anzahl")));


                 if(fahrtReservierenStore.FahrtReservieren(neureservieren.getFahrt(),neureservieren.getKunde(),neureservieren.getAnzPlaetze())){

                     try (FahrtDetailsStore fahrtDetailsStore=new FahrtDetailsStore()){
                         //Aktionenlist
                         Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
                         benutzer benutzer=fahrtDetailsStore.getBenutzer();
                         //bewertung
                         List<Rate> Rates=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

                         // contains user's email in the same order as bewertungen
                         // Durschschnittsrating for fart = fid
                         float Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

                         /*bewertung request**/
                         request.setAttribute("average",Durschschnittrating);
                         request.setAttribute("Rates",Rates);

                         /*aktionliste request**/
                         request.setAttribute("benutzer",benutzer);
                         request.setAttribute("user", de.unidue.inf.is.domain.benutzer.getBid());

                         /*Informationen request**/
                         request.setAttribute("FahrtDetails",fahrt);

                         /*forward all data to viewer**/
                         request.setAttribute("message","Erfolgreich");
                         request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
                         fahrtDetailsStore.complete();

                     }

                 }

                 else{
                     try (FahrtDetailsStore fahrtDetailsStore=new FahrtDetailsStore()){

                         //Aktionenlist
                         Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
                         benutzer benutzer=fahrtDetailsStore.getBenutzer();
                         //bewertung
                         List<Rate> Rates=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

                         // contains user's email in the same order as bewertungen
                         // Durschschnittsrating for fart = fid
                         float Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

                         /*bewertung request**/
                         request.setAttribute("average",Durschschnittrating);
                         request.setAttribute("Rates",Rates);

                         /*aktionliste request**/
                         request.setAttribute("benutzer",benutzer);
                         request.setAttribute("user", de.unidue.inf.is.domain.benutzer.getBid());

                         /*Informationen request**/
                         request.setAttribute("FahrtDetails",fahrt);

                         /*forward all data to viewer**/
                         request.setAttribute("message","fehlgeschlagen,Sie haben schon einmal reserviert oder Sie sind der ersteller der fahrt");
                         request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
                         fahrtReservierenStore.complete();

                     }
                 }
             }

     }



    else if(request.getParameter("action").equals("loeschen")){
        List<fahrerlaubnis> fahrerlaubnisse= new ArrayList<>();
        /*
         * get fahrt id and benutzer id and anzahl von plaetze and save it
         * **/

        if(fahrtReservierenStore.FahrtLoeschen(neureservieren.getFahrt(),neureservieren.getKunde())){
            try {
                HauptSeiteServlet hauptSeiteServlet =new HauptSeiteServlet();
                fahrtReservierenStore.complete();
                fahrtReservierenStore.close();
                hauptSeiteServlet.doGet(request,response);


            } catch (ServletException e) {
                e.printStackTrace(); }

        }
        else
            try {
                HauptSeiteServlet hauptSeiteServlet =new HauptSeiteServlet();
                request.setAttribute("message", "fehlgeschlagen, Sie können diese Fahrt nicht löschen, Sie sind nicht der Ersteller");
                fahrtReservierenStore.close();
                hauptSeiteServlet.doGet(request,response);

            } catch (ServletException e) {
                e.printStackTrace(); }
    }

    else
    {
        doGet(request,response);

    }


    }
}

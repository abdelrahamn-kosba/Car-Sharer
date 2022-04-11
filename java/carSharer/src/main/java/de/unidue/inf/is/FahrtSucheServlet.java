package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.stores.FahrtSucheStore;
import de.unidue.inf.is.utils.DateTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FahrtSucheServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<Fahrt> fahrten = Collections.emptyList();
        request.setAttribute("fahrten",fahrten);
        request.getRequestDispatcher("FahrtSuche.ftl").forward(request,response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startOrt= request.getParameter("start");
        String zielOrt= request.getParameter("ziel");
        String ab= request.getParameter("ab");

        /*   test if the view send valid data ! */
       if(startOrt.length() !=0 && zielOrt.length() !=0 && ab.length() !=0){
           /* Convert Date ****************/
           DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
           DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
           Date date = null;
           try {
               date = inputFormat.parse(ab);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           String outputText = outputFormat.format(date);
           String DateTime = DateTimeUtil.convertDateAndTimeToDB2DateTime(outputText,"00:00");



           FahrtSucheStore fahrtSucheStore=new FahrtSucheStore();
           List<Fahrt> fahrten= new ArrayList<>();
           fahrten= fahrtSucheStore.FahrtSuche(startOrt,zielOrt,DateTime);

           if(fahrten.size() != 0){

               request.setAttribute("fahrten",fahrten);
               request.setAttribute("user", benutzer.getBid());
               fahrtSucheStore.complete();
               request.getRequestDispatcher("FahrtSuche.ftl").forward(request,response);
               fahrtSucheStore.close();
           } else{
               System.out.println("I am here in servlet");
               request.setAttribute("fahrten",fahrten);
               request.setAttribute("message","there no trips");
               fahrtSucheStore.complete();
               request.getRequestDispatcher("FahrtSuche.ftl").forward(request,response);
               fahrtSucheStore.close();

           }
       }else {
           request.setAttribute("message","Please Enter valid data");
           request.getRequestDispatcher("FahrtSuche.ftl").forward(request,response);       }

    }

}


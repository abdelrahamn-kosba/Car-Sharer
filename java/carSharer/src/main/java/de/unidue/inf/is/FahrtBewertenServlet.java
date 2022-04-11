package de.unidue.inf.is;

import de.unidue.inf.is.domain.schreiben;
import de.unidue.inf.is.stores.FahrtBewrtenStore;
import de.unidue.inf.is.utils.DateTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FahrtBewertenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public  static schreiben schreiben;

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        schreiben =new schreiben( Integer.parseInt (request.getParameter("bid")),Integer.parseInt(request.getParameter("fid")));
        System.out.println(schreiben.getFahrt());
        System.out.println(schreiben.getBenutzer());

        request.getRequestDispatcher("FahrtBewerten.ftl").forward(request,response);
    }


    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)throws IOException {
        // import time now and initialize your attributes
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");
        String bewertdate = simpleDateFormat.format(date);
        System.out.println("bewert date"+bewertdate);
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("HH:mm");
        String berwerttime= simpleDateFormat2.format(date);
        String erstllungsdatum= DateTimeUtil.convertDateAndTimeToDB2DateTime(bewertdate,berwerttime);

        String Beschreibung= request.getParameter("Beschreibung");
        int Bewertungsrating=Integer.parseInt(request.getParameter("Bewertungsrating"));

        /* test if viewer send valid data */
        if(Beschreibung.length() !=0 &&  (Bewertungsrating >=1 && Bewertungsrating <=5)){
            if(request.getParameter("action").equals("bewert")){

                FahrtBewrtenStore fahrtBewrtenStore=new FahrtBewrtenStore();


                try {
                    if(fahrtBewrtenStore.FahrtBewerten(schreiben.getBenutzer(), schreiben.getFahrt(), Beschreibung, erstllungsdatum, Bewertungsrating)){
                        try {
                            //set request attributes

                            // Dispatch request to template engine

                            request.setAttribute("bid",schreiben.getBenutzer());
                            request.setAttribute("fid",schreiben.getFahrt());

                            int bid= (int)request.getAttribute("bid");
                            System.out.println("bid after casting"+bid);
                            System.out.println("bid"+schreiben.getBenutzer());
                            System.out.println("fid"+schreiben.getFahrt());
                            fahrtBewrtenStore.complete();
                            fahrtBewrtenStore.close();
                            request.getRequestDispatcher("FahrtDetails").forward(request, response);
                        } catch (ServletException | IOException e) {
                            e.printStackTrace();
                        }
                        // else means there is user in data base with the same data
                    }
                    else {

                        try {
                            //set request attributes

                            request.setAttribute("message", "Der Bewertungsprozess ist fehlgeschlagen,Sie haben schon einmal bewertet oder Sie sind der ersteller der fahrt");
                            // Dispatch request to template engine
                            request.getRequestDispatcher("FahrtBewerten.ftl").forward(request, response);
                            fahrtBewrtenStore.close();
                        } catch (ServletException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else{

            try {
                request.setAttribute("message", "Please Enter valid Data");
                // Dispatch request to template engine
                request.getRequestDispatcher("FahrtBewerten.ftl").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }
}




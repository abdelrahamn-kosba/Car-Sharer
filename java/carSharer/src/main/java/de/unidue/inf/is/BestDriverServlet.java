package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.stores.BestDriverStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BestDriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        BestDriverStore bestDriverStore=new BestDriverStore();
        /* get data from store */
        List<Fahrt> fahrts = bestDriverStore.BestDriverData();

        String benutzers= bestDriverStore.getBestDriverEmail();
        System.out.println("1- hi from store:  "+benutzers);
        float average= bestDriverStore.getAverage();
        System.out.println("average:  "+average);

        /* send data to viewer  */

        request.setAttribute("fahrts",fahrts);
        request.setAttribute("average",average);
        request.setAttribute("user",benutzer.getBid());
        request.setAttribute("benutzers",benutzers);
        System.out.println(benutzers);
        bestDriverStore.complete();
        request.getRequestDispatcher("BestDriver.ftl").forward(request,response);
        bestDriverStore.close();
    }


}

// view request.getAttr (post)
//view call store mien
// MVC  VIEWER
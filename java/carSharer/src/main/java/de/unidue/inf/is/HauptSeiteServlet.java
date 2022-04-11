//Controller
package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.domain.transportmittel;
import de.unidue.inf.is.stores.HauptSeiteStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class HauptSeiteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // give a user



        try (HauptSeiteStore hauptSeiteStore=new HauptSeiteStore()){

            // get response from controller and save it in list
            List<Fahrt>MeineReservfahrte= HauptSeiteStore.getInstance().getMeineReservFahrten(benutzer.getBid());
            List<Fahrt>offeneFahrten = HauptSeiteStore.getInstance().getOffeneFahrten();
            List<transportmittel> transportmittels=HauptSeiteStore.getInstance().getTransportmittels();


            /******** send meine reservierte fahrten to viewer *******/
            request.setAttribute("benutzer", benutzer.getBid());
            request.setAttribute("ReservFahrten", MeineReservfahrte);
            request.setAttribute("offeneFahrten",offeneFahrten);
            request.setAttribute("transportmittels",transportmittels);
            hauptSeiteStore.complete();
            hauptSeiteStore.close();

            request.getRequestDispatcher("HauptSeite.ftl").forward(request, response);

        }

    }
}


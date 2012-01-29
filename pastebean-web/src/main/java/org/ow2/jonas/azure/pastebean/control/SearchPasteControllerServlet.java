package org.ow2.jonas.azure.pastebean.control;

import org.ow2.jonas.azure.pastebean.model.Paste;
import org.ow2.jonas.azure.pastebean.service.PasteService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 29/01/12
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class SearchPasteControllerServlet extends HttpServlet {

    @EJB
    private PasteService pasteService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        String token = request.getParameter("token");

        List<Paste> matches = pasteService.searchPastes(token);

        request.setAttribute("matches", matches);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jspx");
        dispatcher.forward(request, response);
    }
}

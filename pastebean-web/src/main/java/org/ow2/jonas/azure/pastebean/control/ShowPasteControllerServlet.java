package org.ow2.jonas.azure.pastebean.control;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ow2.jonas.azure.pastebean.model.Paste;
import org.ow2.jonas.azure.pastebean.service.PasteService;

/**
 * A {@code ${NAME}} is ...
 *
 * @author Guillaume Sauthier
 */
public class ShowPasteControllerServlet extends HttpServlet {

    @EJB
    private PasteService pasteService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Remove the leading '/'
        String hashFragment = request.getPathInfo().substring(1);

        List<Paste> pastes = pasteService.findMatchingPastes(hashFragment);
        
        if (pastes.isEmpty()) {
            // forward to error page
            System.out.println("Empty result: Forward to error page");
            response.sendError(500, "No pastes found for hash: " + hashFragment);
        } else if (pastes.size() > 1) {
            // forward to error page
            System.out.println("Multiple matches: Forward to error page");
            response.sendError(500, "Too many pastes (" + pastes.size() + ") found for hash: " + hashFragment);
        } else {
            // Only 1 match
            request.setAttribute("paste", pastes.get(0));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/paste.jspx");
            dispatcher.forward(request, response);
        }
    }


}

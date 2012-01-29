package org.ow2.jonas.azure.pastebean.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ow2.jonas.azure.pastebean.model.Paste;
import org.ow2.jonas.azure.pastebean.service.PasteService;

/**
 * A {@code ${NAME}} is ...
 *
 * @author Guillaume Sauthier
 */
public class CreatePasteControllerServlet extends HttpServlet {
    
    @EJB
    private PasteService pasteService;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String description = request.getParameter("desc");
        String content = request.getParameter("content");

        Paste paste = pasteService.createPaste(name, author, description, content);
        
        addInSession(request.getSession(), paste);

        response.sendRedirect(getUrl(paste));
    }

    private void addInSession(HttpSession session, Paste paste) {
        Object o = session.getAttribute("myPastes");
        List<String> pastes;
        if (o == null) {
            pastes = new ArrayList<String>();
            session.setAttribute("myPastes", pastes);
        } else {
            if (o instanceof List) {
                pastes = (List<String>) o;
            } else {
                pastes = new ArrayList<String>();
                session.setAttribute("myPastes", pastes);
            }
        }

        pastes.add(paste.getHash());
        
    }

    private String getUrl(Paste paste) {
        return "p/" + paste.getHash().substring(0, 8);
    }
}

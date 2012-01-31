/**
 * Copyright 2012 Bull S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

        String title = request.getParameter("title");
        if (title == null || "".equals(title)) {
            title = "Untitled";
        }
        String author = request.getParameter("author");
        String description = request.getParameter("desc");
        String content = request.getParameter("content");

        Paste paste = pasteService.createPaste(title, author, description, content);
        
        addInSession(request.getSession(), paste);

        response.sendRedirect(getUrl(paste));
    }

    private void addInSession(HttpSession session, Paste paste) {
        Object o = session.getAttribute("myPastes");
        List<Paste> pastes;
        if (o == null) {
            pastes = new ArrayList<Paste>();
            session.setAttribute("myPastes", pastes);
        } else {
            if (o instanceof List) {
                pastes = (List<Paste>) o;
            } else {
                pastes = new ArrayList<Paste>();
                session.setAttribute("myPastes", pastes);
            }
        }

        pastes.add(paste);
        
    }

    private String getUrl(Paste paste) {
        return "p/" + paste.getHash().substring(0, 8);
    }
}

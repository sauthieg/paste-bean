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
            Paste paste = pastes.get(0);
            String content = paste.getContent()
                                  .replace("<", "&lt;");
            paste.setContent(content);
            request.setAttribute("paste", paste);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/paste.jspx");
            dispatcher.forward(request, response);
        }
    }


}

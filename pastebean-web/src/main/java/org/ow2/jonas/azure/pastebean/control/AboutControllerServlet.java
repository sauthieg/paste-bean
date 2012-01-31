package org.ow2.jonas.azure.pastebean.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.windowsazure.serviceruntime.Role;
import com.microsoft.windowsazure.serviceruntime.RoleEnvironment;
import com.microsoft.windowsazure.serviceruntime.RoleInstance;
import com.microsoft.windowsazure.serviceruntime.RoleInstanceEndpoint;

/**
 * A {@code ${NAME}} is ...
 *
 * @author Guillaume Sauthier
 */
public class AboutControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("deploymentId", RoleEnvironment.getDeploymentId());
        request.setAttribute("roles", RoleEnvironment.getRoles().values());
        request.setAttribute("currentInstance", RoleEnvironment.getCurrentRoleInstance());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/about.jspx");
        dispatcher.forward(request, response);

    }
}

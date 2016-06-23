package by.bsu.cinemarating.servlet;

import by.bsu.cinemarating.command.ActionCommand;
import by.bsu.cinemarating.command.ChangeLanguageCommand;
import by.bsu.cinemarating.command.factory.ActionFactory;
import by.bsu.cinemarating.database.ConnectionPool;
import by.bsu.cinemarating.memento.Caretaker;
import by.bsu.cinemarating.memento.MementoRequest;
import by.bsu.cinemarating.resource.ConfigurationManager;
import by.bsu.cinemarating.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String page = command.execute(request);
        if (page != null) {
            ////////////////////////////////////////////todo
            MementoRequest memento = (MementoRequest) request.getSession().getAttribute("memento");
            if (memento == null) {
                memento = new MementoRequest();
            }
            Caretaker caretaker = new Caretaker(memento);
            if (command.getClass() != ChangeLanguageCommand.class) {
                caretaker.extract(request);
                request.getSession().setAttribute("memento", memento);
            } else {
                caretaker.fill(request);
            }
            ////////////////////////////////////////////
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            log.error("Page is null.");
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closePool(); //todo
    }
}

package by.bsu.cinemarating.command;

import by.bsu.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 28.03.16
 * Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LOCALE, language);
        String page = ConfigurationManager.getProperty((String) request.getSession().getAttribute(PAGE));
        return page;
    }
}

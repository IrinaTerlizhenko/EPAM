package by.bsu.cinemarating.command;

import by.bsu.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LOCALE, language);
        String page = ConfigurationManager.getProperty((String) request.getSession().getAttribute(PAGE));
        return page;
    }
}

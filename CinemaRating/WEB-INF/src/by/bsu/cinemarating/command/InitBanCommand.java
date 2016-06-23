package by.bsu.cinemarating.command;

import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.logic.UserLogic;
import by.bsu.cinemarating.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 30.05.2016.
 */
public class InitBanCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(InitBanCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt(request.getParameter(ID));
        try {
            User user = UserLogic.findUser(id).get();
            request.setAttribute(USER, user);
            page = ConfigurationManager.getProperty("path.page.ban");
        } catch (LogicException e) {
            log.error(e);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}

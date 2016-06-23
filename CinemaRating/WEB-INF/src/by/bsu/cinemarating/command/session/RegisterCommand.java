package by.bsu.cinemarating.command.session;

import by.bsu.cinemarating.command.ActionCommand;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.logic.RegisterLogic;
import by.bsu.cinemarating.resource.ConfigurationManager;
import by.bsu.cinemarating.resource.MessageManager;
import by.bsu.cinemarating.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 18.04.16
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class RegisterCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);
        String email = request.getParameter(EMAIL);
        User user = new User();
        try {
            ValidationResult result = RegisterLogic.register(login, password, repeatPassword, email, user);
            switch (result) {
                case ALL_RIGHT:
                    request.getSession().setAttribute(USER_ID, user.getId());
                    request.getSession().setAttribute(ROLE, user.getRoleID());
                    page = ConfigurationManager.getProperty("path.page.main");
                    break;
                case LOGIN_PASS_INCORRECT:
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                    break;
                case EMAIL_INCORRECT:
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.emailerror"));
                    break;
                case PASS_NOT_MATCH:
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.passnotmatch"));
                    break;
                case LOGIN_NOT_UNIQUE:
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginduplicate"));
                    break;
                case EMAIL_NOT_UNIQUE:
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.emailduplicate"));
                    break;
                case UNKNOWN_ERROR:
                    request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.unknown"));
                    break;
            }
            if (result != ValidationResult.ALL_RIGHT) {
                request.setAttribute(LOGIN, login);
                request.setAttribute(PASSWORD, password);
                request.setAttribute(REPEAT_PASSWORD, repeatPassword);
                request.setAttribute(EMAIL, email);
                page = ConfigurationManager.getProperty("path.page.register");
            }
        } catch (LogicException e) {
            log.error(e);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}

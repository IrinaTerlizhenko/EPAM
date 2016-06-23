package by.bsu.cinemarating.command.profile;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 23.04.16
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class ProfileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ProfileExecutor.executeProfile(request);
    }
}

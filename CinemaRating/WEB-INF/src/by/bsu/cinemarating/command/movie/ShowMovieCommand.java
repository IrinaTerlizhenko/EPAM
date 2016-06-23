package by.bsu.cinemarating.command.movie;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 24.04.16
 * Time: 9:14
 * To change this template use File | Settings | File Templates.
 */
public class ShowMovieCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return MovieExecutor.executeShowMovie(request);
    }
}

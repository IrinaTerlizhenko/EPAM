package by.bsu.cinemarating.command.movie;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 23.04.16
 * Time: 7:55
 * To change this template use File | Settings | File Templates.
 */
public class TopMoviesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(NUM_PAGE, 0);
        return MovieExecutor.executeTopMovies(request);
    }
}

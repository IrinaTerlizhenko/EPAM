package by.bsu.cinemarating.command.movie;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.04.16
 * Time: 5:03
 * To change this template use File | Settings | File Templates.
 */
public class AllMoviesCommand implements ActionCommand { // todo
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(NUM_PAGE, 0);
        /*SessionRequestContent content = new SessionRequestContent(request);
        String page = MovieExecutor.executeAllMovies(content);
        content.insertValues(request);*/
        return MovieExecutor.executeAllMovies(request)/*page*/;
    }
}

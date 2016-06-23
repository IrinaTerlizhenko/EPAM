package by.bsu.cinemarating.command;

import by.bsu.cinemarating.entity.Movie;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.logic.MovieLogic;
import by.bsu.cinemarating.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 05.05.2016.
 */
public class RedirectCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RedirectCommand.class);

    private static final String PAGE_MAIN = "path.page.main";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String nextPage = request.getParameter(NEXT_PAGE);
        try {
            if (PAGE_MAIN.equals(nextPage)) {
                List<Movie> list = MovieLogic.takeLatestAddedMovies(5);
                request.setAttribute(MOVIES, list);
            } // todo all movies, top movies, ...
            page = ConfigurationManager.getProperty(nextPage);
        } catch (LogicException e) {
            log.error(e);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}

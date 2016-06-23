package by.bsu.cinemarating.command;

import by.bsu.cinemarating.command.movie.MovieExecutor;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.logic.RatingLogic;
import by.bsu.cinemarating.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 23.04.16
 * Time: 9:09
 * To change this template use File | Settings | File Templates.
 */
public class RateCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RateCommand.class);

    @Override
    public String execute(HttpServletRequest request) { // todo return to current_movie page
        String page;
        try {
            int movieId = Integer.parseInt(request.getParameter(MOVIE_ID));
            int userId = (int) request.getSession().getAttribute(USER_ID);
            byte newRating = Byte.parseByte(request.getParameter(RATING));
            String newReview = request.getParameter(REVIEW);
            RatingLogic.addRating(userId, movieId, newRating, newReview);
            MovieExecutor.executeShowMovie(request);
            page = ConfigurationManager.getProperty("path.page.current_movie");
        } catch (SQLException | LogicException e) {
            log.error(e);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}

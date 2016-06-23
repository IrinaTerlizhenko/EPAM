package by.bsu.cinemarating.command.movie;

import by.bsu.cinemarating.command.ActionCommand;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.logic.MovieLogic;
import by.bsu.cinemarating.resource.ConfigurationManager;
import by.bsu.cinemarating.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by User on 24.05.2016.
 */
public class AddMovieCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            String movieName = request.getParameter(MOVIE_NAME);
            String description = request.getParameter(DESCRIPTION);
            int year = Integer.parseInt(request.getParameter(YEAR));
            String country = request.getParameter(COUNTRY);
            Part filePart = request.getPart(PICTURE);
            String path = request.getServletContext().getRealPath(request.getServletPath()); // todo
            ValidationResult result = MovieLogic.addMovie(movieName, description, year, country, filePart, path);
            if (ValidationResult.ALL_RIGHT.equals(result)) {
                request.getSession().setAttribute(PAGE_TYPE, ALL);
                MovieExecutor.executeAllMovies(request);
                page = ConfigurationManager.getProperty("path.page.movies");
            } else {
                page = ConfigurationManager.getProperty("path.page.add_movie");
            }
        } catch (NumberFormatException | IOException | ServletException | LogicException e) {
            log.error(e);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}

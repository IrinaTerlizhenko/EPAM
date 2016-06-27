package by.bsu.cinemarating.command.movie;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ShowMovieCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return MovieExecutor.executeShowMovie(request);
    }
}

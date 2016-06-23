package by.bsu.cinemarating.command.profile;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 23.06.2016.
 */
public class ReviewsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ProfileExecutor.executeReviews(request);
    }
}

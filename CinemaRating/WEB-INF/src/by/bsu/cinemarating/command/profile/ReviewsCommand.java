package by.bsu.cinemarating.command.profile;

import by.bsu.cinemarating.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ReviewsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ProfileExecutor.executeReviews(request);
    }
}

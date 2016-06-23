package by.bsu.cinemarating.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    /**
     * @param request request from client side
     * @return URI of the page to display on client side
     */
    String execute(HttpServletRequest request);

    String MOVIE_NAME = "movie_name";
    String DESCRIPTION = "description";
    String YEAR = "year";
    String COUNTRY = "country";
    String MOVIES = "movies";
    String PAGE_TYPE = "page_type";
    String ALL = "all";
    String MOVIE_ID = "movie_id";
    String DELETED = "deleted";
    String REVIEWS = "reviews";
    String MOVIE = "movie";
    String PAGE = "page";
    String TOP = "top";
    String USER_ID = "user_id";
    String PROFILE = "profile";
    String ERROR = "error";
    String NAME = "name";
    String SURNAME = "surname";
    String EMAIL = "email";
    String LOGIN = "login";
    String PASSWORD = "password";
    String ROLE = "role";
    String REPEAT_PASSWORD = "repeat-password";
    String ID = "id";
    String TYPE = "type";
    String REASON = "reason";
    String USER = "user";
    String LANGUAGE = "language";
    String LOCALE = "locale";
    String RATING = "rating";
    String BANNED = "banned";
    String REVIEW = "review";
    String NEXT_PAGE = "nextPage";
    String NUM_PAGE = "num_page";
    String PICTURE = "picture";
    String USERS = "users";
    String RATING_MAP = "rating_map";
    String REVIEW_MAP = "review_map";
}

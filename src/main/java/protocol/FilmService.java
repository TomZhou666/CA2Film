package protocol;

public class FilmService {
    //Local information
    public static final String HOST = "localhost";
    public static final int PORT = 41235;

    //Delimiter
    public static final String DELIMITER = "%%";
    public static final String GENRE_DELIMITER = "~~";

    //Requests
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String RATE = "rate";
    public static final String SEARCH = "search";
    public static final String TITLE = "title";
    public static final String GENRE = "genre";
    public static final String SEARCH_BY_NAME = "searchByName";
    public static final String SEARCH_BY_GENRE = "searchByGenre";
    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String EXIT = "exit";
    public static final String SHUTDOWN = "shutdown";
    public static final String PREVIOUS = "previous";

    //Responses
    public static final String ADDED = "ADDED";
    public static final String REMOVED = "REMOVED";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String REJECTED = "REJECTED";
    public static final String SUCCESS_ADMIN = "SUCCESS_ADMIN";
    public static final String SUCCESS_USER = "SUCCESS_USER";
    public static final String LOGGED_OUT = "LOGGED_OUT";
    public static final String FAILED = "FAILED";
    public static final String SUCCESS = "SUCCESS";
    public static final String INVALID_RATING_SUPPLIED = "INVALID_RATING_SUPPLIED";
    public static final String NOT_LOGGED_IN = "NOT_LOGGED_IN";
    public static final String NOT_MATCH_FOUND = "NOT_MATCH_FOUND";
    public static final String EXISTS = "EXISTS";
    public static final String INSUFFICIENT_PERMISSIONS = "INSUFFICIENT_PERMISSIONS";
    public static final String SHUT_DOWN = "SHUT_DOWN";
    public static final String INVALID_REQUEST = "INVALID_REQUEST";
    public static final String GOODBYE = "GOODBYE";
}

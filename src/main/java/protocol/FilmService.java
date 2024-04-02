package protocol;

public class FilmService {
    //Local information
    public static final String HOST = "localhost";
    public static final int PORT = 41235;

    //Delimiter
    public static final String DELIMITER = "%%";

    //Requests
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String RATE = "rate";
    public static final String SEARCH_BY_NAME = "searchByName";
    public static final String SEARCH_BY_GENRE = "searchByGenre";
    public static final String EXIT = "GOODBYE";
    public static final String SHUTDOWN = "shutdown";
    public static final String REMOVE = "REMOVE";

    //Responses
    public static final String ADD = "ADD";
    public static final String ADDED = "ADDED";
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
    public static final String SHOW_DOWN = "SHOW_DOWN";
    public static final String INVALID_REQUEST = "INVALID_REQUEST";
}

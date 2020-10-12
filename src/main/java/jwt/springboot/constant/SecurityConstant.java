package jwt.springboot.constant;

public class SecurityConstant {

    public static final String SECRET_KEY = "*sc#8st";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 86400000; // One day
}

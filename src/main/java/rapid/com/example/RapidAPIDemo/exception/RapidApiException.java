package rapid.com.example.RapidAPIDemo.exception;

public class RapidApiException extends Exception{

    private final int httpStatusCode;

    public RapidApiException(String message, int httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

}

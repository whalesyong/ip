public class DuncanException extends Exception {
    private final ErrorCode errorCode;

    public DuncanException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessage(args)); // Format the message with args
        this.errorCode = errorCode;
    }



    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage(ErrorCode errorCode) {
        return errorCode.getMessage();
    }
}

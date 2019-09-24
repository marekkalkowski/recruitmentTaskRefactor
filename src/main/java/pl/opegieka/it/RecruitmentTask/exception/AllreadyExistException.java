package pl.opegieka.it.RecruitmentTask.exception;

public class AllreadyExistException extends RuntimeException {

    public AllreadyExistException() {
    }

    public AllreadyExistException(String message) {
        super(message);
    }

    public AllreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllreadyExistException(Throwable cause) {
        super(cause);
    }

    public AllreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

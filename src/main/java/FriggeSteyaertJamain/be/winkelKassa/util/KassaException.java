package FriggeSteyaertJamain.be.winkelKassa.util;

public class KassaException extends RuntimeException{
    public KassaException(String msg, Exception innerException) {
        super(msg, innerException);
    }

    public KassaException(String msg) {
        super(msg);
    }

}


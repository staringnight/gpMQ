package org.edl.gpmq.socket.protocol;

/**
 * Created by yks on 2017/8/17.
 */
public class NotHeadLengthObjectMessageException extends Exception {
    public NotHeadLengthObjectMessageException(String msg)
    {
        super(msg);
    }
}

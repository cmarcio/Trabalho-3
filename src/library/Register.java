package library;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Marcio on 01/06/2015.
 */
public class Register {
    private GregorianCalendar calendar;
    private Long userId;
    private Long bookId;

    public Register (GregorianCalendar calendar, Long userId, Long bookId) {
        this.calendar = calendar;
        this.userId = userId;
        this.bookId = bookId;
    }
}

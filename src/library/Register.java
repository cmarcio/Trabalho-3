package library;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Marcio on 01/06/2015.
 */
public class Register implements Comparable<Register>{
    private GregorianCalendar calendar;
    private Long userId;
    private Long bookId;

    public Register (GregorianCalendar calendar, Long bookId, Long userId) {
        this.calendar = calendar;
        this.userId = userId;
        this.bookId = bookId;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public int compareTo(Register x) {
        return this.getCalendar().compareTo(x.getCalendar());
    }
}
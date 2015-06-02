package library;

import java.util.GregorianCalendar;

/**
 * Created by Marcio on 02/06/2015.
 */
public class BorrowRegister extends Register {
    public GregorianCalendar MaxReturnDate;
    public BorrowRegister(GregorianCalendar calendar, Long bookId, Long userId) {
        super(calendar, bookId, userId);
    }

    public GregorianCalendar getMaxReturnDate() {
        return MaxReturnDate;
    }

    public void setMaxReturnDate(GregorianCalendar maxReturnDate) {
        MaxReturnDate = maxReturnDate;
    }
}

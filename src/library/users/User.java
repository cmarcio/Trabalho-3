package library.users;

import gui.MainWindowController;
import library.BorrowFile;
import library.BorrowRegister;
import library.Register;
import library.ReturnFile;
import library.books.Book;
import library.books.BooksFile;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marcio on 24/05/2015.
 */
public abstract class User {
    private String firstName;
    private String lastName;
    private String email;
    private long userId;
    private boolean blocked;
    private GregorianCalendar unblockDate;
    public Book[] books;
    private String group;

    public User(String firstName, String lastName, String email, long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.blocked = false;
        this.unblockDate = null;
    }

    public abstract String getGroup();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        for (int i = 0; i < books.length; i++) {
            // Verifica se o livro está atrasado
            if (books[i] != null && books[i].getReturnDate().compareTo(MainWindowController.getProgramDate()) < 0)
                setBlocked(true);
        }
        return blocked;
    }

    public boolean isBlocked(BorrowFile borrowFile, ReturnFile returnFile) {
        BooksFile booksFile = new BooksFile("BooksReg.txt");
        this.setBlocked(false);

        // Cria as listas de empréstimos e devoluções
        ArrayList<BorrowRegister> borrowsReg = borrowFile.toBorrowRegisters();
        ArrayList<Register> returnsReg = returnFile.toRegisters();

        // Verifica se algum livro já foi emprestado
        if (borrowsReg.isEmpty())
            return false;

        // Ordena as listas
        Collections.sort(borrowsReg);
        Collections.sort(returnsReg);

        // Verfica a disponibilidade do livro
        for (int i = 0; i < borrowsReg.size(); i++) {
            BorrowRegister borrow = borrowsReg.get(i);

            // Coloca o livro na lista do usuário com a data de empréstimo e data de devolução
            Book lastBook = booksFile.searchID(Long.toString(borrow.getBookId()));
            lastBook.setBorrowDate(borrow.getCalendar());
            lastBook.setReturnDate(borrow.getMaxReturnDate());
            this.addBook(lastBook);

            // Se o numero do usuário e o número do livro são iguais, e a data de empréstimo é anterior a data atual
            if (borrow.getUserId().equals(this.getUserId()) && borrow.getCalendar().compareTo(MainWindowController.getProgramDate()) <= 0) {
                // Procura se foi devolvido
                for (int j = 0; j < returnsReg.size(); j++) {
                    Register realReturn = returnsReg.get(j);
                    // Se o livro foi devolvido antes da data atual
                    if (realReturn.getUserId().equals(this.getUserId()) && realReturn.getBookId().equals(borrow.getBookId())
                            && realReturn.getCalendar().compareTo(borrow.getCalendar()) >= 0 && realReturn.getCalendar().compareTo(MainWindowController.getProgramDate()) <= 0) {
                        // Verifica se foi entregue atrasado e registra data de bloqueio
                        lastBook.isLate(realReturn.getCalendar(), this);
                        // Retira o livro da lista
                        this.removeBook(lastBook);
                        break;
                    }
                }
            }
        }

        return isBlocked();
    }

    public void setUnblockDate(GregorianCalendar unblockDate) {
        this.unblockDate = unblockDate;
    }

    public GregorianCalendar getUnblockDate() {
        return unblockDate;
    }

    public void updateUnblockDate(GregorianCalendar newDate) {
        // Se ele está bloqueado
        if (newDate.compareTo(MainWindowController.getProgramDate()) >= 0) {
            if (!this.isBlocked()) {
                setUnblockDate(newDate);
                setBlocked(true);
            } else {
                if (getUnblockDate().compareTo(newDate) < 0)
                    setUnblockDate(newDate);
            }
        }
    }

    public abstract int getBorrowTime();

    public boolean addBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                return true;
            }
        }
        return false;
    }

    public boolean removeBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getBookNumber() == book.getBookNumber()) {
                books[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean canBorrow() {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null)
                return true;
        }
        return false;
    }
}

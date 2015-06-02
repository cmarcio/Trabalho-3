package library.users;

import gui.MainWindowController;
import library.BorrowFile;
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
        /*for (int i = 0; i < books.length; i++) {
            // Verifica se o livro est� atrasado
            if (books[i] != null && (books[i].getBorrowDate().getTimeInMillis() + (getBorrowTime() * (1000 * 60 * 60 * 24) )) < MainWindowController.getProgramDate().getTimeInMillis() ) {
                setBlocked(true);
                // Calcula o n�mero de dias atrasados
                Long loan = MainWindowController.getProgramDate().getTimeInMillis() - (books[i].getBorrowDate().getTimeInMillis() + (getBorrowTime() * (1000 * 60 * 60 * 24) ));
                int days = (int) (loan / (1000 * 60 * 60 * 24));

                GregorianCalendar date = (GregorianCalendar) MainWindowController.getProgramDate().clone();
                date.add(Calendar.DATE, days);
                setUnblockDate(date);
            }
        }*/
        return blocked;
    }

    public boolean isBlocked(BorrowFile borrowFile, ReturnFile returnFile) {
        BooksFile booksFile = new BooksFile("BooksReg.txt");
        this.setBlocked(false);

        // Cria as listas de empr�stimos e devolu��es
        ArrayList<Register> borrowsReg = borrowFile.toRegisters();
        ArrayList<Register> returnsReg = returnFile.toRegisters();

        // Verifica se algum livro j� foi emprestado
        if (borrowsReg.isEmpty())
            return false;

        // Ordena as listas
        Collections.sort(borrowsReg);
        Collections.sort(returnsReg);

        // Verfica a disponibilidade do livro
        for (int i = 0; i < borrowsReg.size(); i++) {
            // Se o numero do usu�rio e o n�mero do livro s�o iguais, e a data de empr�stimo � anterior a data atual
            if (borrowsReg.get(i).getUserId().equals(this.getUserId()) && borrowsReg.get(i).getCalendar().compareTo(MainWindowController.getProgramDate()) <= 0) {
                // Coloca o livro na lista do usu�rio com a data de empr�stimo
                Book lastBook = booksFile.searchID(Long.toString(borrowsReg.get(i).getBookId()));
                lastBook.setBorrowDate(borrowsReg.get(i).getCalendar());
                this.addBook(lastBook);

                // Procura se foi devolvido
                for (int j = 0; j < returnsReg.size(); j++)
                    // Se o livro foi devolvido antes da data atual
                    if (returnsReg.get(j).getUserId().equals(this.getUserId()) && returnsReg.get(j).getBookId().equals(borrowsReg.get(i).getBookId())
                            && returnsReg.get(j).getCalendar().compareTo(borrowsReg.get(i).getCalendar()) >= 0 && returnsReg.get(j).getCalendar().compareTo(MainWindowController.getProgramDate()) <= 0){
                        // Registra a data de devolu��o
                        lastBook.setReturnDate(returnsReg.get(j).getCalendar());
                        // Verifica se foi entregue atrasado e registra data de bloqueio
                        lastBook.isLate(this);
                        // Retira o livro da lista
                        this.removeBook(lastBook);
                        break;
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
        if (!this.isBlocked()) {
            setUnblockDate(newDate);
            setBlocked(true);
        }
        else {
            if (getUnblockDate().compareTo(newDate) < 0)
                setUnblockDate(newDate);
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

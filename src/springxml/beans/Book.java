package springxml.beans;

import springxml.services.LogService;

public class Book {
    private int bookId;
    private String title;
    private int bookYear;
    private int bookMonth;
    private LogService logService;

    public Book() {
    }

    // constructor that accepts object of the service: whether console or file is chosen in the xml file
    public Book(LogService logService) {
        this.logService=logService;
    }

    // Setter for log service
    public void setLogService(LogService logService) {
        this.logService=logService;
    }
    public void logChange(String msg) {
        logService.log(msg);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public int getBookMonth() {
        return bookMonth;
    }

    public void setBookMonth(int bookMonth) {
        this.bookMonth = bookMonth;
    }
}

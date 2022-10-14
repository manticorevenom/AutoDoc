/**
 * Book.java
 * Defines Book objects for the Library system.
 * @author manticorevenom (Hunter Leary)
 * @date 2022.9.24
 * @version 1.0
 */
package com.library;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <FREQ>Book</FREQ>
 */
class Book {
    // Variables
    //............................................................................................
    /**
     * <FREQ>Book.Author</FREQ>
     */
    private String Author;
    /**
     * <FREQ>Book.Publish</FREQ>
     */
    private LocalDate publishDate;
    /**
     * <FREQ>Book.ISBN</FREQ>
     */
    private String ISBN;
    /**
     * <FREQ>Book.Title</FREQ>
     */
    private String Title;
    /**
     * <FREQ>Book.CopyNumber</FREQ>
     */
    private int copyNumber;
    /**
     * <FREQ>Book.checkoutList</FREQ>
     */
    private ArrayList<String> checkoutList;
    /**
     * <FREQ>Book.Language</FREQ>
     */
    private String Language;
    /**
     * <FREQ>Book.PageCount</FREQ>
     */
    private int pageCount;
    // SETTERS
    //............................................................................................
    /**
     * <FREQ>Book.Author</FREQ>
     * @param author name of the author of the book
     */
    private void setAuthor(String author){
        Author = author;
    }
    /**
     * <FREQ>Book.Publish</FREQ>
     * @param date this is the publishing date for the book
     */
    private void setPublishDate(LocalDate date){
        publishDate = date;
    }
    /**
     * <FREQ>Book.ISBN</FREQ>
     * @param isbn the isbn of the book
     */
    private void setISBN(String isbn){
        ISBN = isbn;
    }
    /**
     * <FREQ>Book.Title</FREQ>
     * @param title the title of the book
     */
    private void setTitle(String title){
        Title = title;
    }
    /**
     * <FREQ>Book.CopyNumber</FREQ>
     * @param copies number of copies for a book
     */
    private void setCopyNumber(int copies){
        copyNumber = copies;
    }
    /**
     * <FREQ>Book.checkoutList</FREQ>
     * @param checkouts list of names for those who have checked this book out
     */
    private void setCheckoutList(ArrayList<String> checkouts){
        checkoutList = checkouts;
    }

    /**
     * <FREQ>Book.Language</FREQ>
     * @param language language the book is in
     */
    private void setLanguage(String language){
        Language = language;
    }

    /**
     * <FREQ>Book.PageCount</FREQ>
     * @param pages number of pages for a book
     */
    private void setPageCount(int pages){
        pageCount = pages;
    }
    // GETTERS
    //............................................................................................
    /**
     * <FREQ>Book.Author</FREQ>
     * @return returns String, the name of the author of the book
     */
    public String getAuthor(){
        return Author;
    }
    /**
     * <FREQ>Book.Publish</FREQ>
     * @return publishDate, LocalDate type for the publishing date of the book
     */
    public LocalDate getPublishDate(){
        return publishDate;
    }
    /**
     * <FREQ>Book.ISBN</FREQ>
     * @return returns string, the isbn of the book
     */
    public String getISBN(){
        return ISBN;
    }
    /**
     * <FREQ>Book.Title</FREQ>
     * @return returns string, the title of the book
     */
    public String getTitle(){
        return Title;
    }
    /**
     * <FREQ>Book.CopyNumber</FREQ>
     * @return returns int, number of copies for a book
     */
    public int getCopyNumber(){
        return copyNumber;
    }

    /**
     * <FREQ>Book.checkoutList</FREQ>
     * @return returns ArrayList, list of names of people who have checked this book out
     */
    public ArrayList<String> getCheckoutList(){
        return checkoutList;
    }

    /**
     * <FREQ>Book.Language</FREQ>
     * @return return string, language the book is in
     */
    public String getLanguage(){
        return Language;
    }

    /**
     * <FREQ>Book.PageCount</FREQ>
     * @return returns int, page count for this book
     */
    public int getPageCount(){
        return pageCount;
    }
    // Methods
    //............................................................................................
    /**
     * <FREQ>Book.checkoutList</FREQ>
     * @param name name of person who is checking the book out
     */
    public void checkout(String name){
        // name is not in list and size is not full
        // add to list
        if (!checkoutList.contains(name) && checkoutList.size() != copyNumber){
            checkoutList.add(name);
        }
        // list already has the name
        // print they cannot check out another copy
        else if (checkoutList.contains(name)){
            System.out.println(name + " is already in the list. A person cannot checkout multiple copies of one book.");
        }
        // list is full
        // print they cannot check out the book
        else if (checkoutList.size() == copyNumber){
            System.out.println("There are no available copies for this book.");
        }
        // could put in some more branches but it is unnecessary
    }

    /**
     * <FREQ>Book.checkoutList</FREQ>
     * @param name name of person who is returning book
     */
    public void returnBook(String name){
        // if the name is not in the list
        if (!checkoutList.contains(name)){
            System.out.println(name + " has not checked this book out.");
        }
        // if the name is in the list, remove the name from the list
        else{
            checkoutList.remove(name);
        }
        // could put in more branches if necessary
    }

    /**
     * <FREQ>Book.Print</FREQ>
     * @return returns a string, the string will be used to print the book
     */
    public String toString(){
        return Title;
    }
    public void incrementCopy(){
        copyNumber += 1;
    }
    public void decrementCopy(){
        copyNumber -= 1;
    }
    // Constructor(s)
    //............................................................................................
    /**
     * <FREQ>Book</FREQ>
     * @param title title of the book
     * @param author author of the book
     * @param publish publishing date of the book
     * @param isbn isbn of the book
     * @param copies number of copies for this book
     * @param checkouts list of names for people that checked out this book
     * @param language language this book is in
     * @param pages number of pages for this book
     */
    public Book(String title, String author, LocalDate publish, String isbn, int copies,
                ArrayList<String> checkouts, String language, int pages) {

            this.setTitle(title);
            this.setAuthor(author);
            this.setPublishDate(publish);
            this.setISBN(isbn);
            this.setCopyNumber(copies);
            this.setCheckoutList(checkouts);
            this.setLanguage(language);
            this.setPageCount(pages);
    }
}

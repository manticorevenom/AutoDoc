/**
 * BookList.java
 * This class defines the inventory for the Library program
 * This class also defines the methods/operations that can be performed on the inventory
 * @author manticorevenom (Hunter Leary)
 * @date 2022.9.24
 * @version 1.0
 */
package com.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <FREQ>BookList</FREQ>
 */
public class BookList {
    // Variables
    //............................................................................................
    /**
     * <FREQ>BookList</FREQ>
     */
    private ArrayList<Book> inventory;
    // Methods
    //............................................................................................
    /**
     * <FREQ>BookList.Read</FREQ>
     */
    private void read(){
        // read in a .csv
    }
    /**
     * <FREQ>BookList.Save</FREQ>
     */
    public void save(){
        // write the list to a .csv file
    }
    /**
     * <FREQ>BookList.Add</FREQ>
     */
    public void add(){
        // ask for all of the book fields
        Book add = askForInfo();
        // search
        Book search = search(add.getTitle(), add.getAuthor(), add.getISBN(), add.getLanguage(), add.getPageCount());
        // not in list add to list
        if (search.equals(null)){
            inventory.add(add);
        }
        // in list add copy
        else{
            int index = inventory.indexOf(add);
            inventory.get(index).incrementCopy();
        }
    }
    /**
     * <FREQ>BookList.AddCopy</FREQ>
     */
    public void addCopy(){
        // ask for info
        Book addCopy = askForInfo();
        // search
        Book search = search(addCopy.getTitle(), addCopy.getAuthor(), addCopy.getISBN(), addCopy.getLanguage(), addCopy.getPageCount());
        // not in list, say so
        if (search.equals(null)){
            System.out.println(addCopy.getTitle() + " does not exist in the list.");
        }
        // in list increment copy number
        else{
            int index = inventory.indexOf(addCopy);
            inventory.get(index).incrementCopy();
        }
    }
    /**
     * <FREQ>BookList.Print</FREQ>
     */
    public void print(){
        // print all books to screen
        // print check info for a book
    }
    /**
     * <FREQ>BookList.ProcessCheckout</FREQ>
     */
    public void processCheckout() {
        // ask for info
        Book checkout = askForInfo();
        // search
        Book search = search(checkout.getTitle(), checkout.getAuthor(), checkout.getISBN(), checkout.getLanguage(), checkout.getPageCount());
        // not in list, say so
        if (search.equals(null)){
            System.out.println(checkout.getTitle() + " does not exist in the list.");
        }
        // in lists ask for name
        else{
            // get the checkout list
            ArrayList<String> checkouts = search.getCheckoutList();

            // ask for name
            String name = "";
            while(name.equals("")){
                System.out.println("Enter the name for the checkout.");
                try{
                    Scanner getName = new Scanner(System.in);
                    name = getName.nextLine();
                }
                catch(Exception ex){
                    System.out.println("Error in parsing checkout name: " + ex.getMessage());
                }
            }
            // if name is in list
            // say name has already checked the book out and cannot check out two copies
            if(checkouts.contains(name)){
                System.out.println(name + " has already checked this book out.");
            }
            // if the name is not in the list but there are no available copies
            else if(!checkouts.contains(name) && search.getCopyNumber() == 0){
                System.out.println(search.getTitle() + " has no available copies for checkout.");
            }
            // otherwise add name to the list
            // and decrement the available copy number
            else{
                checkouts.add(name);
                search.decrementCopy();
            }
        }
    }
    /**
     * <FREQ>BookList.ProcessReturn</FREQ>
     */
    public void processReturn(){
        // ask for info
        Book ret = askForInfo();
        // search
        Book search = search(ret.getTitle(), ret.getAuthor(), ret.getISBN(), ret.getLanguage(), ret.getPageCount());
        // not in list, say so
        if (search.equals(null)){
            System.out.println(ret.getTitle() + " does not exist in the list.");
        }
        // in lists ask for name
        else{
            // get the ret list
            ArrayList<String> checkouts = search.getCheckoutList();

            // ask for name
            String name = "";
            while(name.equals("")){
                System.out.println("Enter the name for the ret.");
                try{
                    Scanner getName = new Scanner(System.in);
                    name = getName.nextLine();
                }
                catch(Exception ex){
                    System.out.println("Error in parsing ret name: " + ex.getMessage());
                }
            }
            // if name is in list
            // remove name from list and
            if(checkouts.contains(name)){
                checkouts.remove(name);
                search.incrementCopy();
            }
            // otherwise they have not checked out the book
            else{
                System.out.println(name + " has not checked this book out.");
            }
        }
    }
    /**
     * <FREQ>BookList.Delete</FREQ>
     */
    public void delete(){
        // ask for all of the book fields
        Book remove = askForInfo();
        // search
        Book search = search(remove.getTitle(), remove.getAuthor(), remove.getISBN(), remove.getLanguage(), remove.getPageCount());
        // not in list say so
        if (search.equals(null)){
            System.out.println(search.getTitle() + " is not in the inventory.");
        }
        // in list remove from inventory
        else{
            inventory.remove(search);
        }
    }
    /**
     * <FREQ>BookList.DeleteCopy</FREQ>
     */
    public void deleteCopy(){
        // ask for all of the book fields
        Book removeCopy = askForInfo();
        // search
        Book search = search(removeCopy.getTitle(), removeCopy.getAuthor(), removeCopy.getISBN(), removeCopy.getLanguage(), removeCopy.getPageCount());
        // not in list say so
        if (search.equals(null)){
            System.out.println(search.getTitle() + " is not in the inventory.");
        }
        // in list but does not have any available copies
        // say you cannot delete copy
        else if(search.getCopyNumber() == 0){
            System.out.println(search.getTitle() + " does not have any available copies to delete.");
        }
        // in list remove from inventory
        else{
            int index = inventory.indexOf(removeCopy);
            inventory.get(index).decrementCopy();
        }
    }
    /**
     * <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
     * @param title title of book
     * @param author author name of book
     * @param isbn isbn of book
     * @param language language of book
     * @param pageCount number of pages for a book
     * @return returns book if the book is found in inventory, null otherwise
     */
    private Book search(String title, String author, String isbn, String language, int pageCount){
        // ask for title, author, isbn, language, pagecount of the book
        Book looking = inventory.stream()
                .filter(book -> title.equals(book.getTitle()) && author.equals(book.getAuthor())
                        && isbn.equals(book.getISBN()) && language.equals(book.getLanguage())
                        && pageCount == book.getPageCount())
                .findAny()
                .orElse(null);
        return looking;
    }

    /**
     * <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
     * @return new book
     */
    private Book askForInfo(){
        // ask for all of the book fields
        // return book
        String title = "", author = "", isbn = "", language = "";
        LocalDate publish = LocalDate.EPOCH;
        int pages = 0;

        // get String fields
        title = getStringFields("title");
        author = getStringFields("author");
        isbn = getStringFields("ISBN");
        language = getStringFields("language");

        // get int fields
        pages = getIntFields("page count");

        // get publishing date
        publish = getDate();

        // create the new book with all of the fields
        Book temp = new Book(title, author, publish, isbn, 1, new ArrayList<String>(), language, pages);
        return temp;
    }

    /**
     * <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
     * @param field
     * @return new string
     */
    private String getStringFields(String field){
        String temp = "";
        while(temp.equals("")){
            System.out.println("Please enter the " + field + " of the book.");
            try{
                Scanner getField = new Scanner(System.in);
                temp = getField.nextLine();
            }
            catch (Exception ex){
                System.out.println("Error in parsing title: " + ex.getMessage());
            }
        }
        return temp;
    }

    /**
     * <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
     * @param field
     * @return new int
     */
    private int getIntFields(String field){
        int temp = 0;
        while(temp == 0){
            System.out.println("Please enter the " + field + " of the book.");
            try{
                Scanner getField = new Scanner(System.in);
                temp = Integer.parseInt(getField.nextLine());
            }
            catch (Exception ex){
                System.out.println("Error in parsing title: " + ex.getMessage());
            }
        }
        return temp;
    }

    /**
     * <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
     * @return LocalDate for the publish date
     */
    private LocalDate getDate(){
        int day = 0, month = 0, year = 0;
        LocalDate temp = LocalDate.EPOCH;
        // get day
        while (day == 0){
            System.out.println("Please enter the publish day for the book.");
            try {
                Scanner getDay = new Scanner(System.in);
                day = Integer.parseInt(getDay.nextLine());
            }
            catch (Exception ex){
                System.out.println("Error in parsing publishing day: " + ex.getMessage());
            }
            if(day >= 1 && day <= 31){
                break;
            }
            else{
                day = 0;
            }
        }
        // get month
        while (month == 0){
            System.out.println("Please enter the publish month for the book.");
            try {
                Scanner getDay = new Scanner(System.in);
                month = Integer.parseInt(getDay.nextLine());
            }
            catch (Exception ex){
                System.out.println("Error in parsing publishing month: " + ex.getMessage());
            }
            if(month >= 1 && month <= 12){
                break;
            }
            else{
                month = 0;
            }
        }
        // get year
        while (year == 0){
            System.out.println("Please enter the publish year for the book.");
            try {
                Scanner getDay = new Scanner(System.in);
                year = Integer.parseInt(getDay.nextLine());
            }
            catch (Exception ex){
                System.out.println("Error in parsing publishing year: " + ex.getMessage());
            }
            if(year >= 1 && year <= LocalDate.now().getYear()){
                break;
            }
            else{
                year = 0;
            }
        }
        // create the publishing date and return it
        try{
            temp = LocalDate.of(year, month, day);
        }
        catch(Exception ex){
            System.out.println("Error in creating the publishing day: "+ ex.getMessage());
        }
        return temp;
    }
    // Constructor(s)
    //............................................................................................
    public BookList(){
        this.read();
    }

}

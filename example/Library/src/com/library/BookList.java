/**
 * BookList.java
 * This class defines the inventory for the Library program
 * This class also defines the methods/operations that can be performed on the inventory
 * @author manticorevenom (Hunter Leary)
 * @date 2022.9.24
 * @version 1.0
 */
package com.library;

import java.util.ArrayList;

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
        // search
        // not in list add
        // in list add copy
    }
    /**
     * <FREQ>BookList.AddCopy</FREQ>
     */
    public void addCopy(){
        // search
        // in list increment copy number
        // not in list, say so
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
        // search
        // ask for name
    }
    /**
     * <FREQ>BookList.ProcessReturn</FREQ>
     */
    public void processReturn(){
        // search
        // ask for name
    }
    /**
     * <FREQ>BookList.Delete</FREQ>
     */
    public void delete(){
        // search
        // in list remove from inventory
        // not in list, say so
    }
    /**
     * <FREQ>BookList.DeleteCopy</FREQ>
     */
    public void deleteCopy(){
        // search
        // in list decrement copy number
        // not in list, say so
    }
    /**
     * <FREQ>BookList.Add::BookList.AddCopy::BookList.ProcessCheckout::BookList.ProcessReturn::BookList.Delete::BookList.DeleteCopy</FREQ>
     * @return returns true if the book is found in inventory, false otherwise
     */
    private boolean search(){
        // ask for title, book, isbn, language, pagecount of the book
        return true;
    }
    // Constructor(s)
    //............................................................................................
    public BookList(){
        this.read();
    }

}

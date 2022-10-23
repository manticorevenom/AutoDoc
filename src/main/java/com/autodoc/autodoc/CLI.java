/**
 * CLI.java
 * This class is responsible for main
 * functionality of the CLI program
 * it should handle user input
 * @author manticorevenom
 * @date 2022.10.22
 * @version 1.0
 */
package com.autodoc.autodoc;
import java.util.Scanner;

class CLI {
        private enum Options{
            def,
            add,
            addCopy,
            print,
            checkout,
            bookReturn,
            delete,
            deleteCopy,
            exit,
        }

        private static void menu(){
            Options menuOption = Options.def;
            // need an instance of the list of students
            //= new BookList();

            // loop until the user exits
            while (true){
                System.out.println();
                System.out.print("""
                     Menu:
                     1). Add a book.
                     2). Add a copy of a book.
                     3). Print the list of books
                     4). Process a book checkout.
                     5). Process a book return.
                     6). Delete a book.
                     7). Delete a copy of a book.
                     8). Save and Exit.
                     >\s""");
                try{
                    // get input
                    Scanner input = new Scanner(System.in);
                    // try to parse the input and convert into enum
                    menuOption = Options.values()[Integer.parseInt(input.nextLine())];
                }
                // if an error print
                catch(Exception ex){
                    System.out.println("Error in parsing menu option: " + ex.getMessage());
                }
                // switch on the menu option
                switch(menuOption){
                    case def:
                        continue;
                    case add:
                        //inventory.add();
                        break;
                    case addCopy:
                        //inventory.addCopy();
                        break;
                    case print:
                        //inventory.print();
                        break;
                    case checkout:
                        //inventory.processCheckout();
                        break;
                    case bookReturn:
                        //inventory.processReturn();
                        break;
                    case delete:
                        //inventory.delete();
                        break;
                    case deleteCopy:
                        //inventory.deleteCopy();
                        break;
                    case exit:
                        //this will save and exit
                        //inventory.save();
                        break;
                    default:
                        break;
                }
            }
        }
        public static void main(String[] args) {
            menu();
        }
    }
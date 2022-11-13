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
            check,
            update,
            printConflicts,
            printReqs,
            addReq,
            changeReg,
            deleteReq,
            exit,
        }

        private static void menu(){
            Options menuOption = Options.def;
            // need an instance of the auto
            Auto program = new Auto();

            // loop until the user exits
            while (!menuOption.equals(Options.exit)){
                System.out.println();
                System.out.print("""
                     Menu:
                     1). Check Requirements.
                     2). Update Requirements.
                     3). Print Conflicts.
                     4). Print Requirements.
                     5). Add Requirement.
                     6). Change Requirement.
                     7). Delete Requirement.
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
                    case check:
                        program.check();
                        break;
                    case update:
                        // program.update();
                        break;
                    case printConflicts:
                        program.printConflicts();
                        break;
                    case printReqs:
                        // program.print();
                        break;
                    case addReq:
                        // program.add();;
                        break;
                    case changeReg:
                        // program.change();
                        break;
                    case deleteReq:
                        //inventory.delete();
                        break;
                    case exit:
                        //this will save and exit
                        // program.exit();
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
/**
 * parseDoc.java
 * This class is responsible for reading
 * and parsing the document
 * it should create a requirements list
 * @author manticorevenom
 * @date 2022.10.22
 * @version 1.0
 */
package com.autodoc.autodoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class parseDoc implements Parse {
    // VARIABLES -------------------------
    /**
     * Requirements list
     */
    public RequirementList list;
    /**
     * File path
     */
    public String filePath;
    /**
     * Identifier
     */
    public String identifier;
    /**
     * Dictionary for storing the document
     */
    public HashMap<Integer, String> document;
    /**
     * Keywords
     */
    public static ArrayList<String> keywords;
    // SETTERS ---------------------------
    /**
     * setList
     * @param l - requirements list
     */
    public void setList(RequirementList l){
        list = l;
    }

    /**
     * setFilePath
     * @param path - path to file for parsing
     */
    public void setFilePath(String path) {
        filePath = path;
    }

    /**
     * setIdentifier
     * @param id - identifier for searching
     */
    public void setIdentifier(String id) {
        identifier = id;
    }
    /**
     * setDocument
     * @param document - dictionary for the document
     */
    public void setDocument(HashMap<Integer, String> document){
        this.document = document;
    }
    /**
     * setKeywords
     * @param keywords - keyword list
     */
    public void setKeywords(ArrayList<String> keywords){
        parseDoc.keywords = keywords;}
    // GETTERS ---------------------------
    /**
     * getList
     * @return list of requirements
     */
    public RequirementList getList(){
        return list;
    }

    /**
     * getFilePath
     * @return - returns the file path for the parse file
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * getIdentifier
     * @return - returns the identifier for searching
     */
    public String getIdentifier() {
        return identifier;
    }
    /**
     * getDocument
     * @return - returns the dictionary of the document
     */
    public HashMap<Integer, String> getDocument(){
        return document;
    }

    /**
     * getKeywords
     * @return - list of keywords
     */
    public static ArrayList<String> getKeywords() {
        return keywords;
    }
    // METHODS ---------------------------
    /**
     * This method will parse the document
     * into a hashMap
     */
    public void read(){
        // create a buffered reader to parse the document
        BufferedReader reader;

        // line count will be used as the key for the hashMap
        int lineCount = 0;
        // try to parse
        try {
            reader = new BufferedReader(new FileReader(filePath));

            // grab each line and convert document into hash map
            for( String line : reader.lines().toList()){
                document.put(lineCount, line);
                lineCount++;
            }
            // close reader
            reader.close();
        }
        // catch any exception that occurs while parsing
        catch(Exception ex){
            System.out.println("Error in parsing the document: " + ex.getMessage());
        }

        // now try to parse requirements
        this.parseRequirements();
    }
    /**
     * This method parses the hashMap for requirements
     * It probably isn't the greatest
     */
    private void parseRequirements(){
        // create a requirements list
        RequirementList list = new RequirementList();
        Requirement requirement = new Requirement();

        // requirement fields
        String tag = null, context = null;
        ArrayList<String> headers = new ArrayList<>();

        // relative line with identifier
        int first = 0;
        // relative second line with identifier
        int second = 0;
        int offest = 0;

        // now try to parse the requirements
        for( int line = 0; line < document.size(); line++){
            // if a line contains the identifier
            // look to the next identifier
            if (document.get(line).contains(identifier)){
                // if the first is not set
                if( first == 0 ) {
                    first = line;
                }
                // otherwise set the second
                else if( second == 0 ){
                    second = line;
                }
            }
            // near end of document
            else if( line == document.size() - 1){
                second = document.size() - 1;
            }

            // if first and second are set
            // get values for requirement
            tag = "";
            context = "";
            headers = new ArrayList<>();

            if(!(first == 0 || second == 0)){
                // for each line in between the first and second
                for( int difference = first; difference < second; difference++){
                    // if the line contains the identifier
                    if(document.get(difference).contains(identifier)) {
                        // grab the line and split
                        // don't ask
                        String[] values = document.get(difference).split("\\s+");

                        tag = values[2];

                        for (int lines = 3; lines < values.length; lines++) {
                            context += values[lines] + " ";
                        }
                    }
                    // otherwise it does not have an identifier
                    else{
                        // we loop over our keywords
                        for(String keyword : keywords){
                            // if it has a keyword it is a header
                            if(document.get(difference).contains(keyword)){
                                headers.add(document.get(difference).trim());
                            }
                        }
                    }
                }
                // a check to make sure that the tag is some kind of word
                // and not empty
                // if it passes the check
                // add the requirement to the list
                if( tag != null ) {
                    if (tag.length() > 2){
                        list.addRequirement(new Requirement(tag, headers, context, line - (second - first) + 1));
                    }
                }

                // set requirement to next requirement
                first = second;
                // default the second value
                second = 0;

            }
        }
        // let see what happens
        //System.out.println(list);
        // sort of works but I'm not happy with it
        setList(list);
    }
    /**
     * updateDocument
     * this will handle updating
     * the requirements in the actual
     * hashMap
     */
    public void updateDocument(){
        // for each requirement in the requirement list
        for (Requirement requirement : list.getRequirementList()){
            // replace whatever was there before and update it with
            // our updated requirement

            // some regex
            int spaces = 0;
            char[] line = document.get(requirement.getLine() - 1).toCharArray();

            for(int chars = 0; chars < line.length; chars++){
                if(line[chars] == ' '){
                    spaces++;
                }
                else if(line[chars] != ' '){
                    break;
                }
            }

            // remove previous entry
            // remove everything up to the next req
            for(int lines = requirement.getLine(); lines < requirement.getLine() + requirement.getHeaders().size(); lines++){
                document.replace(lines, "");
            }
            document.put(requirement.getLine() - 1, " ".repeat(spaces) + identifier + " " + requirement.print(spaces + 4));
        }
    }

    /**
     * writeDoc
     * this method creates the updated document
     * based the updated requirements
     */
    public void writeDoc(){
        // Try block to check if exception occurs
        String text  = "";
        for(int line = 0; line < document.size(); line++){
            text = text + document.get(line) + "\n";
        }
        try {

            // Create a FileWriter object
            // to write in the file
            FileWriter fWriter = new FileWriter("src/srs.txt");

            // Writing into file
            // Note: The content taken above inside the
            // string
            fWriter.write(text);
            // Closing the file writing connection
            fWriter.close();

            // Display message for successful execution of
            // program on the console
            System.out.println(
                    "File is created successfully with the content.");
        }

        // Catch block to handle if exception occurs
        catch (Exception e) {

            // Print the exception
            System.out.print(e.getMessage());
        }
    }
    // CONSTRUCTORS ----------------------

    /**
     * Defaulted no parameter constructor
     */
    public parseDoc(){
        list = new RequirementList();
        filePath = "examples/Library/docs/srs.txt";
        identifier = "SFREQ";
        document = new HashMap<>();
        String[] words = {"public", "private", "protected", "class"};
        keywords = new ArrayList<>(List.of(words));
        read();
    }
    /**
     * Constructor with parameters
     * @param reqs - list of requirements
     * @param fp - file path for parse document
     * @param id - identifier for searching
     * @param keywords - list of words
     */
    public parseDoc(RequirementList reqs, String fp, String id, ArrayList<String> keywords){
        setList(reqs);
        setFilePath(fp);
        setIdentifier(id);
        setDocument(new HashMap<>());
        setKeywords(keywords);
        read();
    }
}

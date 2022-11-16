/**
 * parseCode.java
 * This class is responsible for reading
 * and parsing the source code
 * it should create a requirements list
 * @author manticorevenom
 * @date 2022.10.22
 * @version 1.0
 */
package com.autodoc.autodoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// thinking of turning the code into a hashmap
// this would allow us to update stuff easier
class parseCode implements Parse {
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
     * Identifier that denotes a requirement
     */
    public String identifier;
    /**
     * Dictionary for storing the document
     */
    public HashMap<Integer, String> code;
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
     * setCode
     * @param code - dictionary for the document
     */
    public void setCode(HashMap<Integer, String> code){
        this.code = code;
    }
    /**
     * setKeywords
     * @param keywords_in - keyword list
     */
    public void setKeywords(ArrayList<String> keywords_in){
        keywords = keywords_in;
    }
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
     * getCode
     * @return - returns the dictionary of the document
     */
    public HashMap<Integer, String> getCode(){
        return code;
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
     * This method will parse the code
     * looking for the code tag
     * and then pulling the code headers
     */
    public void read(){
        // create a buffered reader to parse the document
        BufferedReader reader;

        // line count will be used as the key for the hashMap
        int lineCount = 0;
        // try to parse
        try {
            reader = new BufferedReader(new FileReader(filePath));

            // grab each line and convert code into hash map
            for( String line : reader.lines().toList()){
                code.put(lineCount, line);
                lineCount++;
            }
            // close reader
            reader.close();
        }
        // catch any exception that occurs while parsing
        catch(Exception ex){
            System.out.println("Error in parsing the code: " + ex.getMessage());
        }

        // now try to parse requirements
        this.parseRequirements();
    }
    /**
     * parseRequirements
     * this method is responsible
     * for creating the requirements
     * and adding them to the list
     */
    private void parseRequirements(){
        // create a requirements list
        RequirementList reqs = new RequirementList();
        Requirement req = new Requirement();

        // relative line with identifier
        int first = 0;
        // relative second line with identifier
        int second = 0;

        // now try to parse the requirements
        for( int line = 0; line < code.size(); line++){
            // if a line contains the identifier
            // look to the next identifier
            if (code.get(line).contains(identifier)){
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
            else if( line == code.size() - 1){
                second = code.size() - 1;
            }

            // if first and second are set
            // get values for requirement
            String[] multipleTags = null;
            ArrayList<String> tags = new ArrayList<>();

            if(!(first == 0 || second == 0)){
                // for each line in between the first and second
                for( int difference = first; difference < second; difference++){
                    // if the line contains the identifier
                    if(code.get(difference).contains(identifier)) {
                        // grab the line and split
                        // get the regex
                        Pattern p = Pattern.compile("<" + identifier+ ">(.+?)</" + identifier + ">", Pattern.DOTALL);
                        Matcher m = p.matcher(code.get(difference));
                        m.find();

                        // get the requirement tags
                        if(m.group(1).contains("::")){
                            multipleTags = m.group(1).split("::");
                            tags = new ArrayList<>(List.of(multipleTags));
                        }
                        else{
                            tags.add(m.group(1));
                        }
                    }
                    // otherwise it does not have an identifier
                    else{
                        // we loop over our keywords
                        for(String keyword : keywords){
                            // if it has a keyword it is a header
                            if(code.get(difference).contains(keyword)){
                                // get rid of { and ;
                                //System.out.println(code.get(difference).replace("{", "").replace(";", "").trim());
                                req.addHeader(code.get(difference).replace("{", "").replace(";", "").trim());
                            }
                        }
                    }
                }
                for(String tag : tags){
                    Requirement temp = new Requirement(tag, req.getHeaders(), "No context", line);
                    reqs.addRequirement(temp);
                }
                // clear out previous headers
                req = new Requirement();
                // set requirement to next requirement
                first = second;
                // default the second value
                second = 0;

                // a check to make sure that the tag is some kind of word
                // and not empty
                // if it passes the check
                // add the requirement to the list
            }
        }
        // let see what happens
        // System.out.println(reqs);
        // sort of works but I'm not happy with it
        setList(reqs);
    }
    // CONSTRUCTORS ----------------------
    /**
     * Defaulted no parameter constructor
     */
    public parseCode(){
        list = new RequirementList();
        filePath = "examples/Library/src/com/library/BookList.java";
        identifier = "FREQ";
        code = new HashMap<>();
        String[] words = {"public", "private", "protected", "class"};
        keywords = new ArrayList<>(List.of(words));
        read();
    }

    /**
     * Constructor with parameters
     * @param reqs - list of requirements
     * @param fp - file path for parse code
     * @param id - identifier for searching
     */
    public parseCode(RequirementList reqs, String fp, String id, ArrayList<String> keywords_in){
        setList(reqs);
        setFilePath(fp);
        setIdentifier(id);
        setCode(new HashMap<>());
        setKeywords(keywords_in);
        read();
    }
}

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

import java.util.Dictionary;

// thinking of turning the document into a hashmap
// this would allow us to update stuff easier
class parseDoc implements Parse {
    // VARIABLES -------------------------

    // idea is the line would be an int and the string would be contents of a line
    //private Dictionary<Integer, String> doc;
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
    // METHODS ---------------------------
    /**
     * This method will parse the document
     * looking for the document tag
     * and then pulling the code headers
     */
    public void read(){
        // reads line by line
        // looks for the identifier
        // grabs next word as the req tag
        // then loops over the next few lines until
        // it hits another identifier
        // if the line is a header it adds
        // the header to the req header list
        // might use a regex,
        // after everything is parsed the requirements list will be created
    }
    // CONSTRUCTORS ----------------------

    /**
     * Defaulted no parameter constructor
     */
    public parseDoc(){
        list = new RequirementList();
        filePath = "example/Library/docs/srs.txt";
        identifier = "SFREQ";
    }

    /**
     * Constructor with parameters
     * @param reqs - list of requirements
     * @param fp - file path for parse document
     * @param id - identifier for searching
     */
    public parseDoc(RequirementList reqs, String fp, String id){
        setList(reqs);
        setFilePath(fp);
        setIdentifier(id);
    }
}

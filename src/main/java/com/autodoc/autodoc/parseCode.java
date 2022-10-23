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
     * This method will parse the code
     * looking for the code tag
     * and then pulling the code headers
     */
    public void read(){
        // reads line by line
        // looks for the <identifier>
        // grabs everything till </identifier>
        // Splits on the ::
        // if the line contains private/public/class/protected in the string with the ()
        // loop over the list of reqs and add header if it is not already there
        // might use a regex,
    }
    // CONSTRUCTORS ----------------------

    /**
     * Defaulted no parameter constructor
     */
    public parseCode(){
        list = new RequirementList();
        filePath = "example/Library/src/com/library/BookList.java";
        identifier = "FREQ";
    }

    /**
     * Constructor with parameters
     * @param reqs - list of requirements
     * @param fp - file path for parse code
     * @param id - identifier for searching
     */
    public parseCode(RequirementList reqs, String fp, String id){
        setList(reqs);
        setFilePath(fp);
        setIdentifier(id);
    }
}

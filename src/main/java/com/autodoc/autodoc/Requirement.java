/**
 * Requirement.java
 * This class is responsible for requirements
 * It will handle operations for the requirement
 * Set, get,
 * @author manticorevenom
 * @date 2022.10.22
 * @version 1.0
 */
package com.autodoc.autodoc;

import java.util.ArrayList;

// My thinking is that a
// requirement will have a tag
// and a list of code headers
class Requirement {

    // VARIABLES -------------------------
    /**
     * Tag of the requirement
     */
    private String tag;
    /**
     * List of variables, method headers,
     * class headers that correspond to a requirement
     */
    private ArrayList<String> headers;
    /**
     * Requirement context
     */
    // need context because we only want to replace
    // what we need to so we have to make sure to grab
    // things correctly
    // private String context;

    // SETTERS ---------------------------

    /**
     * setTag
     * @param t - String tag for the requirement
     */
    private void setTag(String t){
        tag = t;
    }

    /**
     * setHeaders
     * @param h - List of string that contain the headers
     */
    private void setHeaders(ArrayList<String> h){
        headers = h;
    }
    // GETTERS ---------------------------

    /**
     * getTag
     * @return tag of the requirement
     */
    public String getTag(){
        return tag;
    }

    /**
     * getHeaders
     * @return list of headers for the requirement
     */
    public ArrayList<String> getHeaders(){
        return headers;
    }
    // METHODS ---------------------------
    // maybe a public add header
    // maybe a public remove header
    // maybe a public search header
    // maybe a compare method
    // CONSTRUCTORS ----------------------

    /**
     * Defaulted no parameter constructor
     */
    public Requirement(){
        tag = "FREQ";
        headers = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     * @param t - tag for the requirement
     * @param h - list of strings for headers for the requirement
     */
    public Requirement(String t, ArrayList<String> h){
        setTag(t);
        setHeaders(h);
    }

}

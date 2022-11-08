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
    private String context;
    /**
     * relative line count
     */
    private int line;

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

    /**
     * setContext
     * @param context - string description or context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * setLine
     * @param line - relative line number
     */
    private void setLine(int line){
        this.line = line;
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
    /**
     * getContext
     * @return context of the requirement
     */
    public String getContext() {
        return context;
    }

    /**
     * getLine
     * @return - int representing relative line count
     */
    public int getLine(){
        return line;
    }
    // METHODS ---------------------------
    /**
     * adds a header to the header list
     * @param header - header to add to the list
     */
    public void addHeader(String header){
        if(!headers.contains(header)){
            headers.add(header);
        }
        else{
            System.out.println(header + " already exists in the list.");
        }
    }
    /**
     * removes a header from the header list
     * @param header - header to remove
     */
    public void removeHeader(String header){
        if(headers.contains(header)){
            headers.remove(header);
        }
        else{
            System.out.println(header + " does not exist in the list.");
        }
    }

    /**
     * searches for a given header in the header list
     * @param header - header for searching
     * @return boolean depending on if the header is found
     */
    public boolean searchHeader(String header){
        return headers.contains(header);
    }
    /**
     * Checks headers lists
     * and returns true or false
     * depending on if the lists
     * are equivalent
     * @param headers - a list of headers for comparing
     * @return boolean for if the lists are equal
     */
    public boolean compareHeaders(ArrayList<String> headers){
        // if they are the same size
        // and each item in one list is in the other
        if( this.headers.size() == headers.size()) {
            for (String s : this.headers) {
                // if any of the items are not in the list
                if (!headers.contains(s)) {
                    // return false
                    return false;
                }
            }
        }
        // return true
        return true;
    }

    /**
     * compares requirements
     * @param req - requirement to compare
     * @return boolean depending on if they are equivalent or not
     */
    public boolean compareRequirement(Requirement req){
        // if tags are equal and headers are equal
        return tag.toLowerCase() == req.getTag().toLowerCase() && this.compareHeaders(req.getHeaders());
    }

    /**
     * override toString()
     * @return string form of the requirement
     */
    @Override
    public String toString(){
        String requirement = "";
        requirement += tag + " ";
        for( String header : headers ){
            requirement += header + " ";
        }
        requirement += context + " ";
        requirement += line;
        return requirement;
    }
    // CONSTRUCTORS ----------------------
    /**
     * Defaulted no parameter constructor
     */
    public Requirement(){
        tag = "FREQ";
        context = "Sample description";
        headers = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     * @param tag - tag for the requirement
     * @param headers - list of strings for headers for the requirement
     * @param context - context for the requirement
     * @param line - relative line number for the requirement
     */
    public Requirement(String tag, ArrayList<String> headers, String context, int line){
        setTag(tag);
        setHeaders(headers);
        setContext(context);
        setLine(line);
    }

}

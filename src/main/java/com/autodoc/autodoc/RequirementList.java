/**
 * RequirementList.java
 * This class is responsible for the list of requirements
 * It will handle operations on the list
 * Add, remove, search, etc.
 * @author manticorevenom
 * @date 2022.10.22
 * @version 1.0
 */
package com.autodoc.autodoc;

import java.util.ArrayList;

// My thinking is that the requirements list
// will be able to search for reqs, add reqs, etc.
class RequirementList {
    // VARIABLES -------------------------
    /**
     * List of requirements
     */
    private ArrayList<Requirement> requirementList;
    // SETTERS ---------------------------

    /**
     * setRequirementsList
     * @param reqs - list of requirements
     */
    private void setRequirementsList(ArrayList<Requirement> reqs){
        requirementList = reqs;
    }
    // GETTERS ---------------------------

    /**
     * getRequirementsList
     * @return the requirements list
     */
    public ArrayList<Requirement> getRequirementList(){
        return requirementList;
    }
    // METHODS ---------------------------
    // search
    // add to list
    // remove from list
    // compare method that takes in another requirement
    // CONSTRUCTORS ----------------------
    /**
     * Default no parameter Constructor
     */
    public RequirementList(){
        requirementList = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     */
    public RequirementList(ArrayList<Requirement> reqs){
        setRequirementsList(reqs);
    }
}

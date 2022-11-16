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
    /**
     * searches for a requirement
     * @param search - requirement to search for
     * @return boolean depending on found
     */
    public boolean searchRequirement(Requirement search){
        Requirement contain =  requirementList.stream().filter(requirement -> search.compareRequirement(requirement))
                .findAny()
                .orElse(null);
        if(contain == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Searches for the tag
     * of a requirement
     * @param search - requirement we are searching for
     * @return boolean depending on found
     */
    public boolean searchTag(Requirement search){
        Requirement contain =  requirementList.stream().filter(requirement -> search.compareTag(requirement.getTag()))
                .findAny()
                .orElse(null);
        if(contain == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Returns a requirement in the list if
     * a tag is matched
     * @param tag
     * @return
     */
    public Requirement getRequirementByTag(String tag){
        return  requirementList.stream().filter(requirement -> requirement.compareTag(tag))
                .findAny()
                .orElse(null);
    }
    /**
     * adds a requirement to requirements list
     * @param add - requirement to add
     */
    public void addRequirement(Requirement add){
        // if there is a tag
        // we just need to merge headers
        if(this.searchTag(add)){
            // for each header for add
            for(String header : add.getHeaders()){
                // merge the headers if needed
                this.getRequirementByTag(add.getTag()).addHeader(header);
            }
        }
        // else there is not a tag
        // and it is not in the list
        else if(!this.searchRequirement(add)){
            requirementList.add(add);
        }
        else{
            System.out.println(add + " is already in the list.");
        }
    }
    /**
     * removes a requirement from the list
     * @param remove - requirement to remove
     */
    public void removeRequirement(Requirement remove){
        if(this.searchRequirement(remove)){
            requirementList.remove(remove);
        }
        else{
            System.out.println(remove + " is not in the list.");
        }
    }
    /**
     * compares two requirement lists
     * @param req - requirement list for comparison
     * @return boolean
     */
    public boolean compareRequirements(RequirementList req){
        // if the lists are the same size
        if(requirementList.size() == req.getRequirementList().size()){
            // check each requirement
            for(int r = 0; r < requirementList.size(); r++){
                // if any requirement is not equal
                if(!requirementList.get(r).compareRequirement(req.requirementList.get(r))){
                    // the lists are not equivalent
                    return false;
                }
            }
            // if not false
            // then true;
            return true;
        }
        // if they are not the same size
        else{
            return false;
        }
    }

    /**
     * toString override
     * @return string form of the requirement list
     */
    @Override
    public String toString(){
        String requirements = "";
        for (Requirement requirement : requirementList){
            requirements += "\tRequirement:\n" + requirement.toString() + "\n";
        }
        return requirements;
    }
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

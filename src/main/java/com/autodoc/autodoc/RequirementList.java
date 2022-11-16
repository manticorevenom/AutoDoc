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
    /**
     * Returns a requirement in the list if
     * a tag is matched
     * @param tag - tag for the requirement
     * @return the requirement with the tag
     */
    public Requirement getRequirementByTag(String tag){
        return  requirementList.stream().filter(requirement -> requirement.compareTag(tag))
                .findAny()
                .orElse(null);
    }
    /**
     * getListOfIndices
     * @return list of ints
     */
    public int[] getListOfIndices(){
        int[] indices = new int[requirementList.size()];
        for(int i = 0; i < requirementList.size(); i++){
            indices[i] = requirementList.get(i).getLine();
        }
        return indices;
    }
    // METHODS ---------------------------
    /**
     * searches for a requirement
     * @param search - requirement to search for
     * @return boolean depending on found
     */
    public boolean searchRequirement(Requirement search){
        Requirement contain =  requirementList.stream().filter(search::compareRequirement)
                .findAny()
                .orElse(null);
        return contain != null;
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
        return contain != null;
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
        StringBuilder requirements = new StringBuilder();
        for (Requirement requirement : requirementList){
            requirements.append("\tRequirement:\n").append(requirement.toString()).append("\n");
        }
        return requirements.toString();
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

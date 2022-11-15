/**
 * Auto.java
 * This class will be responsible for the auto-updates
 * and resolving conflicts
 * it will create a parseDoc
 * and a parseCode
 * and compare each of their requirement
 * lists and come up with conflicts
 * @author manticorevenom
 * @date 2022.10.23
 * @version 1.0
 */
package com.autodoc.autodoc;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Auto {
    private parseDoc document;
    private parseCode code;
    private ArrayList<Conflict> conflicts;

    // might want to store a list of conflicts
    // conflicts will be between two requirements
    // and will have a resolution
    // might want to create a resolution type
    // A resolution will be a requirement to replace/update/change
    // a requirement in the list
    // and then figure out how to resolve them
    // private ArrayList<Conflicts> conflicts;

    /**
     * Checks for missing requirements
     * If there is any missing requirements in either
     * list it creates a conflict
     * and adds to the conflict list
     */
    private void checkForMissing(){
        // check for any missing requirements from the code
        for(Requirement r : document.getList().getRequirementList()){
            // if the requirement is not in the list
            // and the conflict is not already in the list
            if(!code.getList().searchTag(r) && !conflicts.stream()
                    .filter(conflict -> conflict.compare(new Conflict(r, new Requirement(), "Code does not have requirement."))).findAny().isPresent()) {
                        conflicts.add(new Conflict(r, new Requirement(), "Code does not have requirement."));
            }
        }

        // check for any missing requirements from the document
        for(Requirement r : code.getList().getRequirementList()){
            // if the requirement is not in the list
            // and the conflict is not already in the list
            if(!document.getList().searchTag(r) && !conflicts.stream()
                    .filter(conflict -> conflict.compare(new Conflict(new Requirement(), r, "Document does not have requirement."))).findAny().isPresent()){
                conflicts.add(new Conflict(new Requirement(), r, "Document does not have requirement."));
            }
        }
    }

    /**
     * This method checks to make sure
     * each requirement that is in
     * either list is equivalent to
     * the same requirement in the other
     * list
     */
    private void checkForDissimilar(){
        // check each requirement in document against corresponding equivalent in code list
        // if it exists
        for(Requirement r : document.getList().getRequirementList()){
            // if requirement is in the list
            if(code.getList().searchTag(r)){
                // and the requirements are not equivalent
                // create a new conflict if it does not already exist
                if(!r.compareRequirement(code.getList().getRequirementByTag(r.getTag()))){
                    if(!conflicts.contains(new Conflict(r, code.getList().getRequirementByTag(r.getTag()), "Requirements are not equivalent."))) {
                        conflicts.add(new Conflict(r, code.getList().getRequirementByTag(r.getTag()), "Requirements are not equivalent."));
                    }
                }
            }
        }
    }

    public void check(){
        // check for conflicts
            // conflicts:
            // 1). requirement in doc, not in code
            // 2). requirement in code, not in doc
            // 3). requirement in doc is not the same (header compare) as the requirement in code
            // 4). requirement in code is not the same (header compare) as the requirement in doc
        // if there is a conflict
        // we ask how they want to resolve it
        // then we update the document or code
        checkForMissing();
        checkForDissimilar();
        //System.out.println(document.getList().toString());
        //System.out.println(code.getList().toString());

    }

    /**
     * update
     * this method will automatically
     * update the document or the code
     * based on user input
     * right now it will do document only
     */
    public void update(){
        // looks at conflicts
        // if the conflict is in document
        // update the requirements and update the document
        // then write the document
        updateDocumentToFitCode();
        document.updateDocument();
        document.writeDoc();

    }

    /**
     * Automatically updates all document
     * requirements to fit the code requirements
     */
    public void updateDocumentToFitCode(){
        RequirementList list = document.getList();
        // go through the conflicts and resolve the conflicts
        ArrayList<Conflict> resolved = new ArrayList<>();
        for(Conflict conflict : conflicts){
            // if the requirements are not equivalent
            if(conflict.getContext().equals("Requirements are not equivalent.")){
                // clear out the document headers
                document.getList().getRequirementByTag(conflict.getDocReq().getTag()).clearHeaders();
                // add the code req headers to the document requirement
                for( String header : conflict.getCodeReq().getHeaders()){
                    document.getList().getRequirementByTag(conflict.getDocReq().getTag()).addHeader(header);
                }
                // conflict is resolved
                // remove conflict
                resolved.add(conflict);
            }
            // if the document does not have a requirement
            else if(conflict.getContext().equals("Document does not have requirement.")){
                // add the code requirement to the document
                document.getList().addRequirement(code.getList().getRequirementByTag(conflict.getCodeReq().getTag()));
                // conflict is resolved
                // remove conflict
                resolved.add(conflict);
            }
            // else ignore it for now
        }
        // loop over resolved list and remove from conflicts
        for(Conflict resolution : resolved){
            conflicts.remove(resolution);
        }
    }

    /**
     * printConflicts
     * prints the list of conflicts if any
     */
    public void printConflicts(){
        if(conflicts.size() > 0){
            for(Conflict conflict : conflicts){
                System.out.println(conflict);
            }
        }
        else{
            System.out.println("No conflicts found.");
        }
    }
    /**
     * printReqs
     */
    public void printReqs(){
        System.out.println(document.getList().toString());
        System.out.println(code.getList().toString());
    }

    /**
     * Auto constructor
     * Defaulted
     */
    public Auto(){
        document = new parseDoc();
        code = new parseCode();
        conflicts = new ArrayList<>();
    }

    /**
     * Auto Constructor
     * Not defaulted
     * @param dFP - document filepath
     * @param dID - document identifier
     * @param cFP - code filepath
     * @param cID - code identifier
     * @param keywords - list of keywords for headers
     */
    public Auto(String dFP, String dID, String cFP, String cID, ArrayList<String> keywords){
        document = new parseDoc(new RequirementList(), dFP, dID, keywords);
        code = new parseCode(new RequirementList(), cFP, cID, keywords);
        conflicts = new ArrayList<>();
    }
}

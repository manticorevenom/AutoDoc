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

class Auto {
    private final parseDoc document;
    private final parseCode code;
    private final ArrayList<Conflict> conflicts;
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
            if(!code.getList().searchTag(r) && conflicts.stream()
                    .noneMatch(conflict -> conflict.compare(new Conflict(r, new Requirement(), "Code does not have requirement.")))) {
                conflicts.add(new Conflict(r, new Requirement(), "Code does not have requirement."));
            }
        }

        // check for any missing requirements from the document
        for(Requirement r : code.getList().getRequirementList()){
            // if the requirement is not in the list
            // and the conflict is not already in the list
            if(!document.getList().searchTag(r) && conflicts.stream()
                    .noneMatch(conflict -> conflict.compare(new Conflict(new Requirement(), r, "Document does not have requirement.")))){
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

    /**
     * check
     * checks the requirements lists
     * from the code
     * and the document
     * and creates conflicts
     */
    public void check(){
        // check for conflicts
        // conflicts:
        // 1). requirement in doc, not in code
        // 2). requirement in code, not in doc
        // 3). requirement in doc is not the same (header compare) as the requirement in code
        // 4). requirement in code is not the same (header compare) as the requirement in doc
        checkForMissing();
        checkForDissimilar();

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
     * print
     * this is for the cli program
     * @param control - value to control print
     */
    public void print(boolean control){
        if(control){
            System.out.println(printConflicts());
        }
        else{
            System.out.println("Document Requirements:\n" + document.getList().toString());
            System.out.println("Code Requirements:\n" + code.getList().toString());
        }
    }

    /**
     * printConflicts
     * prints the list of conflicts if any
     * change to string return
     */
    public String printConflicts(){
        StringBuilder output = new StringBuilder();
        if(conflicts.size() > 0){
            for(Conflict conflict : conflicts){
                output.append(conflict.toString()).append("\n");
            }
        }
        else{
            System.out.println("No conflicts found.");
        }
        return output.toString();
    }

    /**
     * printDocReqs
     * @return string of the list of reqs
     */
    public String printDocReqs(){
        return document.getList().toString();
    }

    /**
     * printCodeReqs
     * @return string of the list of reqs
     */
    public String printCodeReqs(){
        return code.getList().toString();
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

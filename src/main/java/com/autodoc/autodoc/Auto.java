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
    private parseDoc document;
    private parseCode code;

    // might want to store a list of conflicts
    // conflicts will be between two requirements
    // and will have a resolution
    // might want to create a resolution type
    // A resolution will be a requirement to replace/update/change
    // a requirement in the list
    // and then figure out how to resolve them
    // private ArrayList<Conflicts> conflicts;


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
    }
    public Auto(){
        document = new parseDoc();
        code = new parseCode();
    }
    public Auto(String dFP, String dID, String cFP, String cID, ArrayList<String> keywords){
        document = new parseDoc(new RequirementList(), dFP, dID, keywords);
        code = new parseCode(new RequirementList(), cFP, cID);
    }
}

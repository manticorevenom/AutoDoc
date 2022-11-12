/**
 * Conflict.java
 * This is responsible for handling
 * conflicts
 * @author manticorevenom
 * @date 2022.11.12
 * @version 1.0
 */
package com.autodoc.autodoc;

// conflicts will be between two requirements
// conflicts will have some context-
public class Conflict {
    // VARIABLES -------------------------
    Requirement docReq;
    Requirement codeReq;
    String context;
    // GETTERS -------------------------
    private Requirement getDocReq() {
        return docReq;
    }
    private Requirement getCodeReq() {
        return codeReq;
    }
    private String getContext() {
        return context;
    }

    // SETTERS -------------------------
    public void setDocReq(Requirement docReq) {
        this.docReq = docReq;
    }
    public void setCodeReq(Requirement codeReq) {
        this.codeReq = codeReq;
    }
    public void setContext(String context) {
        this.context = context;
    }
    // CONSTRUCTORS -------------------------


}

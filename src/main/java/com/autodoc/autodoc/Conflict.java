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
class Conflict {
    // VARIABLES -------------------------
    /**
     * Document Requirement
     */
    Requirement docReq;
    /**
     * Code Requirement
     */
    Requirement codeReq;
    /**
     * Context Around the conflict
     */
    String context;
    // GETTERS -------------------------

    /**
     * getDocReq
     * @return document requirement
     */
    public Requirement getDocReq() {
        return docReq;
    }

    /**
     * getCodeReq
     * @return code requirement
     */
    public Requirement getCodeReq() {
        return codeReq;
    }

    /**
     * getContext
     * @return context for requirement
     */
    public String getContext() {
        return context;
    }

    // SETTERS -------------------------

    /**
     * setDocReq
     * @param docReq - document requirement
     */
    private void setDocReq(Requirement docReq) {
        this.docReq = docReq;
    }

    /**
     * setCodeReq
     * @param codeReq - code requirement
     */
    private void setCodeReq(Requirement codeReq) {
        this.codeReq = codeReq;
    }

    /**
     * setContext
     * @param context - context for the conflict
     */
    private void setContext(String context) {
        this.context = context;
    }

    /**
     * compare
     * @param conflict - conflict for comparison
     * @return - boolean depending on result
     */
    public boolean compare(Conflict conflict){
        return this.docReq.compareTag(conflict.getDocReq().getTag())
                && this.codeReq.compareTag(conflict.getCodeReq().getTag())
                && this.context.equals(conflict.getContext());
    }
    /**
     * toString
     * @return string for conflicts
     */
    @Override
    public String toString(){
        return context + "\n" + "\tDocument Requirement:\n" + docReq.toString() + "\n" + "\tCode Requirement:\n" + codeReq.toString();
    }

    // CONSTRUCTORS -------------------------

    /**
     * Constructor for Conflicts
     * @param docReq - document requirement
     * @param codeReq - code requirement
     * @param context - context for the
     */
    public Conflict(Requirement docReq, Requirement codeReq, String context){
        setDocReq(docReq);
        setCodeReq(codeReq);
        setContext(context);
    }

}

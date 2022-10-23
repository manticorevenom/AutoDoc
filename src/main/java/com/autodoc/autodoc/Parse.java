/**
 * parse.java
 * This interface is responsible for
 * defining methods and variables
 * needed for parsing
 * @author manticorevenom
 * @date 2022.10.22
 * @version 1.0
 */
package com.autodoc.autodoc;

interface Parse {
    /**
     * setList
     * @param l - list of requirements
     */
    void setList(RequirementList l);

    /**
     * setFilePath
     * @param path - path to file for parsing
     */
    void setFilePath(String path);

    /**
     * setIdentifier
     * @param id - identifier for searching
     */
    void setIdentifier(String id);

    /**
     * getList
     * @return - list of requirements
     */
    RequirementList getList();

    /**
     * getFilePath
     * @return - file path for a file for parsing
     */
    String getFilePath();

    /**
     * getIdentifier
     * @return - identifier for searching
     */
    String getIdentifier();

    /**
     * Read method for parsing
     */
    void read();
}

package com.xu81.java.tools.model;

/**
 *
 * @author Arthur.Tsu
 */
public class DataBaseInfo {
    private String[] dbNames;
    private String[] collNames;

    public String[] getCollNames() {
        return collNames;
    }

    public void setCollNames(String[] collNames) {
        this.collNames = collNames;
    }

    public String[] getDbNames() {
        return dbNames;
    }

    public void setDbNames(String[] dbNames) {
        this.dbNames = dbNames;
    }
    
}

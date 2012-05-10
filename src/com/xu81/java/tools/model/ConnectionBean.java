package com.xu81.java.tools.model;

import java.io.Serializable;

/**
 *
 * @author Arthur.Tsu
 */
public class ConnectionBean implements Serializable{

    private String name;
    private String server;
    private String port;
    private String dbName;

    public ConnectionBean(){
        this(null,null,null,null);
    }
    
    public ConnectionBean(String name,String server,String port,String dbName){
        this.name = name;
        this.server = server;
        this.port = port;
        this.dbName = dbName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}

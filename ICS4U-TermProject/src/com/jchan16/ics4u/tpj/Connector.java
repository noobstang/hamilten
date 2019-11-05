/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4u;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Jackie
 */
public class Connector {

    private String server = "jdbc:derby://localhost:1527/-----";
    private String user = "---";
    private String pass = "----";
    private Connection con;
    private Statement state;
    private ResultSet rs;
    private String command = "select*from----";
    
    public Connector(){
     command = "select*from----"; 
     pass = "----";
     user = "---";
     server = "jdbc:derby://localhost:1527/-----"; 
    }

    public String getServer() {
        return server;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public Connection getCon() {
        return con;
    }

    public ResultSet getResults() {
        return rs;
    }

    public Statement getStatement() {
        return state;
    }

    public Statement setStatement() {
        try {
            state = getCon().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return state;
    }

    public String getCommand() {
        return command;
    }
    
    public void changeCommand(String c){
        command=c;
    }

    public void getConnected() {
        try {
            con = DriverManager.getConnection(getServer(), getUser(), getPass());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getSet() {
        try {
            getConnected();
            rs = getStatement().executeQuery(getCommand());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

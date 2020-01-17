/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj.u3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Jackie
 */
/// Don't use this unless you have sql database
public class Connector {

    private String server;
    private String user;
    private String pass;
    private Connection con;
    private Statement state;
    private ResultSet rs;
    private String command = "select*from DUMMY";
    ///stores sql commands as objects
    private PreparedStatement ptmt1;

    public Connector() {
        command = "select*from Dummy";
        pass = "JAK2ELECBO";
        user = "JAK";
        server = "jdbc:derby://localhost:1527/ICS";;
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

    public void changeCommand(String c) {
        command = c;
    }

    public void changeSQL() {
        getConnected();
        setStatement();
        getSet();
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

    public void insertData(int i,boolean f, String s, String d, String a, String n) {
        try {
            getConnected();
            ptmt1 = con.prepareStatement("INSERT INTO DUMMY VALUES(?,?,?,?,?,?)");
            ptmt1.setBoolean(1, f);
            ptmt1.setString(2, s);
            ptmt1.setString(3, d);
            ptmt1.setString(4, a);
            ptmt1.setString(5, n);
            ptmt1.setInt(6,i);
            ptmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void getResultSet() {
        try {
            getConnected();
            setStatement();
            getSet();
            while (rs.next()) {
                int id = rs.getInt("ID");
                boolean fav = rs.getBoolean("Favorite");
                String sub = rs.getString("Subject");
                String date = rs.getString("Date");
                String amount = rs.getString("Amount");
                String notes = rs.getString("Notes");
                System.out.println(fav + " " + sub + " " + date + " " + amount + " " + notes+" "+id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

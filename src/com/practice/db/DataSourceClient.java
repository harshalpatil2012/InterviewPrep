package com.practice.db;

import java.sql.*;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAccessType;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;
import java.io.*;
import oracle.jdbc.OracleConnection;

public class DataSourceClient {
    public static void main(String[] args) {
        OracleConnection conn = null;
        javax.sql.DataSource ds = null;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");

        try {
            Context context = new InitialContext(env);
            // you will need to have create a Data Source with JNDI name testDS
            ds = (javax.sql.DataSource) context.lookup("testDS");
            conn = (OracleConnection) ds.getConnection();
            java.util.Properties prop = new java.util.Properties();
            System.out.println("Connection object details : " + conn);
            conn.close();
        } catch (Exception ex) {
            // handle the exception
            ex.printStackTrace();
        }
    }
}
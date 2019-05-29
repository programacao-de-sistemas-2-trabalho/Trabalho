/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author fernandokasemodel
 */
public class ConexaoJDBC {
    private final String url;
    private final String usuario;
    private final String senha;
    private final String driver;
    private Connection conn;
    
    // Singleton
    private static ConexaoJDBC instance;
    
    // Singleton
    private ConexaoJDBC() {
        this.driver = "com.mysql.jdbc.Driver";
        this.usuario = "fernando";
        this.senha = "123";
        this.url = "jdbc:mysql://localhost:3306/projeto";
    }
    
    // Singleton
    public static ConexaoJDBC getInstance() {
        if (instance == null)
            instance = new ConexaoJDBC();
        return instance;
    }
    
    public Connection getConnection() {
        if (this.conn != null)
            return this.conn;
        
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url,usuario,senha);
            
        } catch(Exception e) {
            e.printStackTrace();
            this.conn = null;
        }
        return conn;
    }
    
    public void close() {
        try{
            this.conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        this.conn = null;
    }
}
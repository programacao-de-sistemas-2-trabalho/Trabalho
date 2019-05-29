package aplicativo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernandokasemodel
 */
public class DBapp implements CRUDAplicativo {


    private Connection getConnection() {
        String url = "jdbc:derby://localhost:1527/projeto";
        String user = "fernando";
        String password = "123";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Aplicativo aplicativo) {
        try {
            Connection conn = getConnection();

            // 1o Salva a invoice
            String sql = "INSERT INTO aplicativo (id, nome, DESENVOVLVEDOR, numerodowloads) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, aplicativo.getId());
            pstm.setString(2, aplicativo.getNome());
            pstm.setString(3, aplicativo.getDesenvolvedor());
            pstm.setInt(4, aplicativo.getNumerodowloads());

            pstm.executeUpdate();

          
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean update(Aplicativo aplicativo) {
        try{
            Connection conn = getConnection();
            String sql = "UPDATE APLICATIVO SET NOME = ?, DESENVOVLDOR = ?, NUMERODEDOWLOAD = ?, WHERE ID = ?";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, aplicativo.getNome());
            pstm.setString(2, aplicativo.getDesenvolvedor());
            pstm.setDouble(3, aplicativo.getNumerodowloads());
            pstm.setLong(4, aplicativo.getNumerodowloads());
            pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Aplicativo aplicativo) {
        try {
            Connection conn = getConnection();
            // Apaga os itens
            String sql = "DELETE FROM aplicativo WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, (int) aplicativo.getId());
            pstm.executeUpdate();

            // Apaga a invoice
            sql = "DELETE FROM aplicativo WHERE id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, (int) aplicativo.getId());
            pstm.executeUpdate();

            conn.close();
            return true;
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    public Aplicativo get(long id) {
        try {
            Aplicativo resposta = new Aplicativo();
            Connection conn = getConnection();

            // Buscar Invoice
            String sql = "SELECT * FROM aplicativo WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) { return null; }

            Aplicativo app = new Aplicativo();
            
            
            app.setId(id);
            app.setNome(rs.getString("nome"));
            app.setDesenvolvedor(rs.getString("desenvolvedor"));
            app.setNumerodowloads(rs.getInt("numero de downloads"));

           
            conn.close();
            return app;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Aplicativo> getAll() {
        try{
            Connection conn = getConnection();
            String sql = "Select * from Aplicativo";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            List<Aplicativo> lista = new ArrayList<Aplicativo>();
        while (rs.next()){    
            Aplicativo app = new Aplicativo();
            app.setId(rs.getLong("id"));
            app.setNome(rs.getString("nome"));
            app.setDesenvolvedor(rs.getString("desenvolvedor"));
            app.setNumerodowloads(rs.getInt("numero de downloads"));
            lista.add(app);
        }
        stm.close();
        conn.close();
        return lista;
                     
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    }


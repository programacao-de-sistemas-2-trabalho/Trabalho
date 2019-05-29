package time;


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
public class DBtime implements CRUDTime{

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
    public boolean save(Time time) {
        try {
            Connection conn = getConnection();

            // 1o Salva a invoice
            String sql = "INSERT INTO time (id, nome, anodefundacao, cidade, estado) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, time.getId());
            pstm.setString(2, time.getNome());
            pstm.setInt(3, time.getAnodefundacao());
            pstm.setString(4, time.getCidade());
            pstm.setString(5, time.getEstado());

            pstm.executeUpdate();

          
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean update(Time time) {
        try{
            Connection conn = getConnection();
            String sql = "UPDATE TIMES SET NOME = ?, ANO = ?, CIDADE = ?, ESTADO = ?, WHERE ID = ?";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, time.getNome());
            pstm.setInt(2, time.getAno());
            pstm.setString(3, time.getCidade());
            pstm.setString(4, time.getEstado());
            pstm.setLong(5, time.getId());
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
    public boolean delete(Time time) {
        try {
            Connection conn = getConnection();
            // Apaga os itens
            String sql = "DELETE FROM time WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, time.getId());
            pstm.executeUpdate();

            // Apaga a invoice
            sql = "DELETE FROM time WHERE id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, time.getId());
            pstm.executeUpdate();

            conn.close();
            return true;
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Time get(long id) {
        try {
            Time resposta = new Time();
            Connection conn = getConnection();

            // Buscar Invoice
            String sql = "SELECT * FROM time WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) { return null; }

            Time t = new Time();
            
            
            t.setId(id);
            t.setNome(rs.getString("nome"));
            t.setAnodefundacao(rs.getInt("ano de fundacao"));
            t.setCidade(rs.getString("cidade"));
            t.setEstado(rs.getString("estado"));
            

           
            conn.close();
            return t;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Time> getAll() {
        try{
            Connection conn = getConnection();
            String sql = "Select * from Time";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            List<Time> lista = new ArrayList<Time>();
        while (rs.next()){    
            Time t = new Time();
            t.setId(rs.getLong("id"));
            t.setNome(rs.getString("nome"));
            t.setAnodefundacao(rs.getInt("ano de funadacao"));
            t.setCidade(rs.getString("cidade"));
            t.setEstado(rs.getString("estado"));
            lista.add(t);
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


  

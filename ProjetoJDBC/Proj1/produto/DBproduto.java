package produto;


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
public class DBproduto implements CRUDProduto {

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
    public boolean save(Produto produto) {
        try {
            Connection conn = getConnection();

            // 1o Salva a invoice
            String sql = "INSERT INTO produto (id, descricao, marca, preco) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, produto.getId());
            pstm.setString(2, produto.getDescricao());
            pstm.setString(3, produto.getMarca());
            pstm.setDouble(4, produto.getPreco());

            pstm.executeUpdate();

          
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean update(Produto produto) {
         try{
            Connection conn = getConnection();
            String sql = "UPDATE produto SET DESCRICAO = ?, MARCA = ?, PRECO = ?, WHERE ID = ?";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, produto.getDescricao());
            pstm.setString(2, produto.getMarca());
            pstm.setDouble(3, produto.getPreco());
            pstm.setLong(4, produto.getId());
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
    public boolean delete(Produto produto) {
        try {
            Connection conn = getConnection();
            // Apaga os itens
            String sql = "DELETE FROM produto WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, produto.getId());
            pstm.executeUpdate();

            // Apaga a invoice
            sql = "DELETE FROM produto WHERE id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, produto.getId());
            pstm.executeUpdate();

            conn.close();
            return true;
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    
     public Produto get(long id) {
        try {
            Produto resposta = new Produto();
            Connection conn = getConnection();

            // Buscar Invoice
            String sql = "SELECT * FROM produto WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) { return null; }

            Produto p = new Produto();
            
            
            p.setId(id);
            p.setDescricao(rs.getString("descricao"));
            p.setMarca(rs.getString("marca"));
            p.setPreco(rs.getInt("preco"));

           
            conn.close();
            return p;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Produto> getAll() {
        try{
            Connection conn = getConnection();
            String sql = "Select * from Produto";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            List<Produto> lista = new ArrayList<Produto>();
        while (rs.next()){    
            Produto p = new Produto();
            p.setId(rs.getLong("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setMarca(rs.getString("marca"));
            p.setPreco(rs.getDouble("preco"));
            lista.add(p);
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

  


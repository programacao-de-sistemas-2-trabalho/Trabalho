package produto;


import db.ConexaoJDBC;
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


public class ProdutoDAO {
    
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    public ProdutoDAO() {
        try {
            Connection conn = ConexaoJDBC.getInstance().getConnection();
            
            String sqlCreate = "INSERT INTO produtos(descricao,marca,preco) VALUES (?,?,?)";
            String sqlRead   = "SELECT * FROM produtos";
            String sqlUpdate = "UPDATE produtos SET descricao=?, marca=?, preco=? WHERE id=?";
            String sqlDelete = "DELETE FROM produtos WHERE id=?";
            
            this.stmtC = conn.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlRead);
            this.stmtU = conn.prepareStatement(sqlUpdate);
            this.stmtD = conn.prepareStatement(sqlDelete);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Produto criar(Produto produto) {
        try{
            this.stmtC.setString(1, produto.getDescricao());
            this.stmtC.setString(2, produto.getMarca());
            this.stmtC.setDouble(3, produto.getPreco());
            
            if (this.stmtC.executeUpdate() == 0) 
                return null;
            
            ResultSet rs = this.stmtC.getGeneratedKeys();
            
            if (!rs.next())
                return null;
                        
            long id = rs.getLong(1);
            produto.setId(id);
            return produto;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Produto> lerTodos() {
        List<Produto> produto = new ArrayList<>();
        
        try{
            ResultSet rs = this.stmtR.executeQuery();

            while (rs.next()) {
                Produto app = new Produto();
                
                app.setId(rs.getLong("id"));
                app.setDescricao(rs.getString("descricao"));
                app.setMarca(rs.getString("marca"));
                app.setPreco(rs.getInt("preco"));
                
                produto.add(app);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return produto;
    }
    
    public boolean atualizar(long id, Produto produto) {
        try{
            this.stmtU.setString(1, produto.getDescricao());
            this.stmtU.setString(2, produto.getMarca());
            this.stmtU.setDouble(3, produto.getPreco());
            this.stmtU.setLong(4, id);
            
            return this.stmtU.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean apagar(long id) {
        try{
            this.stmtD.setLong(1, id);
            
            return this.stmtD.executeUpdate() > 0;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String argumentos[]) {
        ProdutoDAO dao = new ProdutoDAO();
        
        Produto p = new Produto();
        p.setId(99999);
        p.setDescricao("celular");
        p.setMarca("apple");
        p.setPreco(638);
        
        Produto produtoSalvo = dao.criar(p);
        
        p.setDescricao("computador");
        p.setMarca("dell");
        p.setPreco(533);
        dao.atualizar(4, p);
        dao.apagar(1);
        
        List<Produto> produto = dao.lerTodos();
        
        
        System.out.println(produto.size());
    }
}
    
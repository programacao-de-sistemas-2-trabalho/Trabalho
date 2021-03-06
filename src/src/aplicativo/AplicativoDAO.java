package aplicativo;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernandokasemodel
 */

import db.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AplicativoDAO {
    
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    public AplicativoDAO() {
        try {
            Connection conn = ConexaoJDBC.getInstance().getConnection();
            
            String sqlCreate = "INSERT INTO aplicativos(nome,desenvolvedor,downloads) VALUES (?,?,?)";
            String sqlRead   = "SELECT * FROM aplicativos";
            String sqlUpdate = "UPDATE aplicativos SET nome=?, desenvolveor=?, downloads=? WHERE id=?";
            String sqlDelete = "DELETE FROM aplicativos WHERE id=?";
            
            this.stmtC = conn.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlRead);
            this.stmtU = conn.prepareStatement(sqlUpdate);
            this.stmtD = conn.prepareStatement(sqlDelete);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Aplicativo criar(Aplicativo aplicativo) {
        try{
            this.stmtC.setString(1, aplicativo.getNome());
            this.stmtC.setString(1, aplicativo.getDesenvolvedor());
            this.stmtC.setInt(2, aplicativo.getNumerodowloads());
            
            if (this.stmtC.executeUpdate() == 0) 
                return null;
            
            ResultSet rs = this.stmtC.getGeneratedKeys();
            
            if (!rs.next())
                return null;
                        
            long id = rs.getLong(1);
            aplicativo.setId(id);
            return aplicativo;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Aplicativo> lerTodos() {
        List<Aplicativo> aplicativo = new ArrayList<>();
        
        try{
            ResultSet rs = this.stmtR.executeQuery();

            while (rs.next()) {
                Aplicativo app = new Aplicativo();
                
                app.setId(rs.getLong("id"));
                app.setNome(rs.getString("nome"));
                app.setDesenvolvedor(rs.getString("desenvolvedor"));
                app.setNumerodowloads(rs.getInt("numero de downloads"));
                
                aplicativo.add(app);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return aplicativo;
    }
    
    public boolean atualizar(long id, Aplicativo aplicativo) {
        try{
            this.stmtU.setString(1, aplicativo.getNome());
            this.stmtU.setString(2, aplicativo.getDesenvolvedor());
            this.stmtU.setInt(3, aplicativo.getNumerodowloads());
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
        AplicativoDAO dao = new AplicativoDAO();
        
        Aplicativo app = new Aplicativo();
        app.setId(99999);
        app.setNome("facebook");
        app.setDesenvolvedor("fernando");
        app.setNumerodowloads(6382766);
        
        Aplicativo appSalvo = dao.criar(app);
        
        app.setNome("instagram");
        app.setDesenvolvedor("lucas");
        app.setNumerodowloads(538923);
        dao.atualizar(4, app);
        dao.apagar(1);
        
        List<Aplicativo> aplicativo = dao.lerTodos();
        
        
        System.out.println(aplicativo.size());
    }
}
    
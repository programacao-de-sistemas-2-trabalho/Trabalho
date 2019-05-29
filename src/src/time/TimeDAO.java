package time;


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
public class TimeDAO {
    
    private PreparedStatement stmtC;
    private PreparedStatement stmtR;
    private PreparedStatement stmtU;
    private PreparedStatement stmtD;
    
    public TimeDAO() {
        try {
            Connection conn = ConexaoJDBC.getInstance().getConnection();
            
            String sqlCreate = "INSERT INTO times(nome,ano,cidade,estado) VALUES (?,?,?,?)";
            String sqlRead   = "SELECT * FROM times";
            String sqlUpdate = "UPDATE times SET nome=?, ano=?, cidade=?, estado=? WHERE id=?";
            String sqlDelete = "DELETE FROM times WHERE id=?";
            
            this.stmtC = conn.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            this.stmtR = conn.prepareStatement(sqlRead);
            this.stmtU = conn.prepareStatement(sqlUpdate);
            this.stmtD = conn.prepareStatement(sqlDelete);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Time criar(Time time) {
        try{
            this.stmtC.setString(1, time.getNome());
            this.stmtC.setInt(2, time.getAnodefundacao());
            this.stmtC.setString(3, time.getCidade());
            this.stmtC.setString(4, time.getEstado());
            
            if (this.stmtC.executeUpdate() == 0) 
                return null;
            
            ResultSet rs = this.stmtC.getGeneratedKeys();
            
            if (!rs.next())
                return null;
                        
            long id = rs.getLong(1);
            time.setId(id);
            return time;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Time> lerTodos() {
        List<Time> time = new ArrayList<>();
        
        try{
            ResultSet rs = this.stmtR.executeQuery();

            while (rs.next()) {
                Time t = new Time();
                
                t.setId(rs.getLong("id"));
                t.setNome(rs.getString("nome"));
                t.setAnodefundacao(rs.getInt("ano de fundacao"));
                t.setCidade(rs.getString("cidade"));
                t.setEstado(rs.getString("estado"));
                
                time.add(t);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return time;
    }
    
    public boolean atualizar(long id, Time time) {
        try{
            this.stmtU.setString(1, time.getNome());
            this.stmtU.setInt(2, time.getAnodefundacao());
            this.stmtU.setString(3, time.getCidade());
            this.stmtU.setString(4, time.getEstado());
            this.stmtU.setLong(5, id);
            
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
        TimeDAO dao = new TimeDAO();
        
        Time t = new Time();
        t.setId(99999);
        t.setNome("corinthians");
        t.setAnodefundacao(1910);
        t.setCidade("sao paulo");
        t.setEstado("sp");
        
        Time timeSalvo = dao.criar(t);
        
        t.setNome("flamengo");
        t.setAnodefundacao(1920);
        t.setCidade("rio de janeiro");
        t.setEstado("rj");
        dao.atualizar(4, t);
        dao.apagar(1);
        
        List<Time> time = dao.lerTodos();
        
        
        System.out.println(time.size());
    }
}
    
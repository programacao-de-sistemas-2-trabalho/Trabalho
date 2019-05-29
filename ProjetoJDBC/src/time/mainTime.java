package time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernandokasemodel
 */
public class mainTime {
    public static void main(String[] args) {
        Time t = new Time();
           t.setId(1);
           t.setNome("Corinthians");
           t.setAnodefundacao(1910);
           t.setCidade("sao paulo");
           t.setEstado("sao paulo");
        Time t1 = new Time();
           t1.setId(1);
           t1.setNome("Palmeiras");
           t1.setAnodefundacao(1914);
           t1.setCidade("sao paulo");
           t1.setEstado("sao paulo");
        Time t2 = new Time();
           t2.setId(1);
           t2.setNome("Santos");
           t2.setAnodefundacao(1912);
           t2.setCidade("santos");
           t2.setEstado("sao paulo");
        
        DBtime db = new DBtime();
        db.save(t);
        db.save(t2);
        db.update(t2);
        db.delete(t);
}
}
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
public class mainApp {
    public static void main(String[] args) {
        Aplicativo app = new Aplicativo();
           app.setId(1);
           app.setNome("renato");
           app.setDesenvolvedor("fernando");
           app.setNumerodowloads(20);
        Aplicativo app1 = new Aplicativo();
           app1.setId(2);
           app1.setNome("lucas");
           app1.setDesenvolvedor("joao");
           app1.setNumerodowloads(55);
        Aplicativo app2 = new Aplicativo();
           app2.setId(2);
           app2.setNome("pedro");
           app2.setDesenvolvedor("erik");
           app2.setNumerodowloads(8724);
           
        DBapp db = new DBapp();
        db.save(app);
        db.save(app1);
        db.update(app1);
        db.delete(app);
        
                
    }
}

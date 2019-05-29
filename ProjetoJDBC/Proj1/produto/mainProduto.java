package produto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernandokasemodel
 */
public class mainProduto {
        public static void main(String[] args) {
        Produto p = new Produto();
           p.setId(1);
           p.setDescricao("celular");
           p.setMarca("apple");
           p.setPreco(4500);
        Produto p1 = new Produto();
           p1.setId(2);
           p1.setDescricao("tenis");
           p1.setMarca("nike");
           p1.setPreco(300);
        Produto p2 = new Produto();
           p2.setId(126);
           p2.setDescricao("camiseta");
           p2.setMarca("adidas");
           p2.setPreco(150);
           
        DBproduto db = new DBproduto();
        db.save(p);
        db.save(p2);
        db.update(p2);
        db.delete(p);
   
    
        
                
    }
}

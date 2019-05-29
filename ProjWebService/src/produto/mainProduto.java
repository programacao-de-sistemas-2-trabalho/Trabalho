package produto;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        RestProduto p = new RestProduto();
        try {
            p.run(new String[]{"server"});
        } catch (Exception ex) {
            Logger.getLogger(mainProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

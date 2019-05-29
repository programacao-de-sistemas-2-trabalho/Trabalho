/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernandokasemodel
 */
public class mainAplicativo {
    public static void main(String[] args) {
        RestApp app = new RestApp();
        try {
            app.run(new String[]{"server"});
        } catch (Exception ex) {
            Logger.getLogger(mainAplicativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

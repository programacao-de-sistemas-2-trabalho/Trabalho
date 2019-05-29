/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import produto.ProdutoDAO;
import produto.ProdutoResource;

/**
 *
 * @author fernandokasemodel
 */
public class RestProduto extends Application<Configuration> {
    
    @Override
    public void run(Configuration t,Environment e) throws Exception{
        ProdutoResource pR = new ProdutoResource(new ProdutoDAO());
       
        e.jersey().register(pR);
    }
}
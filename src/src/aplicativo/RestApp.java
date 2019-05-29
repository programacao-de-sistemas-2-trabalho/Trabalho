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
import ch.qos.logback.core.CoreConstants;
import db.ConexaoJDBC;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class RestApp extends Application<Configuration> {
    
    @Override
    public void run(Configuration t,Environment e) throws Exception{
        AplicativoResource appR = new AplicativoResource(new AplicativoDAO());
       
        e.jersey().register(appR);
    }
}

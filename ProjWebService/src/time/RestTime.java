/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
/**
 *
 * @author fernandokasemodel
 */
    public class RestTime extends Application<Configuration> {
    
    @Override
    public void run(Configuration t,Environment e) throws Exception{
        TimeResource tr = new TimeResource(new TimeDAO());
       
        e.jersey().register(tr);
    }
}

package aplicativo;


import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31840175
 */
public interface CRUDAplicativo {
    
    public boolean save(Aplicativo aplicativo);

    public boolean update(Aplicativo aplicativo);

    public boolean delete(Aplicativo aplicativo);

    public Aplicativo get(long id);

    public List<Aplicativo> getAll();
}

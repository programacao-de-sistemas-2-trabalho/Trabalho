package produto;


import time.Time;
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
public interface CRUDProduto {
    
    public boolean save(Produto produto);

    public boolean update(Produto produto);

    public boolean delete(Produto produto);

    public Produto get(long id);

    public List<Produto> getAll();
}

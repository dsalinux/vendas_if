package br.edu.iftm.vendasif.logic;

import br.edu.iftm.vendasif.dao.ProdutoDAO;
import br.edu.iftm.vendasif.entity.Produto;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author danilo
 */
public class ProdutoLogic implements Serializable {
    
    @Inject
    private ProdutoDAO dao;
    
    public Produto salvar(Produto produto){
        return dao.salvar(produto);
    }
    
    public List<Produto> buscar() {
        return dao.listar();
    }
    
}

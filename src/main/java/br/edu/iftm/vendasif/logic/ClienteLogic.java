package br.edu.iftm.vendasif.logic;

import br.edu.iftm.vendasif.dao.ClienteDAO;
import br.edu.iftm.vendasif.entity.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author danilo
 */
public class ClienteLogic implements Serializable {
    
    @Inject
    private ClienteDAO dao;
    
    public Cliente salvar(Cliente produto){
        return dao.salvar(produto);
    }
    
    public Cliente buscarPorCodigo(String codigo) {
        if(codigo == null || "".equals(codigo.trim())) {
            return null;
        }
        return dao.buscarPorCodigo(codigo);
    }
    
    public List<Cliente> buscar() {
        return dao.listar();
    }
    
}

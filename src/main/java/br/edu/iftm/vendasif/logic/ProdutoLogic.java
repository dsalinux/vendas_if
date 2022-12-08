package br.edu.iftm.vendasif.logic;

import br.edu.iftm.vendasif.dao.ProdutoDAO;
import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

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
    
    public List<Produto> buscarPorUsuario(Usuario usuario) {
        return dao.listarProdutosUsuario(usuario);
    }
    
}

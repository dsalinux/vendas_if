package br.edu.iftm.vendasif.dao;

import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class ProdutoDAO extends ConexaoDAO{

    
    public Produto salvar(Produto produto) {
        getConexao().getTransaction().begin();
        produto = getConexao().merge(produto);
        getConexao().getTransaction().commit();
        fecharConexao();
        return produto;
    }
    
    public List<Produto> listar() {
        try {
            List<Produto> produtos = getConexao().createQuery("from Produto", Produto.class).getResultList();
            return produtos;
        } finally {
            fecharConexao();
        }
    }
    public List<Produto> listarProdutosUsuario(Usuario usuario){
        try {
            Query query  = getConexao().createNativeQuery("select p.id, p.nome, p.valor from produto p join usuario_produto up on up.produto_id = p.id and up.usuario_id = :usuarioid", Produto.class);
            query.setParameter("usuarioid", usuario.getId());
            List<Produto> produtos = query.getResultList();
            return produtos;
        } finally {
            fecharConexao();
        }
    }
    
}

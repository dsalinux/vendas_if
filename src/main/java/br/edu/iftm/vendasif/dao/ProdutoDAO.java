package br.edu.iftm.vendasif.dao;

import br.edu.iftm.vendasif.entity.Produto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author danilo
 */
public class ProdutoDAO implements Serializable{

    EntityManager em = null;
    
    public EntityManager getConexao() {
        if(em == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory( "vendasifPU" );
            em = factory.createEntityManager();
        }
        return em;
    }
    
    public Produto salvar(Produto produto) {
        getConexao().getTransaction().begin();
        produto = getConexao().merge(produto);
        getConexao().getTransaction().commit();
        return produto;
    }
    
    public List<Produto> listar() {
        return getConexao().createQuery("from Produto", Produto.class).getResultList();
    }
    
}

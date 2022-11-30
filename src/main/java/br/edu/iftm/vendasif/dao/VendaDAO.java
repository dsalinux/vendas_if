package br.edu.iftm.vendasif.dao;

import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Venda;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class VendaDAO implements Serializable{

    EntityManager em = null;
    
    public EntityManager getConexao() {
        if(em == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory( "vendasifPU" );
            em = factory.createEntityManager();
        }
        return em;
    }
    
    public Venda salvar(Venda venda) {
        getConexao().getTransaction().begin();
        venda = getConexao().merge(venda);
        getConexao().getTransaction().commit();
        return venda;
    }
    
    public List<Venda> listar() {
        return getConexao().createQuery("from Venda", Venda.class).getResultList();
    }
    public List<Venda> listarVendasPorProduto(Produto produto) {
        Query query = getConexao().createQuery("from Venda v where v.produto = :produto and  v.cancelado is null order by v.dataVenda", Venda.class);
        query.setParameter("produto", produto);
        return query.getResultList();
    }
}

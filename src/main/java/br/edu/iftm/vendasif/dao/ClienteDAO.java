package br.edu.iftm.vendasif.dao;

import br.edu.iftm.vendasif.entity.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author danilo
 */
public class ClienteDAO implements Serializable {

    EntityManager em = null;

    public EntityManager getConexao() {
	if (em == null) {
	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("vendasifPU");
	    em = factory.createEntityManager();
	}
	return em;
    }

    public Cliente salvar(Cliente cliente) {
	getConexao().getTransaction().begin();
	cliente = getConexao().merge(cliente);
	getConexao().getTransaction().commit();
	getConexao().close();
	em = null;
	return cliente;
    }

    public Cliente buscarPorCodigo(String codigo) {
	try {
	    Cliente cliente = getConexao().createQuery("from Cliente cli where cli.codigoBarras = :codigo", Cliente.class)
		    .setParameter("codigo", codigo).getSingleResult();
	    getConexao().close();
	    em = null;
	    return cliente;
	} catch (NoResultException ex) {
	    return null;
	}
    }

    public Cliente buscarPorId(Integer id) {
	Cliente cli = getConexao().find(Cliente.class, id);
	getConexao().close();
	em = null;
	return cli;
    }
    
    public List<Cliente> listar() {
	try {
	    return getConexao().createQuery("from Cliente", Cliente.class).getResultList();
	} finally {
	    getConexao().close();
	    em = null;
	}
    }

}

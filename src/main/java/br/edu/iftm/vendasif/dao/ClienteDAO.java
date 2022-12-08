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
public class ClienteDAO extends ConexaoDAO {



    public Cliente salvar(Cliente cliente) {
	getConexao().getTransaction().begin();
	cliente = getConexao().merge(cliente);
	getConexao().getTransaction().commit();
        fecharConexao();
        return cliente;
    }

    public Cliente buscarPorCodigo(String codigo) {
	try {
	    Cliente cliente = getConexao().createQuery("from Cliente cli where cli.codigoBarras = :codigo", Cliente.class)
		    .setParameter("codigo", codigo).getSingleResult();
	    return cliente;
	} catch (NoResultException ex) {
	    return null;
	} finally {
            fecharConexao();
        }
    }

    public Cliente buscarPorId(Integer id) {
	Cliente cli = getConexao().find(Cliente.class, id);
	fecharConexao();
	return cli;
    }
    
    public List<Cliente> listar() {
	try {
	    return getConexao().createQuery("from Cliente", Cliente.class).getResultList();
	} finally {
	    fecharConexao();
	}
    }

}

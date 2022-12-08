package br.edu.iftm.vendasif.dao;

import br.edu.iftm.vendasif.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class UsuarioDAO extends ConexaoDAO {

    public Usuario salvar(Usuario usuario) {
        getConexao().getTransaction().begin();
        usuario = getConexao().merge(usuario);
        getConexao().getTransaction().commit();
        fecharConexao();
        return usuario;
    }

    public List<Usuario> listar() {
        try {
            return getConexao().createQuery("from Usuario", Usuario.class).getResultList();
        } finally {
            fecharConexao();
        }
    }

    public Usuario buscarPorLogin(String login) {
        try {
            Query query = getConexao().createQuery("from Usuario u where u.login = :login");
            query.setParameter("login", login);
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch (NoResultException ex) {
            return null;
        } finally {
            fecharConexao();

        }
    }

}

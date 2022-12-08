package br.edu.iftm.vendasif.logic;

import br.edu.iftm.vendasif.dao.UsuarioDAO;
import br.edu.iftm.vendasif.entity.Usuario;
import br.edu.iftm.vendasif.util.Assert;
import br.edu.iftm.vendasif.util.HashUtil;
import br.edu.iftm.vendasif.util.exception.ErroUsuarioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author danilo
 */
public class UsuarioLogic implements Serializable {
    
    @Inject
    private UsuarioDAO dao;
    
    public Usuario salvar(Usuario usuario) throws ErroUsuarioException{
        if(Assert.isStringEmpty(usuario.getLogin())) {
            throw new ErroUsuarioException("Login é obrigatório.");
        }
        if(Assert.isNull(usuario.getPerfil())) {
            throw new ErroUsuarioException("Perfil é obrigatório.");
        }
        if(Assert.isStringEmpty(usuario.getSenha()) && Assert.isStringEmpty(usuario.getSenha())) {
            throw new ErroUsuarioException("Senha é obrigatória.");
        }
        if(Assert.isStringNotEmpty(usuario.getNovaSenha())) {
            String senhaHash = HashUtil.sha256Hex(usuario.getNovaSenha());
            usuario.setSenha(senhaHash);
        }
        return dao.salvar(usuario);
    }
    
    public Usuario logar(String login, String senha) throws ErroUsuarioException {
        if(login == null || "".equals(login.trim())) {
            throw new ErroUsuarioException("Login ou senha inválidos");
        }
        Usuario usuario = dao.buscarPorLogin(login);
        if(usuario != null) {
            String senhaHash = HashUtil.sha256Hex(senha);
            System.out.println("\n\n\n Novo hash: " + senhaHash);
            System.out.println("\n\n\n Hash anterior: " + usuario.getSenha());
            System.out.println("\n\n");
            if(!senhaHash.endsWith(usuario.getSenha())){
                throw new ErroUsuarioException("Login ou senha inválidos");
            }
            return usuario;
        }
        throw new ErroUsuarioException("Login ou senha inválidos");
    }
    
    public List<Usuario> buscar() {
        return dao.listar();
    }
    
}

package br.edu.iftm.vendasif.bean;

import br.edu.iftm.vendasif.entity.Usuario;
import br.edu.iftm.vendasif.logic.UsuarioLogic;
import br.edu.iftm.vendasif.util.JSFUtil;
import br.edu.iftm.vendasif.util.exception.ErroSistemaException;
import br.edu.iftm.vendasif.util.exception.ErroUsuarioException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class LoginBean extends JSFUtil {
    
    @Getter @Setter
    private String login, senha;
    
    @Inject
    private UsuarioLogic logic;
    
    public String logar(){
        try {
            Usuario usuario = this.logic.logar(login, senha);
            if(usuario == null){
                addErroMensagem("Usuário ou senha inválidos.");
            } else {
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("usuarioLogado", usuario);
                return "/index.html?faces-redirect=true";
            }
            return "";
        } catch (ErroUsuarioException ex) {
            addAvisoMensagem(ex);
        }
        return "";
    }
    
    public String logoff() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    
}

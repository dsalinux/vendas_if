package br.edu.iftm.vendasif.bean;

import br.edu.iftm.vendasif.entity.Cliente;
import br.edu.iftm.vendasif.logic.ClienteLogic;
import br.edu.iftm.vendasif.util.JSFUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danilo
 */
@Named
@SessionScoped
public class ClienteBean extends JSFUtil{
    
    @Inject
    private ClienteLogic logic;
    
    @Getter @Setter
    private String codigo;
    
    @Getter
    private Cliente cliente;
    
    public void buscarCliente(){
        cliente = logic.buscarPorCodigo(codigo);
        if(cliente == null) {
            addAvisoMensagem("Cliente não encontrado.");
        }
        codigo = null;
    }
    public void fecharCliente() {
        cliente = null;
    }
    
    
}

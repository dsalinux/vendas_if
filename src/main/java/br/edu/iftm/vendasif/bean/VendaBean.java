package br.edu.iftm.vendasif.bean;

import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Usuario;
import br.edu.iftm.vendasif.entity.Venda;
import br.edu.iftm.vendasif.logic.ProdutoLogic;
import br.edu.iftm.vendasif.logic.VendaLogic;
import br.edu.iftm.vendasif.util.JSFUtil;
import br.edu.iftm.vendasif.util.exception.ErroUsuarioException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
public class VendaBean extends JSFUtil {
    
    @Inject
    private ProdutoLogic produtoLogic;
    
    @Inject
    private VendaLogic logic;
    
    @Getter
    private Produto produtoSelecionado;
    
    @Getter @Setter
    private String codigoCliente;
    
    private List<Produto> produtos;
    private Long ultimaAtualizacao = 0L;
    
    public void selecionarProduto(Produto produto) {
        produtoSelecionado = produto;
    }
    
    public void cancelarSelecao() {
        produtoSelecionado = null;
    }
    
    public List<Produto> getListaProdutos() {
        
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
        System.out.println("Usuário Logado: "+usuario);
        List<Produto> produtos = null; 
        Long agora = new Date().getTime();
        if((ultimaAtualizacao + 5000) < agora ){
            if(usuario != null) {
                if(Usuario.Perfil.ADMIN.equals(usuario.getPerfil())) {
                    produtos = produtoLogic.buscar();
                } else {
                    produtos = produtoLogic.buscarPorUsuario(usuario);
                    if(produtos != null && produtos.size() == 1) {
                        this.produtoSelecionado = produtos.get(0);
                    }
                }
            }
        }
        return produtos;
    }
    
    public void cancelarVenda(Venda venda) {
	try {
	    this.logic.cancelarVenda(venda);
	    addInfoMensagem("Cancelado com sucesso.");
	} catch (ErroUsuarioException ex) {
	    addAvisoMensagem(codigoCliente);
	}
    }
    
    public void vender(){
        try {
            logic.salvar(produtoSelecionado, codigoCliente);
        } catch (ErroUsuarioException ex) {
            addAvisoMensagem(ex);
        }
        codigoCliente = "";
    }
    
    public List<Venda> getItensVendidos() {
        return logic.listaVendasPorProduto(produtoSelecionado);
    }
    
}

package br.edu.iftm.vendasif.bean;

import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Venda;
import br.edu.iftm.vendasif.logic.ProdutoLogic;
import br.edu.iftm.vendasif.logic.VendaLogic;
import br.edu.iftm.vendasif.util.JSFUtil;
import br.edu.iftm.vendasif.util.exception.ErroUsuarioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VendaBean extends JSFUtil {
    
    @Inject
    private ProdutoLogic produtoLogic;
    
    @Inject
    private VendaLogic logic;
    
    @Getter
    private Produto produtoSelecionado;
    
    @Getter @Setter
    private String codigoCliente;
    
    public void selecionarProduto(Produto produto) {
        produtoSelecionado = produto;
    }
    
    public void cancelarSelecao() {
        produtoSelecionado = null;
    }
    
    public List<Produto> getListaProdutos() {
        return produtoLogic.buscar();
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

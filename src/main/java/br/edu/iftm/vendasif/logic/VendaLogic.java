package br.edu.iftm.vendasif.logic;

import br.edu.iftm.vendasif.dao.ClienteDAO;
import br.edu.iftm.vendasif.dao.VendaDAO;
import br.edu.iftm.vendasif.entity.Cliente;
import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Venda;
import br.edu.iftm.vendasif.util.exception.ErroUsuarioException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author danilo
 */
public class VendaLogic implements Serializable {

    @Inject
    private VendaDAO dao;
    
    @Inject
    private ClienteDAO clienteDAO;
    
    
    public Venda salvar(Produto produto, String codigoCliente) throws ErroUsuarioException {
        Cliente cli = clienteDAO.buscarPorCodigo(codigoCliente);
        if(cli == null) {
            throw new ErroUsuarioException("Cliente " + codigoCliente + " não encontrado.");
        }
        Venda venda = new Venda();
        venda.setCliente(cli);
        venda.setDataVenda(new Date());
        venda.setProduto(produto);
        return dao.salvar(venda);
    }
    
    
    public List<Venda> listaVendasPorProduto(Produto produto ){
        return dao.listarVendasPorProduto(produto);
    }
}

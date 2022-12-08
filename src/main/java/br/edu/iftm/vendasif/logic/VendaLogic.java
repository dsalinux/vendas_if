package br.edu.iftm.vendasif.logic;

import br.edu.iftm.vendasif.dao.ClienteDAO;
import br.edu.iftm.vendasif.dao.VendaDAO;
import br.edu.iftm.vendasif.entity.Cliente;
import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.entity.Venda;
import br.edu.iftm.vendasif.entity.vo.RelatorioVenda;
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
        if(cli.getSaldo().subtract(produto.getValor()).floatValue() < 0f) {
            throw new ErroUsuarioException("Cliente "+codigoCliente+" com saldo insuficiente para esta compra.");
        }
	cli.setSaldo(cli.getSaldo().subtract(produto.getValor()));
        Venda venda = new Venda();
        venda.setCliente(cli);
        venda.setDataVenda(new Date());
        venda.setProduto(produto);
	venda = salvar(venda);
	clienteDAO.salvar(cli);
        return venda;
    }
    
    public Venda salvar(Venda venda) throws ErroUsuarioException {
	if(venda.getCliente() == null){
	    throw new ErroUsuarioException("Cliente não informado.");
	}
	if(venda.getProduto() == null){
	    throw new ErroUsuarioException("Produto não informado.");
	}
	return dao.salvar(venda);
    }
    
    public void cancelarVenda(Venda venda) throws ErroUsuarioException{
	venda.setCancelado(new Date());
	Cliente cli = venda.getCliente();
	cli = clienteDAO.buscarPorId(cli.getId());
	System.out.println("Saldo antes: "+cli.getSaldo());
	cli.setSaldo(cli.getSaldo().add(venda.getProduto().getValor()));
	System.out.println("Saldo dpois: "+cli.getSaldo());
	cli = clienteDAO.salvar(cli);
	salvar(venda);
    }
    
    public List<Venda> listaVendasPorProduto(Produto produto ){
        return dao.listarVendasPorProduto(produto);
    }
    
    public List<RelatorioVenda> buscarRelatorioVendas(){
	return dao.buscarRelatorio();
    }
}

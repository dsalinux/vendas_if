/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.iftm.vendasif.bean;

import br.edu.iftm.vendasif.entity.Produto;
import br.edu.iftm.vendasif.logic.ProdutoLogic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author danilo
 */
@Named
@SessionScoped
public class ProdutoBean implements Serializable {
    
    @Inject
    private ProdutoLogic  logic;
    
    @Getter
    private Produto produto;
    @Getter
    private List<Produto> produtos = new ArrayList<>();
    
    @Getter
    private EstadoTela estadoTela = EstadoTela.BUSCA;
    
    private enum EstadoTela {
        BUSCA,
        NOVO,
        EDITA
    }
    
    public void novo() {
        produto = new Produto();
        estadoTela = EstadoTela.NOVO;
    }
    
    public void salvar() {
        logic.salvar(produto);
        this.produtos.add(produto);
        produto = new Produto();
        estadoTela = EstadoTela.BUSCA;
        
    }
    
    public void editar(Produto produto) {
        this.produto = produto;
        estadoTela = EstadoTela.EDITA;
    }
    
    public void buscar() {
        if(!estadoTela.equals(EstadoTela.BUSCA)) {
            estadoTela = EstadoTela.BUSCA;
            return;
        }
        produtos = logic.buscar();
    }
    
}

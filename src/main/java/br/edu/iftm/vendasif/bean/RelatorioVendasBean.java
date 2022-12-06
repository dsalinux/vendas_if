//<editor-fold defaultstate="collapsed" desc=" License Apache 2.0 ">
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//</editor-fold>

package br.edu.iftm.vendasif.bean;

import br.edu.iftm.vendasif.entity.vo.RelatorioVenda;
import br.edu.iftm.vendasif.logic.VendaLogic;
import br.edu.iftm.vendasif.util.JSFUtil;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author danilo
 */
@Named
@SessionScoped
public class RelatorioVendasBean extends JSFUtil{

    @Inject
    private VendaLogic logic;
    
    
    public List<RelatorioVenda> getRelatorioVendas() {
	return logic.buscarRelatorioVendas();
    }
    
}

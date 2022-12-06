//<editor-fold defaultstate="collapsed" desc=" License Apache 2.0 ">
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//</editor-fold>
package br.edu.iftm.vendasif.entity.vo;

import br.edu.iftm.vendasif.entity.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author danilo
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioVenda implements Serializable {

    @EqualsAndHashCode.Include
    private String nomeProduto;
    private BigDecimal totalVendido;

}

package br.edu.iftm.vendasif.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "venda")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_venda")
    private Date dataVenda;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cancelado")
    private Date cancelado;
    
}

package br.edu.iftm.vendasif.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String login;
    private String senha;
//    @ManyToOne
//    @JoinColumn(name = "produto_id")
//    private Produto produto;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
    
    @Transient
    private String novaSenha;
    
    public enum Perfil {
        VENDEDOR("Vendedor"),
        ADMIN("Administrativo");

        private String descricao;
        private Perfil(String descricao) {
            this.descricao = descricao;
        }
        public String getDescricao() {
            return descricao;
        }
    }
}

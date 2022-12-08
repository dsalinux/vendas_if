/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.iftm.vendasif.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author danilo
 */
public class ConexaoDAO implements Serializable {
    
    private EntityManager em = null;
    
    public EntityManager getConexao() {
        if (em == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("vendasifPU");
            em = factory.createEntityManager();
        }
        return em;
    }

    public void fecharConexao() {
        if (em != null) {
            em.clear();
//            em = null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transacao;

import static JPA.Dao.JPADao.getEntityManager;
import Utils.Mensagem;
import javax.persistence.EntityManager;

/**
 *
 * @author Raizen
 */
public abstract class Transacao {
    protected EntityManager em;
    protected Object Result;
    public abstract Object Transacao(Object... Params);
    

    public Object Realiza(Object... Params) {
        em = null;
        try {
            /////Inicia a Transacao
            em = getEntityManager();
            em.getTransaction().begin();
            
            /////Realiza a Transação
            Result = Transacao();
            
            /////Finaliza a Transação
            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em != null) {
                Mensagem.ExibirException(ex, "Transacao:36");
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return Result;
    }
}

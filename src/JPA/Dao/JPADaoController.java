package JPA.Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Raizen
 */
public abstract class JPADaoController {
    public static Class EntityClass;
    
    public JPADaoController(EntityManagerFactory emf, Class Obj) {
        this.emf = emf;
        JPADaoController.EntityClass = Obj;
    }
    public static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Método utilizado para salvar as informações de uma entidade.
     *
     * @param Obj
     * @return the object
     */
    public Object create(Object Obj) {
        EntityManager entityManager = getEntityManager();
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Salva os dados.
            entityManager.persist(Obj);
            // Finaliza a transação.
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return Obj;
    }

    /**
     * Método utilizado para alterar as informações de uma entidade.
     *
     * @param Obj
     * @return the object edited
     */
    public Object edit(Object Obj) {
        EntityManager entityManager = getEntityManager();
        Object oObj = Obj;
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Atualiza os dados.
            oObj = entityManager.merge(Obj);
            // Finaliza a transação.
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return oObj;
    }

    /**
     * Método que apaga o objeto do banco de dados.
     *
     * @param id
     * @return the object destroyed
     */
    public Object destroy(Object id) {
        EntityManager entityManager = getEntityManager();
        Object oObj = null;
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a pessoa na base de dados através do seu ID.
            oObj = entityManager.find(EntityClass, id);
            // Remove a pessoa da base de dados.
            entityManager.remove(oObj);
            // Finaliza a transação.
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return oObj;
    }

    public static List<Object> findObjectEntities() {
        return findObjectEntities(true, -1, -1);
    }

    public static List<Object> findObjectEntities(int maxResults, int firstResult) {
        return findObjectEntities(false, maxResults, firstResult);
    }

    protected static List<Object> findObjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntityClass));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Método que busca o objeto pelo ID.
     *
     * @param id
     * @return the object
     */
    public static Object findObjectByPK(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntityClass, id);
        } finally {
            em.close();
        }
    }
    /**
     * Método que mostra a quantidade de objetos na tabela
     * 
     * @return Retorna a Quantidade de Objetos
     */
    public static int getObjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            
            Root<Object> rt = cq.from(EntityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora.Base;

import JPA.Dao.JPADao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raizen
 */
public abstract class CtrlBase {

    protected static volatile EntityManagerFactory emf = null;
    protected JPADao Reference;
    protected String Message;

    /*
    * Exemplo de Uso setEntityReference(new Entidade());
    * A Entidade deve ser uma instancia de JPADao;
    *
     */
    protected abstract void setEntityReference();

    protected static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    protected CtrlBase(EntityManagerFactory em) {
        emf = em;
        setEntityReference();
    }

    protected Object Salvar(Object Result, Object... Params) {
        JPADao Obj = null;
        if (Result != null && Result instanceof JPADao) {
            Obj = (JPADao) Result;
            Obj = (JPADao) Obj.create();
            if (Obj != null) {
                Message = "Salvo com Sucesso";
            } else {
                Message = "Erro ao Salvar";
            }
        }
        return Obj;
    }

    protected Object Alterar(Object Result, Object... Params) {
        JPADao Obj = null;
        if (Result != null && Result instanceof JPADao) {
            Obj = (JPADao) Result;
            Obj = (JPADao) Obj.edit();
            if (Obj != null) {
                Message = "Modificado com Sucesso";
            } else {
                Message = "Erro ao Modificar";
            }
        }
        return Obj;
    }

    protected Object Remover(Object ID, Object... Params) {
        JPADao Obj = null;
        if (Reference != null && Reference instanceof JPADao) {
            Obj = (JPADao) Reference;
            Obj = (JPADao) Reference.destroy(ID);
            if (Obj != null) {
                Message = "Removido com Sucesso";
            } else {
                Message = "Erro ao Remover";
            }
        }
        return Obj;
    }

    public String getMsg() {
        return Message + "";
    }

    /**
     *
     * @param Params
     *
     * O primeiro Parametro deve ser uma instancia de JPADao O uma Classe, esta
     * deve ter um Construtor Vazio e Publico para ser Instanciado, e deve ser
     * instancia de JPADao;
     */
    public void setEntityReference(Object... Params) {
        try {
            if (Params != null && Params.length > 0 && Params[0] != null) {
                if (Params[0] instanceof Class) {
                    Class Obj = (Class) Params[0];
                    Object Instancia = Obj.newInstance();
                    if (Instancia != null && Instancia instanceof JPADao) {
                        Reference = (JPADao) Instancia;
                    }
                } else if (Params[0] instanceof JPADao) {
                    Reference = (JPADao) Params[0];
                }
            }
        } catch (InstantiationException ex) {
            Message = "Erro ao Instanciar o Objeto: CtrlBase:101";
        } catch (IllegalAccessException ex) {
            Message = "Erro de acesso ao Objeto: CtrlBase:103";
        } catch (Exception ex) {
            Message = "Erro ao Inserir Referencia ao Objeto: CtrlBase:105: " + ex.getMessage();
        }
    }

}

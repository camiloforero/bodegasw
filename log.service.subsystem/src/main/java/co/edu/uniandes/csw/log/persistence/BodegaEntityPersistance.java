package co.edu.uniandes.csw.log.persistence;

import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaItemInventarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BodegaEntityPersistance 
{

    @PersistenceContext(unitName = "BodegaMasterPU")
    protected EntityManager entityManager;
    
    
   public BodegaItemInventarioEntity createBodegaItemInventario(BodegaItemInventarioEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

}

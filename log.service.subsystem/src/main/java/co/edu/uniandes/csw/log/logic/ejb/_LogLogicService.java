
package co.edu.uniandes.csw.log.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.iteminventario.logic.dto.ItemInventarioDTO;
//import co.edu.uniandes.csw.bodega.logic.dto.BodegaDTO;
import co.edu.uniandes.csw.bodega.persistence.api.IBodegaPersistence;
import co.edu.uniandes.csw.iteminventario.persistence.api.IItemInventarioPersistence;
import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaItemInventarioEntity;
//import co.edu.uniandes.csw.bodega.master.persistence.api.IBodegaMasterPersistence;

import co.edu.uniandes.csw.log.logic.dto.LogDTO;
import co.edu.uniandes.csw.log.logic.api._ILogLogicService;
import co.edu.uniandes.csw.log.persistence.BodegaEntityPersistance;
import co.edu.uniandes.csw.log.persistence.api.ILogPersistence;

public abstract class _LogLogicService implements _ILogLogicService {

	@Inject
	protected ILogPersistence persistance;
        
        @Inject
        protected IBodegaPersistence bodegaPersistance;
        
        @Inject
        protected IItemInventarioPersistence itemInventarioPersistance;
        
        @Inject
	protected BodegaEntityPersistance persistance2;
        
       

	public LogDTO createLog(LogDTO log){
            
            ItemInventarioDTO iDTO = new ItemInventarioDTO();
            iDTO.setName(log.getName());
            iDTO.setCantidad(log.getCantidad());
            iDTO.setId(log.getId());
            iDTO.setProductoId(log.getProductoId());
            
            System.out.println("llega hasta acá 1");
            ItemInventarioDTO persistedItemInventarioDTO = itemInventarioPersistance.createItemInventario(iDTO);
            BodegaItemInventarioEntity entity = new BodegaItemInventarioEntity(log.getBodegaId(), persistedItemInventarioDTO.getId());
            persistance2.createBodegaItemInventario(entity);
            System.out.println("llega hasta acá 2");
            
            
		return persistance.createLog( log); 
    }

	public List<LogDTO> getLogs(){
		return persistance.getLogs(); 
	}

	public LogDTO getLog(Long id){
		return persistance.getLog(id); 
	}

	public void deleteLog(Long id){
	    persistance.deleteLog(id); 
	}

	public void updateLog(LogDTO log){
	    persistance.updateLog(log); 
	}	
}

package co.edu.uniandes.csw.log.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.iteminventario.logic.dto.ItemInventarioDTO;
import co.edu.uniandes.csw.bodega.persistence.api.IBodegaPersistence;
import co.edu.uniandes.csw.iteminventario.persistence.api.IItemInventarioPersistence;
import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaItemInventarioEntity;

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
            
            
            
            System.out.println("llega hasta ac� 1, con lista");
            List<ItemInventarioDTO> itemsProducto = itemInventarioPersistance.getItemInventariosProducto(log.getProductoId());
            boolean existe = false;
            int multiplica = Boolean.TRUE.equals(log.getEntra()) ? 1 : -1;
            for (int i = 0; i < itemsProducto.size() && !existe; i++) 
            {
                ItemInventarioDTO item = itemsProducto.get(i);
                if(item.getProductoId() == log.getProductoId())
                {
                    System.out.println(log.getEntra());
                    int cantidad = item.getCantidad() + multiplica*log.getCantidad();
                    if(cantidad < 0)
                        throw new RuntimeException("No hay objetos suficientes en la bodega");
                    item.setCantidad(cantidad);
                    itemInventarioPersistance.updateItemInventario(item);
                    existe = true;
                }
            }
            
            if(!existe)
            {
                if(multiplica == -1)
                    throw new RuntimeException("No hay objetos de este tipo en la bodega");
                ItemInventarioDTO iDTO = new ItemInventarioDTO();
                iDTO.setName(log.getName());
                iDTO.setCantidad(log.getCantidad());
                iDTO.setId(log.getId());
                iDTO.setProductoId(log.getProductoId());
            
                ItemInventarioDTO persistedItemInventarioDTO = itemInventarioPersistance.createItemInventario(iDTO);          
            
                System.out.println("Acaba de salir de la nueva query");      
            
            
                BodegaItemInventarioEntity entity = new BodegaItemInventarioEntity(log.getBodegaId(), persistedItemInventarioDTO.getId());
                persistance2.createBodegaItemInventario(entity);
            }
            
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
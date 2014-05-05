
package co.edu.uniandes.csw.bodega.master.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.bodega.master.persistence.api.IBodegaMasterPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class BodegaMasterPersistence extends _BodegaMasterPersistence  implements IBodegaMasterPersistence {

}
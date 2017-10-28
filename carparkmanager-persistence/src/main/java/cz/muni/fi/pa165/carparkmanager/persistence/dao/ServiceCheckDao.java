package cz.muni.fi.pa165.carparkmanager.persistence.dao;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import java.util.List;

/** 
 * @author Josef Marek
 */
public interface ServiceCheckDao {


    void create(ServiceCheck c);


    void delete(ServiceCheck c);

    
    void update(ServiceCheck c);


    ServiceCheck findById(Long id);


    List<ServiceCheck> findAll();

}

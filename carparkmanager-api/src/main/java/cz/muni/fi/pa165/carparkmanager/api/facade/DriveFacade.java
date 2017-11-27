package cz.muni.fi.pa165.carparkmanager.api.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.DriveDTO;
import java.util.List;

/**
 *
 * @author Jakub Juřena
 */
public interface DriveFacade {
    /**
     * Creates record and saves it to DB
     * 
     * @param drive drive to persist
     */
    void create(DriveDTO drive);
    
    /**
     * Removes drive from DB
     * 
     * @param drive drive to delete
     */
    void delete(DriveDTO drive);
    
    /**
     * Updates drive in DB
     * 
     * @param drive drive to update
     */
    void update(DriveDTO drive);
    
    /**
     * Finds drive by id.
     * 
     * @param id id of search drive 
     * @return null if Drive with selected id is not found, Drive otherwise
     */
    DriveDTO findById(Long id);
    
    /**
     * Returns all drives.
     * 
     * @return All drives in DB
     */
    List<DriveDTO> findAll();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Juřena
 */
@Service
public interface DriveService {
    /**
     * Creates record and saves it to DB
     * 
     * @param drive drive to persist
     */
    void create(Drive drive);
    
    /**
     * Removes drive from DB
     * 
     * @param drive drive to delete
     */
    void delete(Drive drive);
    
    /**
     * Updates drive in DB
     * 
     * @param drive drive to update
     */
    void update(Drive drive);
    
    /**
     * Finds drive by id.
     * 
     * @param id id of search drive 
     * @return null if Drive with selected id is not found, Drive otherwise
     */
    Drive findById(Long id);
    
    /**
     * Returns all drives.
     * 
     * @return All drives in DB
     */
    List<Drive> findAll();
}

package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Juřena
 */
@Service
public class DriveServiceImpl implements DriveService {
    
    @Autowired
    private DriveDao driveDao;

    @Override
    public void create(Drive drive) {
        driveDao.create(drive);
    }

    @Override
    public void delete(Drive drive) {
        driveDao.delete(drive);
    }

    @Override
    public void update(Drive drive) {
        driveDao.update(drive);
    }

    @Override
    public Drive findById(Long id) {
        return driveDao.findById(id);
    }

    @Override
    public List<Drive> findAll() {
        return driveDao.findAll();
    }
    
    
    
}

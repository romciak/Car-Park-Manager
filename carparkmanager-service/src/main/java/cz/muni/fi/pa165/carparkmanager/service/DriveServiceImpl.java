package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            driveDao.create(drive);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create Drive: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(Drive drive) {
        try {
            driveDao.delete(drive);
        } catch (Exception e) {
            throw new DataAccessException("Cannot delete Drive: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(Drive drive) {
        try {
            driveDao.update(drive);
        } catch (Exception e) {
            throw new DataAccessException("Cannot update Drive: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Drive findById(Long id) {
        try {
            return driveDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Cannot find Drive: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Drive> findAll() {
        try {
            return driveDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Cannot findAll Drive: " + e.getMessage(), e) {
            };
        }
    }

}

package cz.muni.fi.pa165.carparkmanager.service.facade;

import cz.muni.fi.pa165.carparkmanager.api.dto.DriveDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.DriveFacade;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import cz.muni.fi.pa165.carparkmanager.service.DriveService;
import cz.muni.fi.pa165.carparkmanager.service.utils.DataMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Juřena
 */
@Service
@Transactional
public class DriveFacadeImpl implements DriveFacade {

    @Autowired
    private DriveService driveService;

    @Autowired
    private DataMapper mapper;

    @Override
    public void create(DriveDTO driveDTO) {
        Drive drive = mapper.mapTo(driveDTO, Drive.class);
        driveService.create(drive);
    }

    @Override
    public void delete(DriveDTO driveDTO) {
        Drive drive = mapper.mapTo(driveDTO, Drive.class);
        driveService.delete(drive);
    }

    @Override
    public void update(DriveDTO driveDTO) {
        Drive drive = mapper.mapTo(driveDTO, Drive.class);
        driveService.update(drive);
    }

    @Override
    public DriveDTO findById(Long id) {
        Drive drive = driveService.findById(id);
        return mapper.mapTo(drive, DriveDTO.class);
    }

    @Override
    public List<DriveDTO> findAll() {
        List<Drive> drives = driveService.findAll();
        return mapper.mapTo(drives, DriveDTO.class);
    }

}

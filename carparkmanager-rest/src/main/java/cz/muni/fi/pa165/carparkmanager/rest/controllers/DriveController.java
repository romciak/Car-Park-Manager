package cz.muni.fi.pa165.carparkmanager.rest.controllers;

import cz.muni.fi.pa165.carparkmanager.api.dto.DriveDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.DriveFacade;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

/**
 *
 * @author romciak
 */
@RestController
@RequestMapping("/drive")
public class DriveController {

    @Inject
    private DriveFacade driveFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO createDrive(@RequestBody DriveDTO drive) {
        driveFacade.create(drive);
        return driveFacade.findById(drive.getId());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteDrive(@PathVariable("id") long id) {
        driveFacade.delete(driveFacade.findById(id));
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DriveDTO> findAllDrives() {
        return driveFacade.findAll();
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO findDrive(@PathVariable("id") long id) throws Exception {
        return driveFacade.findById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final DriveDTO updateDrive(@RequestBody DriveDTO drive) {
        driveFacade.update(drive);
        return driveFacade.findById(drive.getId());
    }

}

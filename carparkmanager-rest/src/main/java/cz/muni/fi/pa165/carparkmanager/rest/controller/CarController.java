package cz.muni.fi.pa165.carparkmanager.rest.controller;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.CarFacade;
import cz.muni.fi.pa165.carparkmanager.rest.utils.ControllerResponse;
import javax.validation.Valid;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jakub Juřena <445319>
 */

@Controller
@RequestMapping(value = "/pa165/rest/car/")
public class CarController {
    
    @Autowired
    private CarFacade carFacade;
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity createCar(@Valid @RequestBody CarDTO request) throws Exception {
        carFacade.create(request);
        return ControllerResponse.processResponse(request);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity updateCar(@Valid @RequestBody CarDTO request) throws Exception {
        
        if (request.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        final CarDTO carToUpdate = carFacade.findById(request.getId());
        if (carToUpdate != null) {
            carFacade.update(request);
            return ControllerResponse.processResponse(request);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeCar(@Min(0) @PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field ID is missing.");
        }
        
        final CarDTO carToDelete = carFacade.findById(id);
        if (carToDelete != null) {
            carFacade.delete(carToDelete);
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    
    @RequestMapping(value = "getById", method = RequestMethod.GET, params = "id")
    public ResponseEntity getCarById(@Min(0) @RequestParam("id") Long id) throws Exception {
        final CarDTO car = carFacade.findById(id);
        return ControllerResponse.processResponse(car);
    }
    
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public ResponseEntity getAllCars() {
        return ResponseEntity.ok(carFacade.findAll());
    }
           
}

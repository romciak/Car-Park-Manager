package cz.muni.fi.pa165.carparkmanager.api.dto;

/**
 *
 * Representing employee's dto object for authentication
 * 
 * @author Jakub Juřena <445319>
 */
public class EmployeeAuthenticateDTO {
    private Long id;
    private String passwordHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    
}

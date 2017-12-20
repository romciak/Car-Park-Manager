package cz.muni.fi.pa165.carparkmanager.api.dto;

/**
 *
 * Representing employee's dto object for authentication
 * 
 * @author Jakub Juřena <445319>
 */
public class EmployeeAuthenticateDTO {
    private String email;
    private String passwordHash;

    public EmployeeAuthenticateDTO(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    
}

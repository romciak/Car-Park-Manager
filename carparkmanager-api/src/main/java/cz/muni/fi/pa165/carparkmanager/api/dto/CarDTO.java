package cz.muni.fi.pa165.carparkmanager.api.dto;

import java.util.Objects;

/**
 *
 * @author Roman Nedelka
 */
public class CarDTO {

    private Long id;
    private String vin;
    private String brand;
    private String type;
    private String engineType;
    private String productionYear;
    private int kmCount;
    // private List<ServiceCheckDTO> serviceCheckList;
    // private List<DriveDTO> driveList;

    // TODO odkomentovat, ked budu hotove ostatne DTO + upravit toString()
    
    public CarDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public int getKmCount() {
        return kmCount;
    }

    public void setKmCount(int kmCount) {
        this.kmCount = kmCount;
    }

    /*public List<ServiceCheckDTO> getServiceCheckList() {
        return serviceCheckList;
    }

    public void setServiceCheckList(List<ServiceCheckDTO> serviceCheckList) {
        this.serviceCheckList = serviceCheckList;
    }

    public List<DriveDTO> getDriveList() {
        return driveList;
    }

    public void setDriveList(List<DriveDTO> driveList) {
        this.driveList = driveList;
    }*/
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + Objects.hashCode(this.vin);
        result = 37 * result + Objects.hashCode(this.brand);
        result = 37 * result + Objects.hashCode(this.type);
        result = 37 * result + Objects.hashCode(this.engineType);
        result = 37 * result + Objects.hashCode(this.productionYear);
        result = 37 * result + this.kmCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CarDTO)) {
            return false;
        }
        final CarDTO other = (CarDTO) obj;
        if (this.kmCount != other.kmCount) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.engineType, other.engineType)) {
            return false;
        }
        return Objects.equals(this.productionYear, other.productionYear);
    }

    @Override
    public String toString() {
        return "CarDTO{" + "id=" + id + ", vin=" + vin + ", brand=" + brand
                + ", type=" + type + ", engineType=" + engineType
                + ", productionYear=" + productionYear + ", kmCount=" + kmCount + '}';
    }

}

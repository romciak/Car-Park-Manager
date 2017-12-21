package cz.muni.fi.pa165.carparkmanager.api.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jakub Juřena
 */
public class CarCreateDTO {

    @NotNull
    private String vin;
    @NotNull
    private String brand;
    @NotNull
    private String type;
    @NotNull
    private String engineType;
    @NotNull
    private String productionYear;
    @Min(0)
    private int kmCount;
    
    public CarCreateDTO() {
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
        if (this.kmCount != other.getKmCount()) {
            return false;
        }
        if (!Objects.equals(this.vin, other.getVin())) {
            return false;
        }
        if (!Objects.equals(this.brand, other.getBrand())) {
            return false;
        }
        if (!Objects.equals(this.type, other.getType())) {
            return false;
        }
        if (!Objects.equals(this.engineType, other.getEngineType())) {
            return false;
        }
        return Objects.equals(this.productionYear, other.getProductionYear());
    }

    @Override
    public String toString() {
        return "CarDTO{vin=" + vin + ", brand=" + brand
                + ", type=" + type + ", engineType=" + engineType + ", productionYear="
                + productionYear + ", kmCount=" + kmCount + ", serviceCheckList.size="
                + '}';
    }

}

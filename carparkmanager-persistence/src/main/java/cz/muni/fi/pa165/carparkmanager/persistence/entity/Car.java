package cz.muni.fi.pa165.carparkmanager.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Car entity.
 *
 * @author Roman Nedelka
 */
@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String vin;

    @NotNull
    @Column(nullable = false)
    private String brand;

    @NotNull
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column(nullable = false, name = "engine_type")
    private String engineType;

    @NotNull
    @Column(nullable = false, name = "production_year")
    private String productionYear;

    @Column(nullable = true, name = "km_count")
    private int kmCount;

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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
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
        return "Car{" + "id=" + id + ", vin=" + vin + ", brand=" + brand + ", type=" + type
                + ", engineType=" + engineType + ", productionYear=" + productionYear + ", kmCount=" + kmCount + '}';
    }

}

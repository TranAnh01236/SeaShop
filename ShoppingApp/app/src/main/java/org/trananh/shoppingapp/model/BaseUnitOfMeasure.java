package org.trananh.shoppingapp.model;

import java.io.Serializable;
import java.util.List;

public class BaseUnitOfMeasure implements Serializable {
    private String id;
    private String value;
    private List<UnitOfMeasure> unitOfMeasures;

    public BaseUnitOfMeasure(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public BaseUnitOfMeasure(String id) {
        this.id = id;
    }

    public BaseUnitOfMeasure() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BaseUnitOfMeasure{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

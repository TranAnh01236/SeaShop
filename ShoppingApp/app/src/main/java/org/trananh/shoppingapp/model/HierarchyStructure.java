package org.trananh.shoppingapp.model;

import java.io.Serializable;
import java.util.List;

public class HierarchyStructure implements Serializable {
    private int id;
    private String value;

    private List<StructureValue> structureValues;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HierarchyStructure(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public HierarchyStructure(int id) {
        this.id = id;
    }

    public HierarchyStructure(){

    }

    @Override
    public String toString() {
        return "HierarchyStructure{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}

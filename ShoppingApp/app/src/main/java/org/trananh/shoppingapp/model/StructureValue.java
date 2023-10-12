package org.trananh.shoppingapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StructureValue implements Serializable {
    private String id;
    private String value;
    private String description;
    private int level;
    private String parentId;
    private String imageUrl;
    private HierarchyStructure type;
    private List<Product> products;
    private List<User> users;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public StructureValue(String id, String value, String description, int level, String parentId, String imageUrl, HierarchyStructure type) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.level = level;
        this.parentId = parentId;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public StructureValue(String id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public StructureValue(){
        this.products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.value;
    }
}

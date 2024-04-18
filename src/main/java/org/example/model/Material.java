package org.example.model;

public class Material {
    private final MaterialType type;
    private int quantity;

    public Material(MaterialType type) {
        this.type = type;
        this.quantity = 0;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Material: " + type.getName() + ", Quantity: " + quantity;
    }
}

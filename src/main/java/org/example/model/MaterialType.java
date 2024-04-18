package org.example.model;

public class MaterialType {
    private final String name;
    private final String descriptions;
    private final String icon;
    private final int maxCapacity;

    public MaterialType(String name, String descriptions, String icon, int maxCapacity) {
        this.name = name;
        this.descriptions = descriptions;
        this.icon = icon;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Gets the name of the material type.
     *
     * @return The name of the material type as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a description of the material type.
     *
     * @return The description of the material type as a String.
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * Gets the icon associated with the material type (optional).
     *
     * @return The icon path or identifier as a String (can be null).
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Gets the maximum capacity for this material type in a single warehouse.
     *
     * @return The maximum capacity as an integer.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }
}

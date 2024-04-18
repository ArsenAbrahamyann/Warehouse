package org.example.api;

import org.example.model.Material;
import org.example.model.MaterialType;
import org.example.exception.InsufficientMaterialException;
import org.example.exception.MaxCapacityReachedException;
import org.example.observer.WarehouseObserver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Warehouse {

    //    private static final Logger LOGGER = LoggerFactory.getLogger(Warehouse.class);
    private final String name;
    private final Map<MaterialType, Material> materials;
    private final Set<WarehouseObserver> warehouseObservers;

    public Warehouse(String name) {
        this.name = name;
        this.materials = new HashMap<>();
        this.warehouseObservers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a specified quantity of a material type to the warehouse.
     *
     * @param type     The type of material to add.
     * @param quantity The quantity of material to add.
     */
    public void addMaterial(MaterialType type, int quantity) throws MaxCapacityReachedException {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative.");
        }

        Material material = materials.get(type);
        if (material == null) {
            material = new Material(type);
            materials.put(type, material);
        }

        int newQuantity = material.getQuantity() + quantity;
        if (newQuantity > type.getMaxCapacity()) {
            throw new MaxCapacityReachedException("Cannot add more material, exceeding max capacity for " + type.getName());
        }

        material.setQuantity(newQuantity);
        notifyObservers(type, newQuantity);
    }

    /**
     * Removes a specified quantity of a material type from the warehouse.
     *
     * @param type     The type of material to remove.
     * @param quantity The quantity of material to remove.
     */
    public void removeMaterial(MaterialType type, int quantity) throws InsufficientMaterialException {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative.");
        }

        Material material = materials.get(type);
        if (material == null || material.getQuantity() < quantity) {
            throw new InsufficientMaterialException("Not enough " + type.getName() + " in the warehouse.");
        }

        material.setQuantity(material.getQuantity() - quantity);
        notifyObservers(type, material.getQuantity());
    }

    /**
     * Moves a specified quantity of a material type from this warehouse to another warehouse.
     *
     * @param destination The target warehouse to move the material to.
     * @param type        The type of material to move.
     * @param quantity    The quantity of material to move.
     */
    public void moveMaterial(Warehouse destination, MaterialType type, int quantity) throws MaxCapacityReachedException,
            InsufficientMaterialException {
        removeMaterial(type, quantity);
        try {
            destination.addMaterial(type, quantity);
        } catch (MaxCapacityReachedException e) {
            addMaterial(type, quantity);
            throw e;
        }
    }

    /**
     * Gets the current quantity of a specific material type in the warehouse.
     *
     * @param type The type of material to check.
     * @return The quantity of the material type as an integer, or 0 if the material is not present.
     */
    public int getMaterialQuantity(MaterialType type) {
        Material material = materials.get(type);
        return material != null ? material.getQuantity() : 0;
    }

    /**
     * Adds an observer to be notified about warehouse changes.
     *
     * @param warehouseObserver The WarehouseObserver to be added.
     */
    public void registerObserver(WarehouseObserver warehouseObserver) {
        warehouseObservers.add(warehouseObserver);
    }

    /**
     * Removes an observer from receiving warehouse change notifications.
     *
     * @param warehouseObserver The WarehouseObserver to be removed.
     */
    public void removeObserver(WarehouseObserver warehouseObserver) {
        warehouseObservers.remove(warehouseObserver);
    }

    /**
     * Notifies all registered {@link WarehouseObserver}s about changes to a material type in the warehouse.
     * <p>
     * This method is called internally by the warehouse whenever a material is added or removed.
     * It iterates through all registered observers and notifies them using the `onMaterialQuantityChanged` method.
     *
     * @param type     The type of material that has changed.
     * @param quantity The quantity involved in the change. A positive value indicates addition,
     *                 while a negative value indicates removal.
     */
    private void notifyObservers(MaterialType type, int quantity) {
        for (WarehouseObserver observer : warehouseObservers) {
            observer.onMaterialQuantityChanged(type, quantity);
        }
    }
}



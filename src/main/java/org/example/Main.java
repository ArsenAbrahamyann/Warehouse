package org.example;

import org.example.api.Warehouse;
import org.example.api.WarehouseManager;
import org.example.exception.InsufficientMaterialException;
import org.example.exception.MaxCapacityReachedException;
import org.example.model.MaterialType;
import org.example.observer.InventoryUI;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Main {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var warehouseManager = new WarehouseManager();
        var inventoryUI = new InventoryUI();

        warehouseManager.createWarehouse("Warehouse A");
        warehouseManager.createWarehouse("Warehouse B");

        var materialType1 = new MaterialType("Material 1", "Description 1", "Icon 1", 100);
        var materialType2 = new MaterialType("Material 2", "Description 2", "Icon 2", 150);

        for (Warehouse warehouse : warehouseManager.getWarehouses().values()) {
            warehouse.registerObserver(inventoryUI);
        }

        try {
            warehouseManager.getWarehouse("Warehouse A").addMaterial(materialType1, 50);
            warehouseManager.getWarehouse("Warehouse A").addMaterial(materialType2, 80);
            warehouseManager.getWarehouse("Warehouse B").addMaterial(materialType1, 70);
            warehouseManager.getWarehouse("Warehouse B").addMaterial(materialType2, 100);
        } catch (MaxCapacityReachedException e) {
//            LOGGER.error("Failed to add initial materials: {}", e.getMessage());
            System.out.printf("Failed to add initial materials: %s%n", e.getMessage());
        }

        simulateMaterialUpdates(warehouseManager, materialType1);
    }

    private static void simulateMaterialUpdates(WarehouseManager warehouseManager, MaterialType materialType) {
        try {
            warehouseManager.getWarehouse("Warehouse A").removeMaterial(materialType, 20);

            warehouseManager.getWarehouse("Warehouse B").addMaterial(materialType, 30);
        } catch (InsufficientMaterialException | MaxCapacityReachedException e) {
//            LOGGER.error("Failed to update materials: {}", e.getMessage());
            System.out.printf("Failed to update materials: %s%n", e.getMessage());
        }
    }
}

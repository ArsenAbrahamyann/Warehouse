package org.example.observer;

import org.example.model.MaterialType;
import org.example.observer.WarehouseObserver;

public class InventoryUI implements WarehouseObserver {
    private String mockInventoryDisplay = "Mock Inventory Display";

    @Override
    public void onMaterialQuantityChanged(MaterialType type, int quantity) {
        updateInventoryDisplay(type, quantity);
    }

    /**
     * Updates the inventory UI based on the material type, quantity, and addition/removal flag.
     * <p>
     * (This method can be further customized to interact with a specific UI framework)
     *
     * @param type     The type of material that was added or removed.
     * @param quantity The quantity of material that was added or removed.
     */
    private void updateInventoryDisplay(MaterialType type, int quantity) {
        mockInventoryDisplay = String.format("Inventory Update: %s %d", type.getName(), quantity);
        System.out.println(mockInventoryDisplay);
    }
}

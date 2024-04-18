package org.example.observer;

import org.example.model.MaterialType;

public interface WarehouseObserver {

    /**
     * Called when a material is added to the or removed from the warehouse.
     *
     * @param type     The type of material added.
     * @param quantity The quantity of material added.
     */
    void onMaterialQuantityChanged(MaterialType type, int quantity);
}

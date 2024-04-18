package org.example.api;

import java.util.HashMap;
import java.util.Map;

public class WarehouseManager {
    private final Map<String, Warehouse> warehouses;

    public WarehouseManager() {
        warehouses = new HashMap<>();
    }

    public void createWarehouse(String name) {
        warehouses.put(name, new Warehouse(name));
    }

    public Warehouse getWarehouse(String name) {
        return warehouses.get(name);
    }

    public Map<String, Warehouse> getWarehouses() {
        return warehouses;
    }
}

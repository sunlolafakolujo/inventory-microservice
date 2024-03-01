package com.inventory.appuserservice.staticdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public enum UserType {
    ADMIN("Amin"),
    MANAGER_SUPPLY_CHAIN("Manager Supply Chain"),
    STOCK_ANALYST("Stock Analyst"),
    BUYER("Buyer"),
    MANAGER_PURCHASING("Manager Purchasing"),
    MANAGER_FINANCE("Manager Finance"),
    SUPPLIER("Supplier");

    private final String userType;
}

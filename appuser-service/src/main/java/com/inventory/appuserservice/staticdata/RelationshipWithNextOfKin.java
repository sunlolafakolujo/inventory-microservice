package com.inventory.appuserservice.staticdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RelationshipWithNextOfKin {
    SPOUSE("Spouse"),
    PARENT("Parent"),
    SIBLING("Sibling");

    private final String relationshipWithNextOfKin;
}

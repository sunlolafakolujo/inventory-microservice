package com.inventory.appuserservice.makerchecker.dto;


import com.inventory.appuserservice.staticdata.EntityType;
import com.inventory.appuserservice.staticdata.RequestStatus;
import com.inventory.appuserservice.staticdata.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakerCheckerResponse {
    private String id;
    private EntityType entityType;
    private Long entityId;
    private RequestType requestType;
    private String oldState;
    private String newState;
    private RequestStatus requestStatus;
    private Long adminId;
    private Date createdAt;
    private Date updatedAt;
}

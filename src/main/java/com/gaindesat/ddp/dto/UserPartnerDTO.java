package com.gaindesat.ddp.dto;

import java.util.UUID;

public class UserPartnerDTO {
    private UUID userUuid;
    private UUID partnerUuid;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public UUID getPartnerUuid() {
        return partnerUuid;
    }

    public void setPartnerUuid(UUID partnerUuid) {
        this.partnerUuid = partnerUuid;
    }
}

package com.gaindesat.ddp.dto;

import java.util.UUID;

public class MemberDTO {

    private UUID uuid;
    private String username;
    private String email;
    private String fullName;
    private boolean status;

    public MemberDTO(UUID uuid, String username, String email, String fullName, boolean status) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

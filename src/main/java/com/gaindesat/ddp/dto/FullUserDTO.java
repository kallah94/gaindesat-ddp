package com.gaindesat.ddp.dto;

import java.util.List;
import java.util.UUID;
public class FullUserDTO {

    private UUID uuid;

    private boolean status;
    private String username;
    private String email;
    private String fullName;
    private String categoryName;

    private String partnerName;
    private List<String> roles;

    public FullUserDTO(UUID uuid, Boolean status, String username, String email, String fullName, String partnerName,
                       String categoryName, List<String> roles) {
        this.uuid = uuid;
        this.status = status;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.partnerName = partnerName;
        this.categoryName = categoryName;
        this.roles = roles;
    }

    public FullUserDTO() {

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public List<String > getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

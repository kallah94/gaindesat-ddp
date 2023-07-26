package com.gaindesat.ddp.dto;


import java.util.Objects;
import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private UUID partnerUUID;
    private UUID categoryUUID;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private boolean status;
    public UserDTO(UUID uuid,
                   UUID partnerUUID,
                   UUID categoryUUID,
                   String username,
                   String email,
                   String password,
                   String fullName,
                   boolean status) {
        this.uuid = uuid;
        this.partnerUUID = partnerUUID;
        this.categoryUUID = categoryUUID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
    }

    public UserDTO(UUID uuid, boolean status) {
        this.uuid = uuid;
        this.status = status;
    }

    public UserDTO(UUID uuid, String username, String email, String fullName, boolean status) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
    }

    public UserDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UUID getPartnerUUID() {
        return partnerUUID;
    }

    public void setPartnerUUID(UUID partnerUUID) {
        this.partnerUUID = partnerUUID;
    }

    public UUID getCategoryUUID() {
        return categoryUUID;
    }

    public void setCategoryUUID(UUID categoryUUID) {
        this.categoryUUID = categoryUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getUsername().equals(userDTO.getUsername()) && getEmail().equals(userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail(), getFullName());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", status=" + status +
                '}';
    }
}

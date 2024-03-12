package com.gaindesat.ddp.payload.response;

import java.util.List;
import java.util.UUID;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private UUID uuid;
  private String username;
  private String email;

  private UUID partnerUUID;
  private final List<String> roles;

  public JwtResponse(String accessToken, UUID uuid, String username, String email, UUID partnerUUID, List<String> roles) {
    this.token = accessToken;
    this.uuid = uuid;
    this.username = username;
    this.email = email;
    this.partnerUUID = partnerUUID;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public UUID getPartnerUUID() {
    return partnerUUID;
  }

  public void setPartnerUUID(UUID partnerUUID) {
    this.partnerUUID = partnerUUID;
  }
}

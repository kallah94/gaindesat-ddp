package com.gaindesat.ddp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(columnDefinition = "boolean default false")
  private boolean status;

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 250)
  @Email
  private String email;

  @NotBlank
  @Size(max = 250)
  private String fullName;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "partner_id")
  private Partner partner;

  public Partner getPartner() {
    return partner;
  }

  public void setPartner(Partner partner) {
    this.partner = partner;
  }

  public User() {
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public User(String username, String email, String password, String fullName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public UUID getUuid() {
    return uuid;
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


}

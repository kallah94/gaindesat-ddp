package com.gaindesat.ddp.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gaindesat.ddp.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private final UUID uuid;

  private final String username;

  private final String email;

  private final boolean status;

  private final UUID partnerUUID;

  @JsonIgnore
  private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(UUID uuid, String username, String email, boolean status, String password,
      Collection<? extends GrantedAuthority> authorities, UUID partnerUUID) {
    this.uuid = uuid;
    this.username = username;
    this.email = email;
    this.status = status;
    this.password = password;
    this.authorities = authorities;
    this.partnerUUID = partnerUUID;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getCategory().getPermissions().stream()
            .map(role -> new SimpleGrantedAuthority(role.getTitle()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority(user.getCategory().getCatName()));
    return new UserDetailsImpl(
        user.getUuid(),
        user.getUsername(), 
        user.getEmail(),
        user.isStatus(),
        user.getPassword(), 
        authorities,
        user.getPartner().getUuid());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getEmail() {
    return email;
  }

  public UUID getPartnerUUID() {
    return partnerUUID;
  }
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return status;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserDetailsImpl)) return false;
    UserDetailsImpl that = (UserDetailsImpl) o;
    return status == that.status
            &&
            Objects.equals(getUuid(), that.getUuid())
            &&
            Objects.equals(getUsername(), that.getUsername())
            &&
            Objects.equals(getEmail(), that.getEmail())
            &&
            Objects.equals(getPartnerUUID(), that.getPartnerUUID())
            &&
            Objects.equals(getPassword(), that.getPassword())
            &&
            Objects.equals(getAuthorities(), that.getAuthorities());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUuid(), getUsername(), getEmail(), status, getPartnerUUID(), getPassword(), getAuthorities());
  }
}

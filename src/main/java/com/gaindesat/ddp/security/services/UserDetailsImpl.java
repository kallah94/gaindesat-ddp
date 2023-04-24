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

  private UUID uuid;

  private String username;

  private String email;

  private boolean status;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(UUID uuid, String username, String email, boolean status, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.uuid = uuid;
    this.username = username;
    this.email = email;
    this.status = status;
    this.password = password;
    this.authorities = authorities;
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
        authorities);
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
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(uuid, user.uuid);
  }
}

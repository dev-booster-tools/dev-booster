package com.ytty.raja.model;

public interface User {

    Long getId();

    void setId(Long id);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Role getRole();

    void setRole(Role role);

    boolean isEnabled();

    void setEnabled(boolean enabled);
}

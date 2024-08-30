package com.carwash.carwash.domain.dtos.usuario;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Long empresa_moon;
    private boolean ativo;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmpresa_moon() {
        return empresa_moon;
    }

    public void setEmpresa_moon(Long empresa_moon) {
        this.empresa_moon = empresa_moon;
    }


    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

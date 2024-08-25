package com.carwash.carwash.core.domain;

public class Pessoa {
    
    private Long id;
    private Long moon_empresas;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;
    private String cpfcnpj;
    private String tipo;
    private String status;
    private String dataCadastro;

    public Pessoa(Long id, Long moon_empresas , String nome, String email, String senha, String telefone, String endereco, String cpfcnpj, String tipo, String status, String dataCadastro) {
        this.id = id;
        this.moon_empresas = moon_empresas;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpfcnpj = cpfcnpj;
        this.tipo = tipo;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoon_empresas() {
        return moon_empresas;
    }

    public void setMoon_empresas(Long moon_empresas) {
        this.moon_empresas = moon_empresas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}

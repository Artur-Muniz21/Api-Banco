package com.estagio.Api_Banco.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.estagio.Api_Banco.entities.enums.TipoUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;
    private String nomeCompleto;
    private String celular;
    private String cpf;
    
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "usuario")
    private List<Conta> conta;
    
    public Long getId() {
        return id;
    }

    public Usuario() {
    }

    public Usuario(String email, String senha, String nomeCompleto, String celular, String cpf, TipoUsuario tipo) {
        this.email = email;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.celular = celular;
        this.cpf = cpf;
        this.tipo = tipo;
    }

    private Usuario(Builder builder) {
        this.email = builder.email;
        this.senha = builder.senha;
        this.nomeCompleto = builder.nomeCompleto;
        this.celular = builder.celular;
        this.cpf = builder.cpf;
        this.tipo = builder.tipo;
    }

    public static class Builder {
        private String email;
        private String senha;
        private String nomeCompleto;
        private String celular;
        private String cpf;
        private TipoUsuario tipo;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder nomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
            return this;
        }

        public Builder celular(String celular) {
            this.celular = celular;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder tipo(TipoUsuario tipo) {
            this.tipo = tipo;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Conta> getConta() {
        return conta;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id);
    }
}

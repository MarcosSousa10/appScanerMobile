package com.othon.carvalho.model.auth;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "software_autoservico")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String usuario;    
    private Long vesao;
    private Date dt_versao;
    private String senha;
    public String getUsuario() {
        return usuario;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

   

    public Produto(Long id, String status, String usuario, Long vesao, Date dt_versao, String senha) {
        this.id = id;
        this.status = status;
        this.usuario = usuario;
        this.vesao = vesao;
        this.dt_versao = dt_versao;
        this.senha = senha;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getVesao() {
        return vesao;
    }
    public void setVesao(Long vesao) {
        this.vesao = vesao;
    }
    @Override
    public String toString() {
        return "Produto [id=" + id + ", vesao=" + vesao + ", status=" + status + ", usuario=" + usuario + ", dt_versao="
                + dt_versao + ", senha=" + senha + "]";
    }
    public Date getDt_versao() {
        return dt_versao;
    }
    public void setDt_versao(Date dt_versao) {
        this.dt_versao = dt_versao;
    }
    public Produto() {
    }
   


    
}
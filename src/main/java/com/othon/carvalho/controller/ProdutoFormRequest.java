package com.othon.carvalho.controller;

import java.util.Date;

import com.othon.carvalho.model.auth.Produto;



public class ProdutoFormRequest {
    private Long id;
    private String status;
    private String usuario;    
    private Long vesao;
    private Date dt_versao;
    private String senha;
    public String getUsuario() {
        return usuario;
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
    public Produto toModel(){
        return new Produto(id,status,usuario,vesao,dt_versao,senha);
    }
    
    public static ProdutoFormRequest fromModel(Produto logins){
        return new ProdutoFormRequest(logins.getId(),logins.getStatus(),logins.getUsuario(),logins.getVesao(),logins.getDt_versao(),logins.getSenha());
    }
 
 

    public ProdutoFormRequest(Long id, String status, String usuario, Long vesao, Date dt_versao, String senha) {
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
    public Date getDt_versao() {
        return dt_versao;
    }
    public void setDt_versao(Date dt_versao) {
        this.dt_versao = dt_versao;
    }

    
    public ProdutoFormRequest() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "ProdutoFormRequest [id=" + id + ", status=" + status + ", usuario=" + usuario + ", vesao=" + vesao
                + ", dt_versao=" + dt_versao + ", senha=" + senha + "]";
    }

}

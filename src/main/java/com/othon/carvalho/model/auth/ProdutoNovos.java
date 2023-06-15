package com.othon.carvalho.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtosnovos")
public class ProdutoNovos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private Integer quantidade;
    private Integer codigo;
    private String barra;
    private String status;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getBarra() {
        return barra;
    }
    public void setBarra(String barra) {
        this.barra = barra;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
  
    public ProdutoNovos(Integer id, String descricao, Integer quantidade, Integer codigo, String barra, String status) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.barra = barra;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProdutoNovo [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", codigo="
                + codigo + ", barra=" + barra + ", status=" + status + "]";
    }
    public ProdutoNovos() {
    }
    
}

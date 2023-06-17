package com.othon.carvalho.model.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtonovo")
public class ProdutoNovoss {
    @Id
    private Integer codprod;
    private String descricao;
    private Integer quantidade;
    private Integer codfornec;
    private String codbarra;
    private String status;
    private String unidade;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCodfornec() {
        return this.codfornec;
    }

    public void setCodfornec(Integer codfornec) {
        this.codfornec = codfornec;
    }

    public Integer getCodprod() {
        return this.codprod;
    }

    public void setCodprod(Integer codprod) {
        this.codprod = codprod;
    }

    public String getCodbarra() {
        return this.codbarra;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setCodbarra(String codbarra) {
        this.codbarra = codbarra;
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



    @Override
    public String toString() {
        return "ProdutoNovoss [codprod=" + codprod + ", descricao=" + descricao + ", quantidade=" + quantidade
                + ", codfornec=" + codfornec + ", codbarra=" + codbarra + ", status=" + status + ", unidade=" + unidade
                + "]";
    }

    public ProdutoNovoss(Integer codprod, String descricao, Integer quantidade, Integer codfornec, String codbarra,
            String status, String unidade) {
        this.codprod = codprod;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.codfornec = codfornec;
        this.codbarra = codbarra;
        this.status = status;
        this.unidade = unidade;
    }

    public ProdutoNovoss() {
    }

    



}

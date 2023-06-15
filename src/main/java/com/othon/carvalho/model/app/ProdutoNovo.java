package com.othon.carvalho.model.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtosnovos")
public class ProdutoNovo {
    @Id
    private Integer codprod;

    private String descricao;
    private Integer quantidade;
    private Integer codfornec;

    private String codbarra;
    private String status;

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
        return "ProdutoNovo [codfornec=" + codfornec + ", descricao=" + descricao + ", quantidade=" + quantidade
                + ", codprod=" + codprod + ", codbarra=" + codbarra + ", status=" + status + "]";
    }

    public ProdutoNovo(Integer codfornec, String descricao, Integer quantidade, Integer codprod, String codbarra,
            String status) {
        this.codfornec = codfornec;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.codprod = codprod;
        this.codbarra = codbarra;
        this.status = status;
    }

    public ProdutoNovo() {
    }

}

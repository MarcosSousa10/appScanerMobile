package com.othon.carvalho.controller;

import com.othon.carvalho.model.app.ProdutoNovoss;
import com.othon.carvalho.model.auth.Produto;

public class Produtonovo {
      private Integer codprod;

    private String descricao;
    private Integer quantidade;
    private Integer codfornec;
    private String unidade;

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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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
        return "Produtosnovos [codprod=" + codprod + ", descricao=" + descricao + ", quantidade=" + quantidade
                + ", codfornec=" + codfornec + ", unidade=" + unidade + ", codbarra=" + codbarra + ", status=" + status
                + "]";
    }

    public Produtonovo(Integer codprod, String descricao, Integer quantidade, Integer codfornec, String unidade,
            String codbarra, String status) {
        this.codprod = codprod;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.codfornec = codfornec;
        this.unidade = unidade;
        this.codbarra = codbarra;
        this.status = status;
    }

    public Produtonovo() {
    }

    public ProdutoNovoss toModel(){
        return new ProdutoNovoss(codprod,descricao,quantidade,codfornec,codbarra,status,unidade);
    }
    
    public static Produtonovo fromModel(ProdutoNovoss logins){
        return new Produtonovo(logins.getCodprod(),logins.getDescricao(),logins.getQuantidade(),logins.getCodfornec(),logins.getCodbarra(),logins.getStatus(),logins.getUnidade());
    }
 
}

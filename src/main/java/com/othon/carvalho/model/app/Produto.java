package com.othon.carvalho.model.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto{
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long codprod;
    private String descricao;
    private String unidade;
    private Long codauxiliar;
    private BigDecimal precoatual;
    private String dtalteracaopreco;
    private BigDecimal precoanterior;
    private BigDecimal precopromocional;
    private String fimvigencia;
    private String produtopai;
    private Integer estoquecd;
    private Integer estoqueothon;
    private Integer estoquedispothon;
    private Integer qtdiassemvenda;
    private Integer qtachegar;
    private Integer giromes;
    private Integer qtvendida3meses;

    
  
}


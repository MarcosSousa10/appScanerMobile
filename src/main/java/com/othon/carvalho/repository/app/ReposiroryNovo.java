package com.othon.carvalho.repository.app;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.othon.carvalho.model.app.Produto;
import com.othon.carvalho.model.app.ProdutoNovo;

import jakarta.transaction.Transactional;

public interface ReposiroryNovo extends CrudRepository<ProdutoNovo, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "drop table othon.produtosnovos", nativeQuery = true)
    void drop();

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "create table produtosnovos as (SELECT T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, '     ' AS status, SUM(V.QT) AS QUANTIDADE FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F "+
"WHERE V.DTCANCEL IS NULL AND F.CODFORNEC = T.CODFORNEC AND V.codprod = T.codprod AND V.codoper = 'E' AND V.DTMOV = TO_DATE(SYSDATE, 'DD-MM-YYYY') - 1 AND V.CODFILIAL = 1 AND T.CODEPTO < 500 GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar)", nativeQuery = true)
    void create();
 
    @Query(value = "select DISTINCT * from produtosnovos ", nativeQuery = true)
    List<ProdutoNovo> fins();
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE othon.produtosnovos SET status = ?1 WHERE codbarra = ?2", nativeQuery = true)
    void status(String status, String descricao);
}
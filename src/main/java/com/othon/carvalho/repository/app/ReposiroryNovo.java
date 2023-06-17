package com.othon.carvalho.repository.app;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.othon.carvalho.model.app.Produto;
import com.othon.carvalho.model.app.ProdutoNovoss;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import jakarta.transaction.Transactional;

public interface ReposiroryNovo extends CrudRepository<ProdutoNovoss, Long> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "drop table othon.produtonovo", nativeQuery = true)
    void drop();

    @Query(value = "SELECT T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, '     ' AS status, SUM(V.QT) AS QUANTIDADE FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F WHERE V.DTCANCEL IS NULL AND F.CODFORNEC = T.CODFORNEC AND V.codprod = T.codprod AND V.codoper = 'E' AND v.dtmov=  TO_DATE(?1,'DD/MM/YYYY') -1 AND V.CODFILIAL = 1 AND T.CODEPTO < 500 GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar", nativeQuery = true)
    List<ProdutoNovoss> select(String now);

    @Query(value = "create table produtonovo as (SELECT T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, '     ' AS status, SUM(V.QT) AS QUANTIDADE FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F "
            +
            "WHERE V.DTCANCEL IS NULL AND F.CODFORNEC = T.CODFORNEC AND V.codprod = T.codprod AND V.codoper = 'E' AND v.dtmov = TO_DATE(?1,'DD/MM/YYYY') - 1 AND V.CODFILIAL = 1 AND T.CODEPTO < 500 GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar)", nativeQuery = true)
    List<ProdutoNovoss> data(String date);

    @Query(value = "select * from produtonovo", nativeQuery = true)
    List<ProdutoNovoss> fins();

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE othon.produtonovo SET status = ?1 WHERE codbarra = ?2", nativeQuery = true)
    void status(String status, String descricao);

    @Modifying
    @Query(value = "INSERT INTO othon.produtonovo (CODFORNEC, codprod, descricao, unidade, codbarra, QUANTIDADE) SELECT T.CODFORNEC,  T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, SUM(V.QT) AS QUANTIDADE FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F WHERE V.DTCANCEL IS NULL AND F.CODFORNEC = T.CODFORNEC AND v.codprod = t.codprod AND v.codoper = 'E' AND V.DTMOV = TO_DATE(CURRENT_DATE, 'DD-MM-YYYY')-1 AND V.CODFILIAL = 1 AND T.CODEPTO < 500 GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar", nativeQuery = true)
    List<ProdutoNovoss> fetchData(String now);

}
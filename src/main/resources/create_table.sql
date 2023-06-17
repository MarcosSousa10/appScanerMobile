
create table produtosnovos as (SELECT T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, '     ' AS status, SUM(V.QT) AS QUANTIDADE FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F "+
"WHERE V.DTCANCEL IS NULL AND F.CODFORNEC = T.CODFORNEC AND V.codprod = T.codprod AND V.codoper = 'E' AND v.dtmov=  TO_DATE(?1,'DD/MM/YYYY') -1 AND V.CODFILIAL = 1 AND T.CODEPTO < 500 GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar)
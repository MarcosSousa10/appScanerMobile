package com.othon.carvalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.othon.carvalho.repository.app.ReposiroryNovo;
import com.othon.carvalho.repository.app.Repositoryo;
import com.othon.carvalho.model.app.Produto;
import com.othon.carvalho.model.app.ProdutoNovoss;
import com.othon.carvalho.repository.auth.Repositoryoo;
import com.othon.carvalho.service.RelatorioVendasService;
import com.othon.carvalho.util.DateUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class PerguntasRespostasController {
    @Autowired
    private Repositoryo consulta;
    @Autowired
    private Repositoryoo login;
    @Autowired
    private ReposiroryNovo Novo;
	@Autowired
	private RelatorioVendasService relatorioVendasService;
    	@GetMapping("/relatorio-vendas")
	public ResponseEntity<byte[]> relatorioVendas(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "inicio", required= false, defaultValue = "") String inicio,
			@RequestParam(value = "fim", required= false, defaultValue = "") String fim
	){
		Date dataInicio = DateUtils.fromString(inicio);
		Date dataFim = DateUtils.fromString(fim, true);
		
		if(dataInicio == null) {
			dataInicio = DateUtils.DATA_INICIO_PADRAO;
		}
		
		if(dataFim == null) {
			dataFim = DateUtils.hoje(true);
		}
		
		var relatorioGerado = relatorioVendasService.gerarRelatorio(id, dataInicio, dataFim);
		var headers = new HttpHeaders();
		var fileName = "relatorio-vendas.pdf";
		headers.setContentDispositionFormData("inline; filename=\"" +fileName+ "\"", fileName);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		var responseEntity = new ResponseEntity<>(relatorioGerado, headers, HttpStatus.OK);
        
		return responseEntity ;
	}
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> findCode(@PathVariable String codigo) {
        Optional<Produto> list = consulta.findAll(codigo);
        return new ResponseEntity<>(list, HttpStatus.valueOf(202));

    }

    @GetMapping("/codauxiliar/{codigo}")
    public ResponseEntity<?> codauxiliar(@PathVariable String codigo) {
        Optional<Produto> list = consulta.find(codigo);
        return new ResponseEntity<>(list, HttpStatus.valueOf(202));

    }

    @GetMapping("/versaoget/{versao}")
    public ResponseEntity<?> versao(@PathVariable String versao) {
        List<com.othon.carvalho.model.auth.Produto> list = login.versao(versao);
        return new ResponseEntity<>(list, HttpStatus.valueOf(202));

    }

    @GetMapping("/numeroVersao/{versao}")
    public ResponseEntity<?> numeroVersao(@PathVariable String versao) {
        Optional<com.othon.carvalho.model.auth.Produto> list = login.ver(versao);
        if (list.isPresent()) {
            com.othon.carvalho.model.auth.Produto produto = list.get();
            return new ResponseEntity<>(produto, HttpStatus.valueOf(202));
        } else {
            String mensagem = "1";
            return ResponseEntity.ok(mensagem);
        }
    }

    @GetMapping("/login/{usuario}/{senha}")
    public ResponseEntity<?> Login(@PathVariable String usuario, @PathVariable String senha) {
        Optional<com.othon.carvalho.model.auth.Produto> optionalProduto = login.Login(usuario, senha);

        if (optionalProduto.isPresent()) {
            com.othon.carvalho.model.auth.Produto produto = optionalProduto.get();
            return new ResponseEntity<>(produto, HttpStatus.valueOf(202));
        } else {
            String mensagem = "Usuario não encontrado.";
            return ResponseEntity.ok(mensagem);
        }
    }

    @GetMapping("/validarCadastro/{usuario}")
    public ResponseEntity<?> Login(@PathVariable String usuario) {
        Optional<com.othon.carvalho.model.auth.Produto> list = login.consultarUsuarioJaCadastrado(usuario);
        return new ResponseEntity<>(list, HttpStatus.valueOf(202));

    }

    @PutMapping("/validarCadastro/{versao}/{id}")
    public void validarVersao(@PathVariable String versao, @PathVariable String id) {
        login.validarVersao(versao, id);
    }

    @PutMapping("/validarData/{data}/{id}")
    public void data(@PathVariable String data, @PathVariable String id) {
        login.data(data, id);
    }

    // JSON
    @PostMapping("/Cadastro")
    public void Cadastro(@RequestBody Map<String, String> params) {
        String senha = params.get("senha");
        String status = params.get("status");
        String usuario = params.get("usuario");

        login.post(senha, status, usuario);
    }

    // Form
    // @PostMapping("/Cadastro")
    // public void Cadastro(@RequestParam String senha, @RequestParam String status,
    // @RequestParam String usuario) {
    // login.post(senha, status, usuario);
    // }

    // curl -X POST -d "senha=123456&status=activo&usuario=juan"
    // http://localhost:8080/Cadastro
    // curl -X POST -H "Content-Type: application/json" -d '{"senha": "123456",
    // "status": "activo", "usuario": "juan"}' http://localhost:8080/Cadastro

    @PostMapping("/usuario")
    public ProdutoFormRequest salvarr(@RequestBody ProdutoFormRequest loginss) {
        com.othon.carvalho.model.auth.Produto entidade = loginss.toModel();
        login.save(entidade);

        return ProdutoFormRequest.fromModel(entidade);
    }

    @GetMapping("/tudo")
    public Iterable<com.othon.carvalho.model.auth.Produto> obterProduto() {
        return login.findAll();
    }

    
    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoFormRequest> GetById(@PathVariable Long id) {
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente = login.findById(id);
        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var produto = produtoExistente.map(ProdutoFormRequest::fromModel).get();
        return ResponseEntity.ok(produto);
    }

    // void esta retornando um responseentity sem corpo
    @PutMapping("/put/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto) {
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente = login.findById(id);
        if (produtoExistente.isEmpty()) {
            // public void => throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        com.othon.carvalho.model.auth.Produto entidade = produto.toModel();
        entidade.setId(id);
        login.save(entidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente = login.findById(id);
        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        login.delete((produtoExistente.get()));
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/tudoo")
    public List<ProdutoNovoss> dataeselect() {
        LocalDate localDate = LocalDate.now();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String data = format.format(date);
        return Novo.fetchData(data);
    }

    @GetMapping("/select")
    public List<ProdutoNovoss> select() {
        LocalDate localDate = LocalDate.now();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String data = format.format(date);
        return Novo.select(data);
    }
@Autowired
private JdbcTemplate jdbcTemplate;
    @GetMapping("/tu")
    public String createData() {
        
        LocalDate localDate = LocalDate.now();

        java.sql.Date date = java.sql.Date.valueOf(localDate);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String data = format.format(date);

    String sqls = "SELECT T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, '     ' AS status, SUM(V.QT) AS QUANTIDADE " +
                 "FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F " +
                 "WHERE V.DTCANCEL IS NULL " +
                 "AND F.CODFORNEC = T.CODFORNEC " +
                 "AND V.codprod = T.codprod " +
                 "AND V.codoper = 'E' " +
                 "AND v.dtmov = TO_DATE(?,'DD/MM/YYYY') - 1 " +
                 "AND V.CODFILIAL = 1 " +
                 "AND T.CODEPTO < 500 " +
                 "GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar";

    List<ProdutoNovoss> resultado = jdbcTemplate.query(sqls, new BeanPropertyRowMapper<>(ProdutoNovoss.class), data);
        StringBuilder sql = new StringBuilder("CREATE TABLE othon.produtonovo (");

        // Percorre os campos do primeiro objeto da lista
        ProdutoNovoss primeiroObjeto = resultado.get(0);
        Field[] fields = primeiroObjeto.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            String fieldType = getSQLType(field.getType());

            sql.append(fieldName).append(" ").append(fieldType);

            if (i < fields.length - 1) {
                sql.append(", ");
            }
        }

        sql.append(")");

    jdbcTemplate.execute(sql.toString());
        return "Tabela Criada!";

    }

private String getSQLType(Class<?> javaType) {
    if (javaType == Integer.class || javaType == int.class) {
        return "INT";
    } else if (javaType == Long.class || javaType == long.class) {
        return "BIGINT";
    } else if (javaType == String.class) {
        return "VARCHAR(255)";
    } else if (javaType == Date.class || javaType == LocalDate.class) {
        return "DATE";
    } else if (javaType == Double.class || javaType == double.class) {
        return "DOUBLE";
    }
    // Add more mappings for other Java types as needed

    return "UNKNOWN";
}


@GetMapping("/in")
public String insertData() {
    LocalDate localDate = LocalDate.now();
    java.sql.Date date = java.sql.Date.valueOf(localDate);
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    String data = format.format(date);

    String selectSql = "SELECT T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar AS codbarra, '     ' AS status, SUM(V.QT) AS QUANTIDADE " +
             "FROM othon.pcprodut T, othon.pcmov V, othon.PCFORNEC F " +
             "WHERE V.DTCANCEL IS NULL " +
             "AND F.CODFORNEC = T.CODFORNEC " +
             "AND V.codprod = T.codprod " +
             "AND V.codoper = 'E' " +
             "AND v.dtmov = TO_DATE(?,'DD/MM/YYYY') - 1 " +
             "AND V.CODFILIAL = 1 " +
             "AND T.CODEPTO < 500 " +
             "GROUP BY T.CODFORNEC, T.codprod, T.descricao, T.unidade, T.codauxiliar";

    List<ProdutoNovoss> resultado = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(ProdutoNovoss.class), data);

    String insertSql = "INSERT INTO othon.produtonovo (CODFORNEC, codprod, descricao, unidade, codbarra, status, QUANTIDADE) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";

    for (ProdutoNovoss produto : resultado) {
        jdbcTemplate.update(insertSql, produto.getCodfornec(), produto.getCodprod(), produto.getDescricao(),
                produto.getUnidade(), produto.getCodbarra(), produto.getStatus(), produto.getQuantidade());
    }

    return "Produtos Inseridos!";
}
@GetMapping("/tudoNovo")
    public List<ProdutoNovoss> obterProdutosNovos() {
        return Novo.fins();
    }

    @PutMapping("/statusNovo/{status}/{descricao}")
    public void statusNovo(@PathVariable String status, @PathVariable String descricao) {
        Novo.status(status, descricao);
    }

    @DeleteMapping("/drop")
    public void data() {
        Novo.drop();
    }

}
package com.othon.carvalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.othon.carvalho.repository.app.ReposiroryNovo;
import com.othon.carvalho.repository.app.Repositoryo;
import com.othon.carvalho.model.app.Produto;
import com.othon.carvalho.model.app.ProdutoNovo;
import com.othon.carvalho.model.auth.ProdutoNovos;

import com.othon.carvalho.repository.auth.Repositoryoo;

import java.util.Collection;
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
//JSON
    @PostMapping("/Cadastro")
public void Cadastro(@RequestBody Map<String, String> params) {
    String senha = params.get("senha");
    String status = params.get("status");
    String usuario = params.get("usuario");
    
    login.post(senha, status, usuario);
}

//Form
//     @PostMapping("/Cadastro")
//     public void Cadastro(@RequestParam String senha, @RequestParam String status, @RequestParam String usuario) {
//     login.post(senha, status, usuario);
// }

// curl -X POST -d "senha=123456&status=activo&usuario=juan" http://localhost:8080/Cadastro
// curl -X POST -H "Content-Type: application/json" -d '{"senha": "123456", "status": "activo", "usuario": "juan"}' http://localhost:8080/Cadastro

	@PostMapping("/usuario")
    public ProdutoFormRequest salvarr (@RequestBody ProdutoFormRequest loginss){
        com.othon.carvalho.model.auth.Produto entidade=loginss.toModel();
		login.save(entidade);

        return ProdutoFormRequest.fromModel(entidade);
    }
    @GetMapping("/tudo")
    public Iterable<com.othon.carvalho.model.auth.Produto> obterProduto(){
        return login.findAll();
    }
    @GetMapping("/tudoNovo")
    public List<ProdutoNovo> obterProdutosNovos(){
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
    @GetMapping("/create")
    public void create() {
        Novo.create();
    }
    @GetMapping("/select")
    public void select() {
    }
    @GetMapping("/selects")
    public List<ProdutoNovo> selects(){
        return Novo.select();

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoFormRequest> GetById(@PathVariable Long id){
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente= login.findById(id);
        if(produtoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var produto= produtoExistente.map(ProdutoFormRequest::fromModel).get();
        return ResponseEntity.ok(produto);
    }
    //void esta retornando um responseentity sem corpo
    @PutMapping("/put/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto){
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente=login.findById(id);
        if(produtoExistente.isEmpty()){
       //public void =>     throw new ResponseStatusException(HttpStatus.NOT_FOUND);
         return ResponseEntity.notFound().build();
        }
        com.othon.carvalho.model.auth.Produto entidade=produto.toModel();
        entidade.setId(id);
        login.save(entidade);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Optional<com.othon.carvalho.model.auth.Produto> produtoExistente=login.findById(id);
        if(produtoExistente.isEmpty()){
            return ResponseEntity.notFound().build(); 
        }
        login.delete((produtoExistente.get()));
        return ResponseEntity.noContent().build();

    }
}
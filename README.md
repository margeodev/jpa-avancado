# Curso Jpa Avançado/Algaworks
---
## 2. Iniciando com JPA
### 2.6 Buscando objetos por identificador

```
@Test
public void atualizarPorReferenciaTest() {
    Produto produto = entityManager.find(Produto.class, 1L);
    String nomeProd = produto.getNome();
    String nomeNovo = "Nova descricao";
    produto.setNome(nomeNovo);
}
```

### 2.9. Inserindo o primeiro objeto com o método persist

```
@Test
public void inserirPrimeiroObjeto() {
    Produto produto = buildProduto();
    entityManager.getTransaction().begin();
    entityManager.persist(produto);
    entityManager.getTransaction().commit();
}
```

### 2.10. Removendo objetos com o método remove
```
@Test
public void removerObjeto() {
    Produto produto = entityManager.find(Produto.class, 1L);
    entityManager.getTransaction().begin();
    entityManager.remove(produto);
    entityManager.getTransaction().commit();
}
```

### 2.11. Atualizando objetos com o método merge
```
@Test
public void inserirObjetoPorMergeTest() {
    Produto produto = buildProduto();
    entityManager.getTransaction().begin();
    entityManager.merge(produto);
    entityManager.getTransaction().commit();
}
```
### 2.12. Atualizando objetos gerenciados
```
@Test
public void atualizarObjetoGerenciadoTest() {
    Produto produto = entityManager.find(Produto.class, 1L);
    String novaDescricao = "Nova descrição";
    produto.setDescricao(novaDescricao);
    entityManager.getTransaction().begin();
    entityManager.getTransaction().commit();
}
```
### 2.13. Salvando objetos com merge
```
@Test
public void atualizarObjetoGerenciadoTest() {
    Produto produto = entityManager.find(Produto.class, 1L);
    String novaDescricao = "Nova descrição";
    produto.setDescricao(novaDescricao);
    entityManager.getTransaction().begin();
    entityManager.getTransaction().commit();
}
```
---
## 3. Mapeamento básico
### 3.5. Mapeando enumerações com @Enumerated
Salva o valor em string no banco de dados ao invés de um número para representar a posição do enum.
```
@Enumerated(EnumType.STRING)
private StatusPagamento status;
```

### 3.6. Mapeando objetos embutidos com @Embeddable
Persiste em uma mesma tabela do banco classes diferentes
```
@Embeddable // TORNA A CLASSE "EMBUTÍVEL"
public class EnderecoEntrega {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
```
```
@Embedded // USADO NA CLASSE QUE VAI USAR A CLASSE EMBUTÍVEL
private EnderecoEntrega enderecoEntrega;
```









```

```







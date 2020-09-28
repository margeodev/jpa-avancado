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
---
## 4. Mapeamento de relacionamentos
### 4.2. Mapeando relacionamentos muito-para-um com @ManyToOne

```
@Entity
public class Pedido extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
```
O trecho de códico acima irá criar automaticamente na tabela **pedido** uma coluna formada pelo nome do atributo que está mapeado + o nome do atributo chave dele (cliente + id = cliente_id), o nome dessa coluna pode ser redefinido através da anotação **@JoinColumn(name = "novo_nome")**

### 4.7. Removendo objetos referenciados por outras entidades

Uma das formas de se remover entidades relacionadas é limpar todas as referências da base antes de remover a entidade que a referencia.

```
@Test
public void testeRelacionamento() {
    Pedido pedido = entityManager.find(Pedido.class, 1L);

    entityManager.getTransaction().begin();
    pedido.getItens().forEach(itemPedido -> entityManager.remove(itemPedido));
    entityManager.remove(pedido);
    entityManager.getTransaction().commit();
}
```

### 4.8. Mapeando relacionamentos muitos-para-muitos com @ManyToMany e @JoinTable
Cria uma tabela intermediária pra guardar as relações. 
Para usar essa anotação devem ser escolhidas duas entidades, uma vai ser o owner e a outra a non-owner.

Definindo a entidade owner:
A entidade owner além da anotação **@ManyToMany** também deve conter a anotação **@JoinTable** com os seguintes atributos:
* name - define o nome da tabela.
* joinColumns - define a coluna que vai referenciar o id da entidade owner.
* inverseJoinColumns - define a coluna que vai referenciar o id da entidade non-owner.

```
@Entity
public class Produto extends BaseEntity {
    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;
}
```


A entidade non-owner pode receber opcionalmente apenas a anotação **@ManyToMany(mappedBy="atributo_mapeado_na_entidade_owner")**, onde o **mappedBy** especifica o nome da propriedade que também foi mapeada com o **@ManyToMany** da entidade owner para poder buscar os metadados.

```
@Entity
public class Categoria extends BaseEntity {
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
}

```

### 4.9. Mapeamento relacionamentos um-para-um com @OneToOne
No relacionamento **um-para-um** deve ser definida uma entidade owner, a non-owner é opcional, o mapeamento da entidade non-owner não altera a estrutura da tabela no banco.

Entidade principal
```
@Entity
public class PagamentoCartao extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
```
Entidade secundária (opcional)
```
@Entity
public class Pedido extends BaseEntity {
    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;
}
```


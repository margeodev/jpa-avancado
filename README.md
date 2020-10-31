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
Obs: Para o mapeamento **@OneToOne**, opcionalmente também pode ser usada a estratégia de criação de uma outra tabela com o **@JoinTable** como usada no **@ManyToMany**

### 4.12. Entendendo o funcionamento de Eager e Lazy Loading
Por padrão, os atributos de uma entidade que tem algum relacionamento mas não são listas, são carregados automaticamente na consulta (Eager) e os atributos que são listas não são carregados automaticamente (Lazy), mas podem ser alterados usando o atributo **fetch**. Os atributos lazy só serão carregados na hora que forem usados e só serão carregados uma vez por objeto.
```
@Entity
public class Pedido extends BaseEntity {
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens; //Forma de load padrão: Lazy 

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER) //Altera o padrão de leitura do atributo do tipo lista
    private List<ItemPedido> itens;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Forma de load padrão: Eager 
  
}

@Test
public void testeComportamento() {
    Pedido pedido = entityManager.find(Pedido.class, 1L);
    pedido.getItens().isEmpty(); // Faz a segunda consulta ao banco para executar o método isEmpty()
}
```

### 4.13. Para o que serve o atributo optional?
Deve ser usado nos atributos que devem estar salvos antes de salvar a entidade "pai". Essa anotação também altera a consulta do relacionamento de left outer join para inner join melhorando o desempenho na consulta. 

No exemplo de código abaixo, a entidade Pedido (pai) só será persistida após a entidade Cliente já ter sido persistida. 

Pode ser usado nas anotações **@OneToOne** e **@ManyToOne**
```
@Entity
public class Pedido extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
```

## 5. Conhecendo o EntityManager
### 5.1. Estados e ciclo de vida dos objetos
As entidades podem passar por 4 estados diferentes:
* Transient - Estado inicial do objeto quando é criado. Pode ir para Managed
* Managed - Estado gerenciado do objeto. Pode ir para Detached ou Removed
* Removed - Objeto em estado para ser removido. Pode ir para Detached ou Managed
* Detached - Objeto desanexado.
```
@Test
public void analisarEstados() {
    Cliente cliente = new Cliente(); // Estado Transient
    Cliente novo = entityManager.merge(cliente); // De transient para Managed
    Cliente cliente1 = entityManager.find(Cliente.class, 1L); // Estado Managed
    entityManager.remove(cliente1); // Estado Removed
    entityManager.persist(cliente1); // De Removed para Managed
    entityManager.detach(cliente1); // Estado Detached
}
```

### 5.2. Entendendo o cache de primeiro nível
Uma entidade buscada no banco fica no cache de primeiro nível 
```
@Test
public void verificarCache() {
    Cliente cliente = entityManager.find(Cliente.class, 1L); // O retorno desse método traz um objeto e deixa ele no cache 1
    Cliente cliente2 = entityManager.find(Cliente.class, cliente.getId()) // Verifica se o objeto já está em cache, como o objeto está em cache, não faz uma nova consulta ao banco    
}
```

## 5.6. Callbacks para eventos do ciclo de vida
Callbacks são métodos que podem ser criados na própria entidade a partir de anotações e serão executados de acordo com o ciclo de vida da entidade.
* @PrePersist - Um método com essa anotação é executado antes do objeto gerenciado ser persistido.
* @PreUpdate -  Um método com essa anotação é executado antes do objeto gerenciado ser atualizado.
* @PostPersist -  Um método com essa anotação é executado após do objeto gerenciado ser persistido.
* @PostUpdate -  Um método com essa anotação é executado após do objeto gerenciado ser atualizado.
* @PostLoad -  Um método com essa anotação é executado após do objeto gerenciado ser carregado.
```
@PrePersist
public void aoPersistir() {
    dataCriacao = LocalDateTime.now();
}
```
Obs: O hibernate já possui anotações que atualizam automaticamente datas de acordo com o ciclo de vida da entidade:
```
@Entity
public class Pedido extends BaseEntity {

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @UpdateTimestamp
    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;
}
```
## 5.7. Listeners para eventos do ciclo de vida
São usados para fazer integração entre entidades, ou seja, executar ações em outras entidades após determinados eventos.
Para criar um listener basta criar uma classe comum com o método que será executado e anotar com alguma anotação de callback, em seguida, a classe que será "escutada" deve ser anotada com **@EntityListeners({ NovaClasseListener.class })**. Dentro da anotação **@EntityListeners** pode ser passado um array de classes do tipo listener.

```
public class GerarNotaFiscalListener {
    private NotaFiscalService service = new NotaFiscalService();

    @PrePersist // Callback
    @PreUpdate // Callback
    public void gerar(Pedido pedido) {
        if(pedido.isPago() && pedido.getNotafiscalId() == null) {
            service.gerar(pedido);
        }
    }
}

@EntityListeners({ GerarNotaFiscalListener.class }) // Informa que o listener GerarNotaFiscalListener está escutando a classe pedido
@Entity
@Table(name = "pedido")
public class Pedido extends BaseEntity {
    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;
   
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }
}
   
```

## Mapeamento avançado
### Conhecendo detalhes da anotação @Column
```
@Column(name = "data_criacao", updatable = false) // Impede que o atributo seja atualizado
private LocalDateTime dataCriacao;

@Column(name = "data_ultima_atualizacao", insertable = false) // Impede que o atributo seja criado manualmente
private LocalDateTime dataUltimaAtualizacao;
```

### 6.5. Mapeando chave composta com @EmbeddedId
Uma das formas de se mapear uma chave composta é através da anotação **@EmbeddedId**
* Deve ser criada uma classe que vai guardar os ids que irão compor a chave composta, essa classe deve implementar Serializable.
* Essa classe deve ser anotada com **@Embeddable**
* A entidade que terá a chave composta deve ter um atributo do tipo que foi criado e deve ser anotada com **@EmbeddedId**

```
@Embeddable
public class ItemPedidoId implements Serializable {
    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Long pedidoId;

    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long produtoId;
}

@Entity
public class ItemPedido {
    @EmbeddedId
    private ItemPedidoId id;
}
```

### 6.8. Declarando propriedades transientes com @Transient
Essa anotação serve para que os atributos de entidades sejam ignorados pelo JPA, ou seja, a tabela não terá a coluna que representaria esse atributo.


### 6.9. Mapeando coleções de tipos básicos com @ElementCollection
De forma similar ao **@ManyToMany**, também cria uma tabela para armazenar as relações, mas ao contrário do manyToMany, não relaciona uma coleção de entidades, apenas tipos básicos como String, int, etc

* A anotação @Column(name = "nome_custom") define o nome da coluna que vai armazenar os nomes que irão se relacionar com o id da tabela principal, caso seja omitida, a coluna vai ter o mesmo nome do atributo, no exemplo abaixo será **tags**
```
@Entity
public class Produto extends BaseEntity {
    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;
}
```

### 6.10. Mapeando coleções de objetos embutidos com @ElementCollection
Também é possível usar a anotação **@ElementCollection** Com classes anotadas com **@Embeddable**
* Deve ser criada uma classe que irá representar a tabela intermediária, essa classe deve estar anotada com **@Embeddable**, os atributos dessa classe se tornarão colunas na tabela.

```
@Embeddable
public class Atributo {
    private String descricao;
    private String valor;
}

@Entity
public class Produto extends BaseEntity {
    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
}
```

### 6.11. Mapeando mapas com @ElementCollection
Para mapear um Map, além das anotações **@ElementCollection** e **@CollectionTable** deve ser seguida a seguinte estrutura:
* @MapKeyColumn(name = "tipo") - Define o nome da coluna que irá exibir o campo CHAVE
* @Column(name = "descricao") - - Define o nome da coluna que irá exibir o campo VALOR

```
@Entity
public class Cliente extends BaseEntity {
    @ElementCollection
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;
}
```
### 6.12. Mapeando e persistindo dados de arquivos com @Lob
Permite salvar um arquivo no banco transformando ele em um array de bytes

```
@Entity
public class NotaFiscal extends BaseEntity {
    @Lob
    private byte[] xml;
}
```

### 6.14. Mapeando tabela secundária com @SecondaryTable
Essa anotação torna possível mapear mais de uma tabela numa mesma entidade, a anotação na classe define o nome da tabela e o atributo chave do relacionamento, para definir as outras colunas dessa tabela basta informar o nome da tabela com a propriedade **table** da anotação **@Column** no atributo e automaticamente o jpa irá relacionar esse atributo com a tabela secundária.

```
@Entity
@Table(name = "cliente")
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
public class Cliente extends BaseEntity {
    @Column(table = "cliente_detalhe", name = "data_nascimento")
    private Date dataNascimento;
}
```

### 6.15. Mapeando herança com @MappedSuperclass
Permite trabalhar com herança para atributos de chaves primárias

```
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class BaseEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
```

## Operações em Cascata
### 8.2. Fazendo inserções de objetos em cascata
Permite que um objeto em estado transiente seja salvo através do atributo cascade = CascadeType.ALL/PERSIST
```
@Entity
public class Pedido extends BaseEntity {
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
```
No trecho de código acima, quando a entidade Pedido for persistida, automaticamente o atributo cliente, que também é uma entidade, será salvo.

# grid-app
Criação de aplicação para execução de grid computing com JPPF (Java).

A entidade Pessoa representa a tabela Pessoa do banco de dados para utilizar outra tabela basta mapea-la. A entidade mapeada <strong>DEVE</strong> implementar <strong>IEntity</strong> e, 
consequentemente, <strong>Serializable</strong> (serialVersionUID).

Você deve alterar a classe EntityRepository passando o nome da sua entidade no lugar de Pessoa em CrudRepository<>. O mais correto seria criar seu próprio repositório, mas enfim, <i>It works</i>.

Para que o método de migração funcione corretamente é necessário que sejam alterados os dados de <strong>key</strong> (nome do objeto no Redis), 
<strong>host</strong> (host onde o redis está sendo executado) e <strong>port</strong> (porta do redis) no arquivo <strong>redis.properties</strong>.

Para se conectar ao banco de dados, no meu caso mysql, é necessário que os dados para a conexão sejam adicionados no arquivo <strong>application.properties</strong>.

Wish you the best. :D

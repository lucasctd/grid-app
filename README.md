# grid-app
Criação de aplicação para execução de grid computing com JPPF (Java).

A entidade Pessoa representa a tabela Pessoa do banco de dados para utilizar outra tabela basta mapea-la. A entidade mapeada DEVE implementar Serializable.

No Arquivo PessoaRepository favor editar CrudRepository<Pessoa, Integer> removendo "Pessoa" pelo nome da classe mapeada.

Para que o método de migração funcione corretamente é necessário que sejam alterados os dados de KEY (nome do objeto no Redis), 
HOST (host onde o redis está sendo executado, PORT (porta do redis) e o nome da entidade mapeada na linha 61 da classe GridTask.

Para se conectar ao banco de dados, no meu caso mysql, é necessário que os dados para a conexão sejam adicionados no arquivo application.properties.

Wish you the best. :D

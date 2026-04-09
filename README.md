## Teste Prático - Iniflex (Processo Seletivo Projedata)

Este projeto implementa o desafio prático de manipulação de funcionários, com leitura de dados em JSON, processamento em memória e exibição dos resultados no console.

## Objetivo

Executar as etapas solicitadas no teste, incluindo:

1. Carregar funcionários a partir de um arquivo JSON.
2. Remover o funcionário João.
3. Exibir funcionários com data e salário formatados.
4. Aplicar aumento de 10% nos salários.
5. Agrupar funcionários por função.
6. Exibir aniversariantes dos meses 10 e 12.
7. Exibir funcionário(s) mais velho(s).
8. Ordenar nomes alfabeticamente.
9. Somar todos os salários.
10. Exibir quantos salários mínimos cada funcionário recebe.

## Tecnologias Utilizadas

- Java (API padrão: coleções, streams, datas e BigDecimal)
- Maven (gerenciamento de dependências e execução)
- Jackson Databind 2.17.0 (leitura e mapeamento de JSON)

Dependência principal (pom.xml):

- com.fasterxml.jackson.core:jackson-databind:2.17.0

Plugin utilizado para execução:

- org.codehaus.mojo:exec-maven-plugin:3.1.0

## Estrutura do Projeto

- src/main/java/Principal.java
	- Classe principal com o fluxo completo do desafio.
- src/main/java/service/FuncionarioService.java
	- Carrega os dados de funcionários do arquivo JSON.
- src/main/java/model/Pessoa.java
	- Classe base da entidade de pessoa.
- src/main/java/model/Funcionario.java
	- Entidade de funcionário (nome, data de nascimento, salário, função).
- src/main/java/dto/FuncionarioDTO.java
	- DTO usado para desserializar os dados do JSON.
- src/main/resources/funcionarios.json
	- Base de dados inicial dos funcionários.

## Como o Código Funciona

### 1) Leitura dos dados

Em FuncionarioService.carregarFuncionarios:

1. Abre o arquivo funcionarios.json via ClassLoader.
2. Converte o JSON em um array de FuncionarioDTO com ObjectMapper.
3. Converte cada DTO para um objeto Funcionario.
4. Retorna uma List<Funcionario> para uso na aplicação.

### 2) Processamento principal

Em Principal.main:

1. Carrega os funcionários.
2. Remove João da lista.
3. Formata e imprime os dados.
4. Atualiza os salários com aumento de 10%.
5. Agrupa por função com Collectors.groupingBy.
6. Filtra aniversariantes de outubro e dezembro.
7. Descobre a menor data de nascimento e lista o(s) mais velho(s).
8. Ordena nomes por ordem alfabética.
9. Soma salários com BigDecimal.
10. Calcula quantidade de salários mínimos por funcionário.

## Como Executar

Pré-requisitos:

- Java instalado
- Maven instalado

No diretório raiz do projeto, execute:

```bash
mvn exec:java -Dexec.mainClass=Principal
```

## Observações

- O programa processa os dados em memória durante a execução.
- As alterações (como aumento salarial e remoção) não persistem no arquivo JSON original.

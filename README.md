# Projeto Relatório de Vendas
Projeto para geração de relatório de vendas a partir de um arquivo de entrada contendo dados sobre vendedores, clientes e vendas.
O projeto foi desenvolvido em Java 8, com a utilização do framework JUnit para testes automatizados e Maven para realização de build
e empacotamento.

# Pré Requisitos técnicos
  - Java 1.8+
  - Maven 3+
  
# Pré Requisitos de ambiente
  - Definição da variável de ambiente HOMEPATH
  - Criação das seguintes pastas:
	- HOMEPATH/data/in
	- HOMEPATH/data/out
	- HOMEPATH/data/prd
	- HOMEPATH/data/fld

# Exemplo arquivo de entrada
001ç1234567891234çPedroç50000<br/>
001ç3245678865434çPauloç40000.99<br/>
002ç2345675434544345çJose da SilvaçRural<br/> 
002ç2345675433444345çEduardo PereiraçRural<br/> 
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro<br/> 
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo<br/>

# Exemplo arquivo de saída
Quantidade de clientes no arquivo de entrada: 2<br/>
Quantidade de vendedores no arquivo de entrada: 2<br/>
ID da venda mais cara: 51<br/>
Nome Pior vendedor: Paulo<br/>

## Rodando a aplicação:
  - Abra a linha de comando
  - Vá para a pasta raiz do projeto
  - Na linha de comando, execute **mvn clean package** para a realização do build
  - Após a conclusão do build, vá para a pasta **target** do projeto
  - Na linha de comando, execute **java -jar sales-1.0-SNAPSHOT-jar-with-dependencies.jar**

## Execução de testes automáticos:
  - Abra a linha de comando
  - Vá para a pasta raiz do projeto
  - Na linha de comando, execute **mvn test**
  - Verifique os resultados dos testes executados
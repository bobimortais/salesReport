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

# Fluxo da aplicação
A aplicação funciona a partir da verificação de eventos de criação de novos arquivos na pasta **HOMEPATH/data/in**. Quando
da criação de um novo arquivo na pasta citada, uma nova Thread é criada para o processamento do arquivo em questão. A seguinte
ordem de execução é então seguida:<br/>
1. A aplicação identifica um evento de criação na pasta **HOMEPATH/data/in**. Uma nova Thread então é iniciada para processamento do arquivo<br/>
2. O arquivo é então lido e cada linha é convertida para um de três tipos de entidades estabelecidas:<br/>
   - Seller (Vendedor)<br/>
   - Customer (Cliente)<br/>
   - Sale (Venda)<br/>
3. Após a conversão do arquivo para as entidades em questão, seguintes informações são extraídas:<br/>
	- Quantidade de clientes no arquivo de entrada<br/>
	- Quantidade de vendedores no arquivo de entrada<br/>
	- ID da venda mais cara<br/>
	- Nome Pior vendedor<br/>
4. Caso o processamento ocorra com sucesso, o arquivo de entrada é movido para a pasta **HOMEPATH/data/prd**, enquanto um arquivo de saída é gerado
na pasta **HOMEPATH/data/out** com as informações descritas acima. Caso acha alguma falha durante o processamento do arquivo, o arquivo em questão é 
movido para a pasta **HOMEPATH/data/fld** e não há geração de arquivo de saída. Os logs de execução podem ser analizados no arquivo **app_{horário da execução}.log**, contido
na pasta **logs** gerada no mesmo caminho de execução da aplicação

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
  
# Informações adicionais
	- Informações como diretório de entrada, diretório de saída, e extensão do arquivo de saída podem ser modificadas no arquivo **app.properties** contido na 
pasta **src/main/resources** do projeto
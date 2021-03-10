# Sistema simples de banco
A ideia deste é relembrar alguns conceitos básicos, porém essenciais, como, por exemplo, herança e encapsulamento. 

## Digamos que alguns "requisitos" estabelecidos foram:
* Este banco possui clientes pessoa física e jurídica.
* Um cliente pode possuir mais de uma conta.
* As contas pode ser de dos tipos:
  * Conta Especial: onde o cliente possui um limite de crédito pré-aprovado que ele pode sacar a qualquer momento.
  * Conta Poupança: onde o saldo existente será acrescido por uma taxa de remuneração no aniversário mensal da conta.
* É possível realizar saques, depósitos e transferências de valores entre contas diferentes.
* Conta poupança não permite saque que seja menor que o limite existente na conta. Já a conta especial permite.
* Gerar um relatório que liste os nomes dos clientes e saldo das contas e a soma do saldo de todas as contas.
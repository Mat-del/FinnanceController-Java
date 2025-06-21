Olá! Este é o meu primeiro projeto solo em Java, ainda em fase de desenvolvimento. Estou utilizando esse projeto como uma forma de aprendizado e prática com Java e Maven.
Ele funciona como um controlador de finanças pessoais, permitindo registrar receitas, despesas e visualizar o saldo atual. Apesar de ainda estar incompleto, já é possível entender bem sua proposta e funcionamento.

Como rodar o projeto
Abra o terminal na pasta raiz do projeto.
Rode em:
mvn clean compile
Em seguida, execute o projeto com:
mvn exec:java '-Dexec.mainClass=main.Main'

O que você encontrará na interface
Na tela principal, você verá:

Saldo – Mostra o saldo atual

Adicionar Receita – Permite registrar uma nova entrada de dinheiro

Adicionar Despesa – Registra uma nova saída de dinheiro

Compras do mês – Lista as despesas feitas no mês atual

Listar Lançamentos – Mostra todas as transações feitas

Clique em "Adicionar Receita" e insira, por exemplo, a descrição "Salário" com o valor 2500.
Em seguida, clique em "Adicionar Despesa" e insira algo como "Teclado" com o valor 200.
Ao clicar em "Saldo", o sistema mostrará o valor restante com base nas receitas e despesas registradas.
Pode mexer e testando, já que é essa a proposta.

Detalhes que vc não pode esquecer:
O projeto roda em FlatLaf para um visual moderno e limpo.
Ele ainda está sendo aprimorado e pode receber novas funcionalidades em breve(Eu acho)
Sinta-se à vontade XD


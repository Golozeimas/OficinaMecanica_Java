# 🛠️ Sistema de Gerenciamento de Oficina Mecânica

Este projeto é uma aplicação desktop desenvolvida em **Java** utilizando **JavaFX** para a interface gráfica. O sistema foi projetado para gerenciar o fluxo completo de uma oficina mecânica, desde o cadastro de clientes e veículos até a criação de ordens de serviço, controle de estoque de peças e geração de relatórios financeiros.

O projeto segue o padrão de arquitetura **MVC (Model-View-Controller)** e utiliza **MySQL** para persistência de dados.

---

## 👥 Equipe de Desenvolvimento

Este projeto foi desenvolvido como parte da avaliação da disciplina de Programação Orientada a Objetos (POO) pelos alunos:

* **João Matheus Ramos Araujo**
* **Igor Pereira Lima**
* **Erick Rhuan Carvalho**

---

## 📋 Funcionalidades do Sistema

O sistema cobre as principais necessidades operacionais de uma oficina:

### 1. 🔐 Controle de Acesso
* **Login Administrativo:** Acesso seguro ao sistema via e-mail e senha.

### 2. 🚗 Gestão de Clientes e Veículos
* **Cadastro de Clientes:** Registro completo com validação automática de **CPF** e **Telefone**.
* **Cadastro de Veículos:** Registro de placa, modelo e ano, associando o veículo diretamente a um cliente proprietário.
* **Histórico de Manutenções:** Visualização detalhada de todos os serviços já realizados em um veículo específico.

### 3. 🛠️ Ordens de Serviço (OS)
* **Abertura de OS:** Criação de ordens detalhadas com descrição do problema e status inicial ("Em Serviço").
* **Gestão de Peças e Mão de Obra:** Adição dinâmica de peças do estoque à ordem de serviço e definição do valor da mão de obra. O sistema calcula o total automaticamente.
* **Controle de Status:** Atualização do andamento da OS (*Em Serviço*, *Aguardando Peças*, *Pronto para Entrega*, *Finalizado*).

### 4. 📦 Controle de Estoque
* **Gerenciamento de Peças:** Cadastro de novas peças com preço e quantidade inicial.
* **Ajuste de Estoque:** Interface dedicada para realizar entradas (compra) e saídas (perda/uso) manuais de estoque.
* **Alertas de Estoque Baixo:** O painel avisa visualmente quando uma peça possui menos de 10 unidades disponíveis.

### 5. 💰 Financeiro e Relatórios
* **Registro de Pagamentos:** Baixa de ordens de serviço com múltiplas formas de pagamento (Pix, Dinheiro, Cartão).
* **Relatórios PDF:** Geração automática de relatórios financeiros detalhados (faturamento total e lista de serviços) prontos para impressão.
* **Dashboard:** Visão rápida do faturamento total e quantidade de ordens finalizadas na tela de relatórios.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
* **Interface Gráfica:** [JavaFX](https://openjfx.io/) (com FXML e Scene Builder)
* **Banco de Dados:** [MySQL](https://www.mysql.com/) (8.0+)
* **Relatórios:** [iTextPDF 5.5.13](https://itextpdf.com/)
* **Segurança:** `dotenv-java` para gerenciamento de variáveis de ambiente (credenciais do banco).
* **Arquitetura:** MVC (Model - View - Controller)

---

## ⚙️ Pré-requisitos e Configuração

Para executar este projeto, você precisará ter instalado:
* **JDK 21** ou superior.
* **MySQL Server** rodando localmente.
* Uma IDE Java (IntelliJ IDEA, Eclipse ou NetBeans).

### Passo 1: Configuração do Banco de Dados
1.  Crie um banco de dados no MySQL chamado `oficina`.
2.  Execute o script SQL abaixo para criar as tabelas e o usuário administrador padrão:

```sql
CREATE DATABASE IF NOT EXISTS oficina;
USE oficina;

-- Tabela de Administradores
CREATE TABLE IF NOT EXISTS administrador (
    id_adm INT AUTO_INCREMENT PRIMARY KEY,
    email_adm VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

-- Inserir admin padrão
INSERT INTO administrador (email_adm, senha) VALUES ('admin@oficina.com', 'admin123');
-- (O restante do script de criação das tabelas cliente, veiculo, peca, ordem_servico, ordem_peca e pagamento deve ser executado aqui)
```
### Passo 2: Configuração de Variáveis de Ambiente (.env)
Por segurança, as senhas do banco de dados não estão no código.

1. Crie um arquivo chamado .env na raiz do projeto (ao lado da pasta src).

2. Adicione o seguinte conteúdo, ajustando a senha para a do seu MySQL:
DB_URL=jdbc:mysql://localhost:3306/oficina
DB_USER=root
DB_PASSWORD=sua_senha_aqui

### Passo 3: Executar o Projeto
1. Abra o projeto na sua IDE.
2. Certifique-se de que as bibliotecas (JavaFX, MySQL Connector, iTextPDF, Dotenv) estão no classpath ou configuradas via Maven/Gradle.
3. Localize a classe principal src/App.java e execute-a.

### Credenciais de Acesso Padrão
Login: admin@oficina.com
Senha: admin123

## Estrutura do Projeto
O código está organizado seguindo o padrão MVC para facilitar a manutenção e escalabilidade:

Oficina_POO/
├── src/
│   ├── Controller/       # Lógica de controle das telas (ex: LoginController, VeiculoController)
│   ├── Model/            # Classes de entidade e regras de negócio (ex: Cliente, Peca)
│   ├── View/             # Arquivos .fxml da interface gráfica
│   ├── DB/               # Conexão com banco e classes DAO (Data Access Object)
│   ├── Templates/        # Classes utilitárias (ex: Alertas)
│   └── App.java          # Classe principal que inicia a aplicação
├── .env                  # Arquivo de configuração (não versionado)
└── README.md             # Documentação do projeto

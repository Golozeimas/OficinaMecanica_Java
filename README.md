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

-- Tabela de Administradores (Login)
CREATE TABLE IF NOT EXISTS administrador (
    id_adm INT AUTO_INCREMENT PRIMARY KEY,
    email_adm VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

-- Inserir admin padrão para conseguir logar
INSERT INTO administrador (email_adm, senha) VALUES ('admin@oficina.com', 'admin123');

-- Tabela de Clientes
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(11),
    isVip BOOLEAN DEFAULT FALSE
);

-- Tabela de Veículos
CREATE TABLE veiculo (
    id_veiculo INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(100) NOT NULL,
    ano VARCHAR(4) NOT NULL,
    placa VARCHAR(7) NOT NULL UNIQUE,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

-- Tabela de Peças
CREATE TABLE peca (
id_peca INT PRIMARY KEY AUTO_INCREMENT,
nome_peca VARCHAR(100) NOT NULL,
preco_unitario DECIMAL(10,2) NOT NULL,
quantidade_estoque INT NOT NULL DEFAULT 0
);


-- Tabela de Ordem de Serviço
CREATE TABLE ordem_servico (
    id_ordem INT PRIMARY KEY AUTO_INCREMENT,
    id_veiculo INT NOT NULL,
    descricao TEXT NOT NULL,
    valor_mao_obra DECIMAL(10,2) NOT NULL DEFAULT 0,
    status ENUM('Aguardando Peças', 'Em Serviço', 'Pronto para Entrega', 'Finalizado') NOT NULL DEFAULT 'Em Serviço',
    data_abertura DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_finalizacao DATETIME NULL,
    FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo) ON DELETE CASCADE
);

-- Tabela de Relacionamento Ordem <-> Peça (Muitos para Muitos)
CREATE TABLE ordem_peca (
    id_ordem_peca INT PRIMARY KEY AUTO_INCREMENT,
    id_ordem INT NOT NULL,
    id_peca INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_ordem) REFERENCES ordem_servico(id_ordem) ON DELETE CASCADE,
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca) ON DELETE CASCADE
);

-- Tabela de Pagamentos
CREATE TABLE pagamento (
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    id_ordem INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    forma_pagamento ENUM('Dinheiro', 'Cartão Débito', 'Cartão Crédito', 'PIX', 'Boleto') NOT NULL,
    status_pagamento ENUM('Pendente', 'Pago', 'Cancelado') NOT NULL DEFAULT 'Pendente',
    data_pagamento DATETIME NULL,
    FOREIGN KEY (id_ordem) REFERENCES ordem_servico(id_ordem) ON DELETE CASCADE
);
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

2. Certifique-se de que as bibliotecas (JavaFX, MySQL Connector, iTextPDF, Dotenv) estão no classpath (.jar) ou configuradas via Maven/Gradle.

  Obs: Caso tenha dificuldade com a instalação do JavaFX, veja esse vídeo no youtube (https://www.youtube.com/watch?v=gd9Ds86gFI0)

4. Localize a classe principal src/App.java e execute-a.

### Credenciais de Acesso Padrão
Login: admin@gmail.com

Senha: 1234

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

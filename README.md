# ✈️ Sistema Simplificado de Gestão de Empresa Aérea

Sistema desenvolvido em **Java (console)** com o objetivo de simular operações básicas de uma empresa aérea, aplicando **Programação Orientada a Objetos**, **estruturas de dados lineares** e o **padrão de projeto Interactor (Use Case)**.

Projeto acadêmico focado em **modelagem, organização de código e aplicação prática de estruturas de dados**.

---

## 📌 Objetivos do Projeto

Implementar um sistema que permita:

✔ Gerenciamento de aviões  
✔ Venda de passagens  
✔ Embarque de passageiros  
✔ Atendimento prioritário  
✔ Histórico de operações com desfazer ação

Utilizando obrigatoriamente:

- Lista Encadeada
- Fila
- Fila de Prioridade (Heap)
- Pilha
- Padrão de Projeto (Interactor)

---

## 🧠 Conceitos Aplicados

### 🔹 Programação Orientada a Objetos
- Encapsulamento
- Separação de responsabilidades
- Camadas
- Interfaces

### 🔹 Estruturas de Dados
| Estrutura | Uso no sistema |
|----------|---------------|
| Lista Encadeada | Gerenciamento de aviões |
| Fila | Embarque por ordem de chegada |
| Priority Queue (Heap) | Atendimento prioritário |
| Pilha | Histórico de operações (undo) |

### 🔹 Padrão de Projeto
### Interactor (Use Case)

Cada funcionalidade do sistema é representada por um **Interactor**, responsável por executar uma única regra de negócio.

Exemplos:
- Cadastrar avião
- Vender passagem
- Embarcar passageiro
- Desfazer operação

Isso garante:
- baixo acoplamento
- código organizado
- fácil manutenção
- testes mais simples

---

## 🏗️ Arquitetura

O sistema segue uma arquitetura em camadas:

Main (menu console)
↓
Controller
↓
Interactors (regras de negócio)
↓
Repositories (estruturas de dados)
↓
Entities


---

## 📂 Estrutura de Pastas

src/
├── main
├── controller
├── entity
├── repository
├── structure
└── interactor


### 📦 Descrição

### entity
Classes de domínio:
- Aviao
- Passageiro
- Operacao

### structure
Implementações das estruturas:
- ListaEncadeada
- Fila
- FilaPrioridade
- Pilha

### repository
Responsáveis por armazenar dados:
- AviaoRepository
- EmbarqueRepository
- HistoricoRepository

### interactor
Casos de uso do sistema:
- CadastrarAviaoInteractor
- VenderPassagemInteractor
- EmbarcarInteractor
- DesfazerOperacaoInteractor

### controller
- SistemaController (controla o menu)

### main
- Main (execução via terminal)

---

## ⚙️ Funcionalidades

### ✈️ Aviões (Lista Encadeada)
- Inserir avião
- Listar aviões
- Buscar por código
- Remover avião

### 🎟️ Passagens e Embarque (Fila)
- Registrar venda
- Inserir na fila
- Embarque por ordem de chegada

### ⭐ Atendimento Prioritário (Heap)
- Definir prioridade
- Embarque respeitando prioridade
- Empate → ordem de chegada

### 🕘 Histórico (Pilha)
- Registrar operações
- Visualizar histórico
- Desfazer última ação

---

## ▶️ Como Executar

### Requisitos
- Java 17+ (ou 11+)

### Compilar
```bash
javac -d bin src/**/*.java
Executar
java -cp bin main.Main
# 📝 Todolist API

Uma API REST simples de **gerenciamento de tarefas (ToDo)** desenvolvida em **Java com Spring Boot**, permitindo criar, listar, atualizar e deletar tarefas.

Este projeto é ideal para aprender e demonstrar como construir APIs com Spring Boot e integrações básicas com banco de dados usando **Spring Data JPA**.

---

## 📌 Tecnologias Utilizadas 

- Java  
- Spring Boot  
- Spring Data JPA  
- Maven  
- BCrypt para hashing de senha  

---

## 🚀 Funcionalidades

A API oferece:

- 📄 **Criar tarefas**
- 🔍 **Listar tarefas**
- 🔁 **Atualizar tarefas**
- ❌ **Remover tarefas**
- 👤 **Gerenciar usuários**
- 📌 Validação de usuário único e proteção de senha

---

## 🧩 Endpoints Disponíveis

### **Usuários**
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/users/` | Cria um novo usuário |
  
### **Tarefas**
| Método | Rota | Descrição |
|--------|------|-----------|
| GET  | `/tasks/` | Lista todas as tarefas |
| POST | `/tasks/` | Cria uma nova tarefa |
| PUT  | `/tasks/{id}` | Atualiza uma tarefa existente |
| DELETE | `/tasks/{id}` | Remove uma tarefa |

> 😃 Observação: Você pode testar usando clientes REST como o **Postman** ou **Insomnia**

---

## 🛠️ Instalação e Execução

### Pré-requisitos

Antes de iniciar, verifique se você tem instalado:

- JDK 17+  
- Maven  
- IDE (VS Code, IntelliJ IDEA, Eclipse, etc.)

---

### 🚧 Passos

1. Clone o repositório:

```bash
git clone https://github.com/carlospablo12/todolist.git
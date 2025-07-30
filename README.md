# Gerenciamento-de-Financas Mobile Android
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
![Status](https://img.shields.io/badge/status-Em%20Desenvolvimento-yellow)

> Um aplicativo Android para gerenciamento de finanças pessoais, permitindo o controle de transações, orçamentos e metas financeiras.

## 📖 Sobre o Projeto

Este é um projeto de aplicativo mobile nativo para Android, desenvolvido em **Java** com o **Android Studio**. O objetivo é oferecer ao usuário uma ferramenta simples e offline para controlar suas finanças pessoais diretamente no celular. Todos os dados são armazenados localmente no dispositivo usando o banco de dados **SQLite**, garantindo a privacidade e o funcionamento sem necessidade de conexão com a internet.

---

## ✨ Funcionalidades Implementadas

A análise dos arquivos de código revela a seguinte arquitetura de funcionalidades:

- **👤 Autenticação e Perfil de Usuário:**
  - `LoginActivity.java` / `CriarContaActivity.java`: Telas para o usuário entrar ou criar uma nova conta.
  - `PerfilActivity.java`: Área para o usuário visualizar suas informações.

- **💸 Controle de Transações:**
  - `FincancaActivity.java` (FinancaActivity): Tela para registrar novas transações (receitas e despesas).
  - `HistoricoActivity.java`: Tela que exibe um histórico completo de todas as transações cadastradas.

- **📈 Planejamento Financeiro:**
  - **Orçamento Mensal:** Capacidade de definir e acompanhar um orçamento (`OrcamentoMensal.java`, `OrcamentoMensalDAO.java`).
  - **Metas Financeiras:** Funcionalidade para o usuário criar e gerenciar metas de economia (`Meta.java`, `MetaDAO.java`, `MetaAdapter.java`).

- **📚 (Outra funcionalidade - Livros):**
  - O projeto inclui arquivos como `Livro.java` e `LivrosAdapter.java`, uma área de recomendação de livros com foco em educação financeira. .

---

## 🏗️ Estrutura do Código

O projeto foi organizado seguindo padrões de arquitetura mobile para separar responsabilidades:

- **Activities (`*Activity.java`):** Controlam as telas (UI) e a lógica de navegação.
- **Models (`Usuario.java`, `Transacao.java`, `Meta.java`):** Classes que modelam os dados da aplicação.
- **DAOs (`*DAO.java`):** *Data Access Objects* – Camada responsável por toda a comunicação com o banco de dados SQLite (inserir, buscar, atualizar, deletar).
- **Adapters (`*Adapter.java`):** Fazem a "ponte" entre os conjuntos de dados (buscados dos DAOs) e os componentes de lista da interface (`RecyclerView` ou `ListView`).
- **`DatabaseHelper.java`:** Classe central que gerencia a criação, versão e atualização do banco de dados SQLite.


## 🛠️ Tecnologias Utilizadas

- **Plataforma:** `Android`
- **Linguagem:** `Java`
- **IDE:** `Android Studio`
- **Banco de Dados:** `SQLite` (armazenamento local)

---

## 👨‍💻 Autor

Desenvolvido por **Wallace Castro de Oliveira**.

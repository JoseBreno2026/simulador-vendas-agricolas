# Simulador de Software de Vendas Agrícolas 🚜

Software desenvolvido em Java, executado no terminal (modo console), focado na simulação do fluxo de transações comerciais entre agricultores e clientes e no controlo de produtos e stock. O projeto foi desenhado como um laboratório prático para a aplicação de conceitos de Programação Orientada a Objetos (POO), como classes, objetos e encapsulamento, com as entidades do sistema organizadas em classes separadas (`Produto`, `Cliente` e `Agricultor`).

## 🚀 Funcionalidades
- **Gestão de Produtos e Stock:** Registo de produtos agrícolas (nome, preço e quantidade) e controlo das quantidades disponíveis. Os agricultores autenticados podem consultar, alterar e excluir produtos.
- **Simulação de Fluxo de Vendas:** Processamento de compras em que o cliente, depois de fazer login, escolhe o produto e a quantidade; o sistema calcula o valor total e abate o stock automaticamente.
- **Cadastro e Login:** Registo de clientes e agricultores, com autenticação por login e senha. Os dados ficam guardados apenas em memória, enquanto o programa está em execução (não são gravados em ficheiro ou banco de dados).
- **Design de Software (POO):** 
  - **Encapsulamento:** Atributos privados com acesso por métodos *getters* e *setters*, aplicado principalmente na classe `Produto`, que protege o nome, o preço e a quantidade em stock.
  - **Classes e Objetos:** Modelagem das entidades `Produto`, `Cliente` e `Agricultor`, geridas em listas (`ArrayList`) pela classe principal `Aplicativo`.

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java (JDK 17 ou superior)
- **Paradigma:** Programação Orientada a Objetos (POO)
- **Interface:** Aplicação de console (menu no terminal)

## ⚙️ Como executar o projeto na sua máquina

> **Atenção:** Certifique-se de que tem o Java Development Kit (JDK) **17 ou superior** instalado e configurado no `PATH` (variáveis de ambiente) do seu sistema. Para confirmar, execute `javac -version` no terminal. Se precisar instalar, pode obter o JDK 17 em https://adoptium.net/temurin/releases/?version=17

1. Clone o repositório para a sua máquina local:
`git clone https://github.com/JoseBreno2026/simulador-vendas-agricolas.git`

2. **Execução via IDE (Recomendado):**
   - Importe a pasta do projeto para a sua IDE de preferência:
     - **Eclipse** (o projeto já é um projeto Eclipse): *File > Import > General > Existing Projects into Workspace* e selecione a pasta clonada.
     - **VS Code** (com a extensão *Extension Pack for Java*): *File > Open Folder* e selecione a pasta clonada.
     - **IntelliJ IDEA / Apache NetBeans:** crie um projeto a partir de código-fonte existente e marque a pasta `src` como pasta de código-fonte.
   - Aguarde a indexação e localize a classe principal `Aplicativo` (`src/agro/Aplicativo.java`), que contém o método `public static void main(String[] args)`.
   - Execute a classe através do botão *Run* da IDE (no Eclipse: *Run As > Java Application*). O menu do programa aparece no console da IDE, onde deve digitar as respostas.
   - Se o Eclipse indicar que não encontra o `JavaSE-17`, registe o JDK 17 em *Window > Preferences > Java > Installed JREs*.

3. **Execução via Terminal (Linha de Comandos):**
   - Navegue até à diretoria raiz do projeto (a que contém a pasta `src`):
   `cd simulador-vendas-agricolas`
   - Compile o código (as classes compiladas são criadas na pasta `bin`):

     Linux / macOS:
     ```
     mkdir bin
     javac -encoding UTF-8 -d bin src/module-info.java src/agro/*.java
     ```
     Windows (Prompt de Comando):
     ```
     mkdir bin
     javac -encoding UTF-8 -d bin src\module-info.java src\agro\*.java
     ```
   - Execute a aplicação, indicando o nome completo da classe (com o pacote `agro`, sem a extensão `.class`):
   `java -cp bin agro.Aplicativo`
   - Siga o menu digitando o número da opção e pressionando Enter. Use a opção `6` para sair.

   > **Dica (Windows):** se os acentos aparecerem trocados no terminal, execute `chcp 65001` e depois `java -Dfile.encoding=UTF-8 -cp bin agro.Aplicativo`.

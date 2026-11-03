package agro;

import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Aplicativo {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Locale BR = Locale.forLanguageTag("pt-BR");

    private static final ArrayList<Produto> produtos = new ArrayList<>();
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Agricultor> agricultores = new ArrayList<>();

    // ------------------------------------------------------------------
    // Leitura segura de dados
    // Tudo é lido com nextLine() (linha inteira), o que evita misturar
    // nextInt()/next()/nextLine() e permite nomes e endereços com espaços.
    // ------------------------------------------------------------------

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("O campo não pode ficar vazio. Tente novamente.");
        }
    }

    private static String lerSemEspacos(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem);
            if (!texto.contains(" ")) {
                return texto;
            }
            System.out.println("Este campo não pode conter espaços. Tente novamente.");
        }
    }

    private static int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            String texto = lerTexto(mensagem);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                if (maximo == Integer.MAX_VALUE) {
                    System.out.println("Digite um número maior ou igual a " + minimo + ".");
                } else {
                    System.out.println("Digite um número entre " + minimo + " e " + maximo + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números inteiros.");
            }
        }
    }

    // Aceita "2.5", "2,5" e "1.234,50" (independe do idioma do computador)
    private static double lerPreco(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem);
            if (texto.contains(",")) {
                texto = texto.replace(".", "").replace(",", ".");
            }
            if (texto.matches("\\d+(\\.\\d+)?")) {
                double valor = Double.parseDouble(texto);
                if (Double.isFinite(valor)) {
                    return valor;
                }
            }
            System.out.println("Preço inválido. Digite um valor positivo, por exemplo: 2,50");
        }
    }

    private static String formatarPreco(double valor) {
        return String.format(BR, "R$ %.2f", valor);
    }

    // ------------------------------------------------------------------
    // Produtos
    // ------------------------------------------------------------------

    private static Produto buscarProduto(String nomeProduto) {
        for (Produto produto : produtos) {
            if (produto.getnome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    private static void exibirListaProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getnome() + ", Preço: " + formatarPreco(produto.getpreco())
                    + ", Quantidade: " + produto.getquantidade());
        }
    }

    private static void cadastrarProdutos() {
        int quantidadeProdutos = lerInteiro("Quantos produtos você deseja cadastrar?: ", 0, 1000);

        for (int i = 0; i < quantidadeProdutos; i++) {
            String nome;
            while (true) {
                nome = lerTexto("Informe o nome do produto " + (i + 1) + ":");
                if (buscarProduto(nome) == null) {
                    break;
                }
                System.out.println("Já existe um produto com esse nome. Informe outro nome.");
            }

            double preco = lerPreco("Informe o preço do produto " + (i + 1) + " (ex: 2,50):");
            int quantidade = lerInteiro("Informe a quantidade do produto " + (i + 1) + ":", 0, Integer.MAX_VALUE);

            produtos.add(new Produto(nome, preco, quantidade));
        }

        System.out.println("Lista de produtos:");
        exibirListaProdutos();
    }

    private static void alterarProduto(String nomeProduto) {
        Produto produto = buscarProduto(nomeProduto);
        if (produto == null) {
            System.out.println("\nProduto não encontrado para alteração.");
            return;
        }

        System.out.println();
        String novoNome;
        while (true) {
            novoNome = lerTexto("Informe o novo nome do produto:");
            Produto existente = buscarProduto(novoNome);
            if (existente == null || existente == produto) {
                break;
            }
            System.out.println("Já existe outro produto com esse nome. Informe outro nome.");
        }
        double novoPreco = lerPreco("Informe o novo preço do produto (ex: 2,50):");
        int novaQuantidade = lerInteiro("Informe a nova quantidade do produto:", 0, Integer.MAX_VALUE);

        produto.setnome(novoNome);
        produto.setpreco(novoPreco);
        produto.setquantidade(novaQuantidade);
        System.out.println("Produto alterado com sucesso.");
    }

    private static void excluirProduto(String nomeProduto) {
        Produto produto = buscarProduto(nomeProduto);
        if (produto == null) {
            System.out.println("\nProduto não encontrado para exclusão.");
            return;
        }
        produtos.remove(produto);
        System.out.println("\nProduto excluído com sucesso.");
    }

    // ------------------------------------------------------------------
    // Clientes
    // ------------------------------------------------------------------

    private static void exibirListaClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getnome() + ", idade: " + cliente.getidade() + ", cpf: "
                    + cliente.getcpf() + ", endereço: " + cliente.getendereco() + ", login: " + cliente.getlogin());
        }
    }

    private static boolean loginClienteExiste(String login) {
        for (Cliente cliente : clientes) {
            if (cliente.getlogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    // Devolve o cliente se login e senha conferem; caso contrário, null
    private static Cliente buscarCliente(String log, String sen) {
        for (Cliente cliente : clientes) {
            if (cliente.getlogin().equalsIgnoreCase(log) && cliente.getsenha().equals(sen)) {
                return cliente;
            }
        }
        return null;
    }

    private static void cadastrarClientes() {
        int quantidadeCliente = lerInteiro("Quantos clientes você deseja cadastrar?: ", 0, 1000);

        for (int i = 0; i < quantidadeCliente; i++) {
            String nome = lerTexto("Informe o nome do cliente " + (i + 1) + ":");
            int idade = lerInteiro("Informe a idade do cliente " + (i + 1) + ":", 1, 120);
            String cpf = lerTexto("Informe o cpf do cliente " + (i + 1) + ":");
            String endereco = lerTexto("Informe o endereço do cliente " + (i + 1) + ":");

            String login;
            while (true) {
                login = lerSemEspacos("Informe o login do cliente " + (i + 1) + ":");
                if (!loginClienteExiste(login)) {
                    break;
                }
                System.out.println("Esse login já está em uso. Escolha outro.");
            }

            String senha = lerTexto("Informe a senha do cliente " + (i + 1) + ":");

            clientes.add(new Cliente(nome, cpf, login, senha, endereco, idade));
        }

        System.out.println("Lista de clientes:");
        exibirListaClientes();
    }

    private static void realizarCompra(Cliente cliente, String nomeProduto) {
        Produto produto = buscarProduto(nomeProduto);
        if (produto == null) {
            System.out.println("\nProduto não encontrado.");
            return;
        }
        if (produto.getquantidade() == 0) {
            System.out.println("\nProduto sem estoque no momento.");
            return;
        }

        System.out.println("Produto encontrado:");
        System.out.println("Nome: " + produto.getnome() + ", Preço: " + formatarPreco(produto.getpreco()));

        int quantidadeCompra = lerInteiro("Informe a quantidade que deseja comprar:", 1, Integer.MAX_VALUE);

        if (quantidadeCompra > produto.getquantidade()) {
            System.out.println("Quantidade insuficiente em estoque. Disponível: " + produto.getquantidade());
            return;
        }

        double valorTotal = produto.getpreco() * quantidadeCompra;
        produto.setquantidade(produto.getquantidade() - quantidadeCompra);

        System.out.println("Compra realizada com sucesso!");
        System.out.println("Valor total a pagar: " + formatarPreco(valorTotal));
        System.out.println("Cliente:");
        System.out.println("Nome: " + cliente.getnome() + ", Idade: " + cliente.getidade() + ", CPF: "
                + cliente.getcpf() + ", Endereço: " + cliente.getendereco());
    }

    private static void logarComoCliente() {
        String log = lerTexto("Informe seu login: ");
        String sen = lerTexto("Informe sua senha: ");

        Cliente cliente = buscarCliente(log, sen);
        if (cliente == null) {
            System.out.println("\nLogin ou senha inválidos.");
            return;
        }
        System.out.println("\nBem-vindo(a), " + cliente.getnome() + "!");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível para compra no momento.");
            return;
        }

        exibirListaProdutos();
        String produtoDesejado = lerTexto("\nDigite o nome do produto que deseja comprar:");
        realizarCompra(cliente, produtoDesejado);
    }

    // ------------------------------------------------------------------
    // Agricultores
    // ------------------------------------------------------------------

    private static void exibirListaAgricultores() {
        for (Agricultor agricultor : agricultores) {
            System.out.println("Nome: " + agricultor.getnome() + ", Idade: " + agricultor.getidade() + ", Empresa: "
                    + agricultor.getempresa() + ", Cnpj: " + agricultor.getcnpj() + ", Login: "
                    + agricultor.getlogin());
        }
    }

    private static boolean loginAgricultorExiste(String login) {
        for (Agricultor agricultor : agricultores) {
            if (agricultor.getlogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    // Devolve o agricultor se login e senha conferem; caso contrário, null
    private static Agricultor buscarAgricultor(String log, String sen) {
        for (Agricultor agricultor : agricultores) {
            if (agricultor.getlogin().equalsIgnoreCase(log) && agricultor.getsenha().equals(sen)) {
                return agricultor;
            }
        }
        return null;
    }

    private static void cadastrarAgricultores() {
        int quantidadeAgricultor = lerInteiro("Quantos agricultores você deseja cadastrar?: ", 0, 1000);

        for (int i = 0; i < quantidadeAgricultor; i++) {
            String nome = lerTexto("Informe o nome do agricultor " + (i + 1) + ":");
            int idade = lerInteiro("Informe a idade do agricultor " + (i + 1) + ":", 1, 120);
            String empresa = lerTexto("Informe o nome da empresa do agricultor " + (i + 1) + ":");
            String cnpj = lerTexto("Informe o cnpj do agricultor " + (i + 1) + ":");

            String login;
            while (true) {
                login = lerSemEspacos("Informe o login do agricultor " + (i + 1) + ":");
                if (!loginAgricultorExiste(login)) {
                    break;
                }
                System.out.println("Esse login já está em uso. Escolha outro.");
            }

            String senha = lerTexto("Informe a senha do agricultor " + (i + 1) + ":");

            agricultores.add(new Agricultor(nome, login, senha, empresa, cnpj, idade));
        }

        System.out.println("Lista de agricultores:");
        exibirListaAgricultores();
    }

    private static void logarComoAgricultor() {
        String log = lerTexto("Informe o login: ");
        String sen = lerTexto("Informe sua senha: ");

        Agricultor agricultor = buscarAgricultor(log, sen);
        if (agricultor == null) {
            // Antes o menu abria mesmo com login/senha errados
            System.out.println("\nLogin ou senha inválidos.");
            return;
        }
        System.out.println("\nBem-vindo(a), " + agricultor.getnome() + " (" + agricultor.getempresa() + ")!");

        boolean voltar = false;
        while (!voltar) {
            System.out.println();
            System.out.println("1 - Visualizar Lista de Produtos");
            System.out.println("2 - Alterar Produtos");
            System.out.println("3 - Excluir Produtos");
            System.out.println("0 - Voltar ao menu principal");
            System.out.println();
            int opcaoAgri = lerInteiro("Informe o que você deseja fazer: ", 0, 3);

            switch (opcaoAgri) {
                case 1:
                    exibirListaProdutos();
                    break;

                case 2:
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    exibirListaProdutos();
                    alterarProduto(lerTexto("Informe o nome do produto que você deseja alterar: "));
                    exibirListaProdutos();
                    break;

                case 3:
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    exibirListaProdutos();
                    excluirProduto(lerTexto("Informe o nome do produto que você deseja excluir: "));
                    exibirListaProdutos();
                    break;

                default:
                    voltar = true;
                    break;
            }
        }
    }

    // ------------------------------------------------------------------
    // Menu principal
    // ------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            boolean sair = false;

            while (!sair) {
                System.out.println();
                System.out.println("1 - Cadastrar Produto");
                System.out.println("2 - Cadastrar Cliente");
                System.out.println("3 - Cadastrar Agricultor");
                System.out.println("4 - Logar como Cliente");
                System.out.println("5 - Logar como Agricultor");
                System.out.println("6 - Sair");
                System.out.println();

                int opcao = lerInteiro("Informe o que você deseja fazer: ", 1, 6);
                System.out.println();

                switch (opcao) {
                    case 1:
                        cadastrarProdutos();
                        break;
                    case 2:
                        cadastrarClientes();
                        break;
                    case 3:
                        cadastrarAgricultores();
                        break;
                    case 4:
                        logarComoCliente();
                        break;
                    case 5:
                        logarComoAgricultor();
                        break;
                    default:
                        sair = true;
                        break;
                }
            }
            System.out.println("Programa encerrado.");
        } catch (NoSuchElementException e) {
            // Entrada de dados encerrada (ex.: Ctrl+D / Ctrl+Z)
            System.out.println("\nEntrada encerrada. Saindo do programa...");
        }
    }
}

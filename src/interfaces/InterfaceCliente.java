package interfaces;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static database.Database.*;

public class InterfaceCliente {

    public static void inicial(Cliente cliente) {
        System.out.println("Bem-vindo " + cliente.getNome());
        System.out.println("1 - Realizar compra");
        System.out.println("2 - Ver Compras");
        System.out.println("0 - Deslogar");
    }

    public static void vendas(Cliente cliente) {
        System.out.println();
        System.out.println("************************************************************");
        System.out.println("COMPRAS EFETUADAS");
        vendas.forEach(venda -> {
            if (venda.getCliente().getUsername().equals(cliente.getUsername())) {
                System.out.println("************************************************************");
                System.out.println("Compra de código: " + venda.getCodigo() + " na empresa "
                        + venda.getEmpresa().getNome() + ": ");
                venda.getItens().forEach(x -> {
                    System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                });
                System.out.println("Total: R$" + venda.getValor());
                System.out.println("************************************************************");
            }
        });
    }

    public static void realizarCompras(Cliente cliente) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
        empresas.forEach(empresa -> System.out.println(empresa.getId() + " - " + empresa.getNome()));
        Integer empresaEscolhidaId = sc.nextInt();
        Empresa empresaEscolhida = empresas.stream()
                .filter(empresa -> empresa.getId().equals(empresaEscolhidaId))
                .findFirst()
                .orElse(null);

        if (empresaEscolhida != null) {

            List<Produto> produtosDaEmpresa = new ArrayList<>();
            produtos.forEach(produto -> {
                if (Objects.equals(produto.getEmpresa().getId(), empresaEscolhida.getId()))
                    produtosDaEmpresa.add(produto);
            });

            List<Produto> carrinho = new ArrayList<>();

            Integer escolhaProduto;
            do {

                System.out.println("Escolha os seus produtos: ");
                produtosDaEmpresa.forEach(produto ->
                        System.out.println(produto.getId() + " - " + produto.getNome() + "    R$" + produto.getPreco() + "    " + produto.getQuantidade() + " unidades disponíveis")
                );

                System.out.println("0 - Finalizar compra");

                escolhaProduto = sc.nextInt();
                if (escolhaProduto != 0) {
                    Integer finalEscolhaProduto = escolhaProduto;
                    Produto produtoEscolhido = produtosDaEmpresa.stream()
                            .filter(produto -> produto.getId().equals(finalEscolhaProduto))
                            .findFirst()
                            .orElse(null);

                    if (produtoEscolhido != null) {
                        if (produtoEscolhido.getQuantidade() > 0) {
                            carrinho.add(produtoEscolhido);
                            produtoEscolhido.setQuantidade(produtoEscolhido.getQuantidade() - 1);
                        } else System.out.println("Produto indisponível");
                    }
                    else System.out.println("Produto não encontrado");
                }

            } while (escolhaProduto != 0);

            System.out.println("************************************************************");
            System.out.println("Resumo da compra: ");
            carrinho.forEach(produto ->
                    System.out.println(produto.getId() + " - " + produto.getNome() + "    R$" + produto.getPreco())
            );

            Venda venda = criarVenda(carrinho, empresaEscolhida, cliente);

            System.out.println("Total: R$" + venda.getValor());
            System.out.println("************************************************************");
        } else System.out.println("Empresa não encontrada");
    }

    private static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente) {
        Double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
        Double comissaoSistema = total * empresa.getTaxa();

        int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCodigo() + 1;
        Venda venda = new Venda(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
        vendas.add(venda);

        empresa.setSaldo(empresa.getSaldo() + (total - comissaoSistema));
        return venda;
    }
}

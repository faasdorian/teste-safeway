package interfaces;

import model.Empresa;

import static database.Database.produtos;
import static database.Database.vendas;

public class InterfaceEmpresa {

    public static void inicial(Empresa empresa) {
        System.out.println("Bem-vindo " + empresa.getNome());

        System.out.println("1 - Listar vendas");
        System.out.println("2 - Ver produtos");
        System.out.println("0 - Deslogar");
    }

    public static void vendas(Empresa empresa) {

        System.out.println("************************************************************");
        System.out.println("VENDAS EFETUADAS");
        vendas.forEach(venda -> {
            if (venda.getEmpresa().getId().equals(empresa.getId())) {
                System.out.println("************************************************************");
                System.out.println("Venda de código: " + venda.getCodigo() + " no CPF "
                        + venda.getCliente().getCpf() + ": ");
                venda.getItens().forEach(x -> {
                    System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                });
                System.out.println("Total Venda: R$" + venda.getValor());
                System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
                System.out.println("Total Líquido  para empresa: R$ "
                        + (venda.getValor() - venda.getComissaoSistema()));
                System.out.println("************************************************************");
            }

        });
        System.out.println("Saldo Empresa: " + empresa.getSaldo());
        System.out.println("************************************************************");
    }

    public static void produtos(Empresa empresa) {
        System.out.println();
        System.out.println("************************************************************");
        System.out.println("MEUS PRODUTOS");
        produtos.forEach(produto -> {
            if (produto.getEmpresa().getId().equals(empresa.getId())) {
                System.out.println("************************************************************");
                System.out.println("Código: " + produto.getId());
                System.out.println("Produto: " + produto.getNome());
                System.out.println("Quantidade em estoque: " + produto.getQuantidade());
                System.out.println("Valor: R$" + produto.getPreco());
                System.out.println("************************************************************");
            }

        });
        System.out.println("Saldo Empresa: " + empresa.getSaldo());
        System.out.println("************************************************************");
    }
}

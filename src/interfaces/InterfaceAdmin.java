package interfaces;

import model.Empresa;

import java.util.Scanner;

import static database.Database.empresas;

public class InterfaceAdmin {
    public static void inicial() {
        System.out.println("Bem-vindo Administrador");
        System.out.println("1 - Empresas");
        System.out.println("0 - Deslogar");
    }

    public static void empresas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecionar Empresa");
        empresas.forEach(empresa -> System.out.println(empresa.getId() + " - " + empresa.getNome()));
        Integer empresaEscolhidaId = sc.nextInt();
        Empresa empresaEscolhida = empresas.stream()
                .filter(empresa -> empresa.getId().equals(empresaEscolhidaId))
                .findFirst()
                .orElse(null);

        if (empresaEscolhida != null) empresa(empresaEscolhida);
        else System.out.println("Empresa não encontrada");
    }

    private static void empresa(Empresa empresaEscolhida) {
        System.out.println("Empresa: " + empresaEscolhida.getNome());
        System.out.println("1 - Ver produtos");
        System.out.println("2 - Ver vendas");

        Scanner sc = new Scanner(System.in);
        Integer escolha = sc.nextInt();

        switch (escolha) {
            case 1 -> InterfaceEmpresa.produtos(empresaEscolhida);
            case 2 -> InterfaceEmpresa.vendas(empresaEscolhida);
        }

    }
}

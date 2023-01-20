import database.Database;
import interfaces.InterfaceCliente;
import interfaces.InterfaceEmpresa;
import interfaces.InterfaceAdmin;
import model.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database.init();
        executar();
    }

    public static void executar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Login");
        System.out.print("Usuário: ");
        String username = sc.next();
        System.out.print("Senha: ");
        String senha = sc.next();

        Usuario usuarioLogado = Database.usuarios.stream()
                .filter(x -> x.getUsername().equals(username))
                .toList()
                .get(0);

        if (usuarioLogado != null) {

            if ((usuarioLogado.getSenha().equals(senha))) {

                if (usuarioLogado.IsEmpresa()) {
                    Integer escolha;
                    do {
                        System.out.println();
                        InterfaceEmpresa.inicial(usuarioLogado.getEmpresa());
                        escolha = sc.nextInt();
                        switch (escolha) {
                            case 1 -> InterfaceEmpresa.vendas(usuarioLogado.getEmpresa());
                            case 2 -> InterfaceEmpresa.produtos(usuarioLogado.getEmpresa());
                            case 0 -> System.out.println("Deslogado");
                            default -> System.out.println("Opção inválida");
                        }
                    } while (escolha != 0);
                }
                else if (usuarioLogado.IsAdmin()) {
                    Integer escolha;
                    do {
                        System.out.println();
                        InterfaceAdmin.inicial();
                        escolha = sc.nextInt();
                        switch (escolha) {
                            case 1 -> InterfaceAdmin.empresas();
                            case 0 -> System.out.println("Deslogando");
                            default -> System.out.println("Opção inválida");
                        }
                    } while (escolha != 0);
                }
                else {
                    Integer escolha;
                    do {
                        System.out.println();
                        InterfaceCliente.inicial(usuarioLogado.getCliente());
                        escolha = sc.nextInt();
                        switch (escolha) {
                            case 1 -> InterfaceCliente.realizarCompras(usuarioLogado.getCliente());
                            case 2 -> InterfaceCliente.vendas(usuarioLogado.getCliente());
                            case 0 -> System.out.println("Deslogando");
                            default -> System.out.println("Opção inválida");
                        }
                    } while (escolha != 0);
                }

            } else System.out.println("Senha incorreta");

        } else System.out.println("Usuário não encontrado");
        executar();
    }
}

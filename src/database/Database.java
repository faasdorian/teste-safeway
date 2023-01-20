package database;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Empresa> empresas = new ArrayList<>();
    public static List<Produto> produtos = new ArrayList<>();
    public static List<Venda> vendas = new ArrayList<>();

    public static void init() {
        Empresa empresa = new Empresa(2, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
        Empresa empresa2 = new Empresa(1, "Level Varejo", "53239160000154", 0.05, 0.0);
        Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);
        empresas.add(empresa);
        empresas.add(empresa2);
        empresas.add(empresa3);

        produtos.add( new Produto(1, "Pão Frances", 5, 3.50, empresa) );
        produtos.add( new Produto(2, "Coturno", 10, 400.0, empresa2) );
        produtos.add( new Produto(3, "Jaqueta Jeans", 15, 150.0, empresa2) );
        produtos.add( new Produto(4, "Calça Sarja", 15, 150.0, empresa2) );
        produtos.add( new Produto(5, "Prato feito - Frango", 10, 25.0, empresa3) );
        produtos.add( new Produto(6, "Prato feito - Carne", 10, 25.0, empresa3) );
        produtos.add( new Produto(7, "Suco Natural", 30, 10.0, empresa3) );
        produtos.add( new Produto(8, "Sonho", 5, 8.50, empresa) );
        produtos.add( new Produto(9, "Croissant", 7, 6.50, empresa) );
        produtos.add( new Produto(10, "Ché Gelado", 4, 5.50, empresa) );

        Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
        Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);
        clientes.add(cliente);
        clientes.add(cliente2);

        usuarios.add( new Usuario("admin", "1234", null, null) );
        usuarios.add( new Usuario("empresa", "1234", null, empresa) );
        usuarios.add( new Usuario("cliente", "1234", cliente, null) );
        usuarios.add( new Usuario("cliente2", "1234", cliente2, null) );
        usuarios.add( new Usuario("empresa2", "1234", null, empresa2) );
        usuarios.add( new Usuario("empresa3", "1234", null, empresa3) );
    }
}

package br.com.alura.consulfipe.principal;

import br.com.alura.consulfipe.model.Dados;
import br.com.alura.consulfipe.model.DadosModelos;
import br.com.alura.consulfipe.model.Veiculo;
import br.com.alura.consulfipe.service.ConsumoApi;
import br.com.alura.consulfipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    ConsumoApi api = new ConsumoApi();
    Scanner sc = new Scanner(System.in);
    ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println("Qual tipo de veículo será consultado? [Carros/Motos/Caminhoes]:");
        var tipo = sc.nextLine().toLowerCase();

        var endereco = "https://parallelum.com.br/fipe/api/v1/" + tipo + "/marcas";
        var json = api.consumir(endereco);
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Qual o código da marca para consulta?");
        var codigoMarca = sc.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = api.consumir(endereco);
        var modeloLista = conversor.obterDados(json, DadosModelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado: ");
        var trecho = sc.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos()
                .stream().filter(m -> m.nome().toUpperCase().contains(trecho.toUpperCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\nDigite o codigo do modelo a ser buscado: ");
        var codigoModelo = sc.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = api.consumir(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = api.consumir(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);

            System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
            veiculos.forEach(System.out::println);
        }
    }
}

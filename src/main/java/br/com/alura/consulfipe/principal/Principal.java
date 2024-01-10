package br.com.alura.consulfipe.principal;

import br.com.alura.consulfipe.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    ConsumoApi api = new ConsumoApi();
    Scanner sc = new Scanner(System.in);

    public void exibeMenu() {
        System.out.println("Qual tipo de veículo será consultado? [Carros/Motos/Caminhoes]:");
        var veiculo = sc.nextLine().toLowerCase();
        var json = api.consumir("https://parallelum.com.br/fipe/api/v1/" + veiculo + "/marcas");
        System.out.println(json);
    }
}

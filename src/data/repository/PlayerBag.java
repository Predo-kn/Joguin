package data.repository;

import entities.classes.Classes;
import entities.itens.Item;
import entities.itens.Raridade;
import game.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerBag {

    private final ArrayList<Item> itens;

    public PlayerBag() {
        this.itens = new ArrayList<>();

        // 1 slot por raridade
        for (int i = 0; i < 5; i++) {
            itens.add(null);
        }
    }

    public void addItem(Item item, Classes classe) {
        if (item == null) {
            throw new InvalidArgumentException("Item não pode ser nulo");
        }

        int indice = indicePorRaridade(item.getRaridade());
        Item atual = itens.get(indice);

        if (atual == null) {
            itens.set(indice, item);
            System.out.println("Item adicionado à bag: " + item.getNome());
            item.aplicarEfeito(classe);
            return;
        }

        if (atual.getClass().equals(item.getClass())) {
            System.out.println("Item repetido recebido. Mantendo o atual.");
            return;
        }

        // Confirmação de troca
        Scanner sc = new Scanner(System.in);
        System.out.println("Slot de raridade ocupado!");
        System.out.println("Atual: " + atual.getNome());
        System.out.println("Novo: " + item.getNome());
        System.out.print("Deseja trocar? (Sim/Nao): ");

        if (sc.next().equalsIgnoreCase("Sim")) {

            // Se o item atual estiver equipado, remove da classe
            if (classe != null && classe.getItemEquipado() == atual) {
                classe.removerItemEquipado();
                System.out.println("Item equipado foi removido.");
            }

            itens.set(indice, item);
            System.out.println("Item trocado com sucesso!");
        } else {
            System.out.println("Item mantido.");
        }
    }

    public void equiparItem(Classes classe, Raridade raridade) {
        if (classe == null) {
            throw new InvalidArgumentException("Classe não pode ser nula");
        }

        int indice = indicePorRaridade(raridade);
        Item item = itens.get(indice);

        if (item == null) {
            throw new InvalidArgumentException("Não há item dessa raridade");
        }

        classe.equiparItem(item);
        System.out.println("Item equipado: " + item.getNome());
    }

    private int indicePorRaridade(Raridade raridade) {
        return switch (raridade) {
            case COMMON -> 0;
            case RARE -> 1;
            case EPIC -> 2;
            case LEGENDARY -> 3;
            case MITHIC -> 4;
        };
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void listarItens() {
        System.out.println("\n=== Itens na Bag ===");

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println(
                    Raridade.values()[i] + ": " +
                            (item == null ? "Vazio" : item.getNome())
            );
        }

        System.out.println("====================\n");
    }
}

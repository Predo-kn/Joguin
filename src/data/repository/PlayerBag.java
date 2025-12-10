package data.repository;

import entities.classes.Classes;
import entities.itens.Item;
import entities.itens.Raridade;
import game.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerBag <T>{
    private ArrayList<Item> itens;

    public PlayerBag() {
        this.itens = new ArrayList<>();
    }

    public void addItem(Item item){
        if (item == null) {
            throw new InvalidArgumentException("Item não pode ser nulo");
        }
        
        if (podeTrocar(item)){
            int indice = checaRaridade(item);
            if (indice < itens.size()) {
                itens.set(indice, item);
            } else {
                itens.add(indice, item);
            }
        }else {
            System.out.println("Item mantido");
        }
    }

    public boolean podeTrocar(Item item){
        if (item == null) {
            throw new InvalidArgumentException("Item não pode ser nulo");
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Em caso de troca, perderá o item equipado");

        int indice = checaRaridade(item);
        if(indice < itens.size() && this.itens.get(indice) != null){
            System.out.println("Item na Bag: " + this.itens.get(indice).toString());
            System.out.println("Novo Item: " + item.toString());
            System.out.println("Quer trocar (Sim)/(Nao)");
            return sc.next().equalsIgnoreCase("Sim");
        }else{
            System.out.println("Item adicionado com sucesso");
            return true;
        }
    }

    public int checaRaridade(Item item){
        if (item == null) {
            throw new InvalidArgumentException("Item não pode ser nulo");
        }
        
        Raridade raridade = item.getRaridade();
        if (raridade.equals(Raridade.COMMON)){
            return 0;
        }else if (raridade.equals(Raridade.RARE)){
            return 1;
        }else if(raridade.equals((Raridade.EPIC))){
            return 2;
        }else if(raridade.equals(Raridade.LEGENDARY)){
            return 3;
        }else{
            return 4;
        }
    }

    public void equipItem(Classes classes, int indiceRaridade) {
        if (classes == null) {
            throw new InvalidArgumentException("Personagem não pode ser nulo");
        }
        
        if (indiceRaridade < 0 || indiceRaridade >= itens.size()) {
            throw new InvalidArgumentException("Índice de item inválido");
        }
        
        Item item = itens.get(indiceRaridade);
        if (item == null) {
            throw new InvalidArgumentException("Slot de raridade vazio");
        }
        
        classes.equiparItem(item);
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        if (itens == null) {
            throw new InvalidArgumentException("Lista de itens não pode ser nula");
        }
        this.itens = itens;
    }

    public void listarItens() {
        System.out.println("\n=== Itens na Bag ===");
        if (itens.isEmpty()) {
            System.out.println("Nenhum item equipado");
        } else {
            for (int i = 0; i < itens.size(); i++) {
                Item item = itens.get(i);
                if (item != null) {
                    System.out.println(i + " - " + item.toString());
                }
            }
        }
        System.out.println("====================\n");
    }
}

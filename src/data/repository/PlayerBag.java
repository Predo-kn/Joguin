package data.repository;

import entities.classes.Classes;
import entities.itens.Item;
import entities.itens.Raridade;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerBag <T>{
    private ArrayList<Item> itens;

    public PlayerBag() {
        this.itens = new ArrayList<>();
    }

    public void addItem(Item item){
        if (podeTrocar(item)){
            itens.add(checaRaridade(item), item);
        }else {
            System.out.println("Item mantido");
        }
    }



    public boolean podeTrocar(Item item){
        Scanner sc = new Scanner(System.in);
        System.out.println("Em caso de troca, perderá o item equipado");

        if( this.itens.get(checaRaridade(item)) != null){
            System.out.println("Item na Bag: " + this.itens.get(checaRaridade(item)).toString());
            System.out.println("Novo Item: " + item.toString());
            System.out.println("Quer trocar (Sim)/(Nao)");
            return sc.next().equalsIgnoreCase("Sim");
        }else{
            itens.add(checaRaridade(item), item);
            System.out.println("Item adicionado com sucesso");
            return false;
        }
    }

    public int checaRaridade(Item item){
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

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public void equipItem(){

    }
}

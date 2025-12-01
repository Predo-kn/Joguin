package entities.itens;

import entities.classes.Classes;

import java.util.ArrayList;

public class PresaDoLobo  extends  Item{

    private String nome = "Presa do Lobo";
    private String info = "Daddd";
    private Raridade raridade = Raridade.COMMON;
    private ArrayList<Buff> buff;
    private double mult;

    public PresaDoLobo(){
        buff = new ArrayList<>();
        buff.add(Buff.DANO.ordinal(),Buff.MOVESPEED);



    }
    @Override
    public void setAtt(Classes cl){
        if (cl.getBag().getItens().contains(this)){
            cl.setDano(cl.getDano() +2);
            cl.setMoveSpeed(cl.getMoveSpeed() +1);
        }
    }


    /*public PresaDoLobo(String nome, String info, Raridade raridade){
        super(nome, info, raridade);
    }*/


    //1. Presa do Lobo — (Comum)
    //Drop de: Lobo Silvestre
    //Descrição: Um dente afiado que vibra com energia primal.
    //Efeito:
    //+2 de Dano
    //+1 Velocidade de Movimento
    //Design: A ideia é dar um leve empurrão no início sem afetar muito o balanceamento.
}

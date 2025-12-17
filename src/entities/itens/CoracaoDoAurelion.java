package entities.itens;

public class CoracaoDoAurelion extends Item{
    public CoracaoDoAurelion(String nome, String info, Raridade raridade) {
        super(
                "Coração Dourado de Aurelion",
                "Um núcleo pulsante de energia ancestral do Guardião" +
                        "Quando a vida cai abaixo de 30%, concede +4 de Dano até o fim do combate.",
                Raridade.MITHIC
        );
        addBuff(Buff.VIDA, 15);
        addBuff(Buff.DANO, 5);
        addBuff(Buff.MOVESPEED, 3);
    }
}



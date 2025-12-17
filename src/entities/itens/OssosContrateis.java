package entities.itens;

public class OssosContrateis extends Item{

    public OssosContrateis() {
        super(
                "Olho Vigilante",
                "Um olho preservado que nunca pisca. " +
                        "Concede +1 Attack Speed e +3 de dano no primeiro ataque do combate.",
                Raridade.RARE
        );

        addBuff(Buff.VIDA, 6);
        addBuff(Buff.ESCUDO, 2);

    }
    //Efeito:
    //+8 Vida Máxima
    //Reduz o dano recebido na primeira rodada em 20%
}

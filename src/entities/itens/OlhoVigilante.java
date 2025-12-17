package entities.itens;

public class OlhoVigilante extends Item {

    public OlhoVigilante() {
        super(
                "Olho Vigilante",
                "Um olho preservado que nunca pisca. " +
                        "Concede +1 Attack Speed e +3 de dano no primeiro ataque do combate.",
                Raridade.RARE
        );

        // Buff passivo
        addBuff(Buff.DANO, 2);
    }
}

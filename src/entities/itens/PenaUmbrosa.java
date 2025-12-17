package entities.itens;

public class PenaUmbrosa extends Item {

    public PenaUmbrosa() {
        super(
                "Olho Vigilante",
                "Um olho preservado que nunca pisca. " +
                        "Concede +1 Attack Speed e +3 de dano no primeiro ataque do combate.",
                Raridade.RARE
        );

        addBuff(Buff.ATKSPEED, 2);
    }
    //2. Pena Umbrosa — (Comum)
    //Drop de: Corvo Sombrio
    //Descrição: Uma pena negra que distorce a luz ao redor.
    //Efeito:
    //+3 de Escudo no início de cada combate
    //Design: Defesa pequena, mas consistente.
}

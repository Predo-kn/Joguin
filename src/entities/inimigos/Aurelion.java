package entities.inimigos;

public non-sealed class Aurelion extends Inimigo{
    private static final double VIDA_PADRAO = 100;
    private static final double DANO_PADRAO = 12;
    private static final double ESCUDO_PADRAO = 5;
    private static final double ATACK_SPEED_PADRAO = 2;
    private static final double MOVE_SPEED_PADRAO = 2;

    public Aurelion() {
        super(VIDA_PADRAO, DANO_PADRAO, ESCUDO_PADRAO, ATACK_SPEED_PADRAO, MOVE_SPEED_PADRAO);
        carregarAtributosDoArquivo("Aurelion");
    }

    public Aurelion(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}

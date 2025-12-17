package entities.classes;

import data.loader.DataLoader;
import data.repository.PlayerBag;
import entities.inimigos.Inimigo;
import entities.itens.Item;
import entities.itens.Mascara;
import entities.player.Player;
import game.exceptions.InvalidArgumentException;

import java.util.Map;

public abstract sealed class Classes extends Player
        permits Archer, Knight, Mage, Tanker {

    private double vida;
    private double vidaMaxima;
    private double dano;
    private double escudo;
    private double attackSpeed;
    private double moveSpeed;

    private Item itemEquipado;
    private boolean bonusVidaAtivado;

    public Classes(String nome,
                   double vida,
                   double dano,
                   double escudo,
                   double attackSpeed,
                   double moveSpeed) {

        super(nome);
        super.setClasse(this);

        if (vida <= 0) throw new InvalidArgumentException("Vida deve ser maior que 0");
        if (dano < 0) throw new InvalidArgumentException("Dano não pode ser negativo");
        if (escudo < 0) throw new InvalidArgumentException("Escudo não pode ser negativo");
        if (attackSpeed < 0) throw new InvalidArgumentException("Attack Speed não pode ser negativo");
        if (moveSpeed < 0) throw new InvalidArgumentException("Move Speed não pode ser negativo");

        this.vida = vida;
        this.vidaMaxima = vida;
        this.dano = dano;
        this.escudo = escudo;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;

        this.itemEquipado = null;
        this.bonusVidaAtivado = false;
    }


    protected void carregarAtributosDoArquivo(String nomeArquivo) {
        try {
            Map<String, Double> dados = DataLoader.carregarClasse(nomeArquivo);

            if (dados.containsKey("vida")) {
                this.vida = dados.get("vida");
                this.vidaMaxima = this.vida;
            }
            if (dados.containsKey("dano")) this.dano = dados.get("dano");
            if (dados.containsKey("escudo")) this.escudo = dados.get("escudo");
            if (dados.containsKey("attackSpeed")) this.attackSpeed = dados.get("attackSpeed");
            if (dados.containsKey("moveSpeed")) this.moveSpeed = dados.get("moveSpeed");

        } catch (Exception e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }


    public double getVida() {
        return vida;
    }

    public double getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVida(double novaVida) {
        this.vida = Math.max(0, novaVida);

        if (itemEquipado instanceof Mascara
                && !bonusVidaAtivado
                && this.vida <= vidaMaxima * 0.3) {

            this.dano += 4;
            bonusVidaAtivado = true;
            System.out.println("Máscara Sem Identidade ativada! +4 Dano");
        }
    }


    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        if (dano < 0) throw new InvalidArgumentException("Dano não pode ser negativo");
        this.dano = dano;
    }

    public double getEscudo() {
        return escudo;
    }

    public void setEscudo(double escudo) {
        if (escudo < 0) throw new InvalidArgumentException("Escudo não pode ser negativo");
        this.escudo = escudo;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        if (attackSpeed < 0)
            throw new InvalidArgumentException("Attack Speed não pode ser negativo");
        this.attackSpeed = attackSpeed;
    }


    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        if (moveSpeed < 0)
            throw new InvalidArgumentException("Move Speed não pode ser negativo");
        this.moveSpeed = moveSpeed;
    }


    public void atacar(Inimigo inimigo) {
        if (inimigo == null)
            throw new InvalidArgumentException("Inimigo não pode ser nulo");

        if (!inimigo.esquivar(this)) {
            double danoFinal = Math.max(0, this.dano - inimigo.getEscudo());
            inimigo.setVida(inimigo.getVida() - danoFinal);

            System.out.println("Inimigo recebeu " + danoFinal + " de dano");
        } else {
            System.out.println("Inimigo esquivou");
        }
    }


    public double chanceEsquiva(Inimigo inimigo) {
        double diff = this.moveSpeed - inimigo.getAttackSpeed();
        if (diff <= 0) return 0;
        return Math.min(diff, 10) * 10;
    }

    public boolean esquivar(Inimigo inimigo) {
        return Math.random() < (chanceEsquiva(inimigo) / 100);
    }

    public void equiparItem(Item item) {
        if (item == null) throw new InvalidArgumentException("Item não pode ser nulo");

        this.itemEquipado = item;
        this.bonusVidaAtivado = false;
        item.aplicarEfeito(this);
    }

    public void removerItemEquipado() {
        this.itemEquipado = null;
    }


    public Item getItemEquipado() {
        return itemEquipado;
    }

    public PlayerBag getBag() {
        return super.getBag();
    }

    public void resetarCombate() {
        bonusVidaAtivado = false;
    }

    @Override
    public String toString() {
        return "Atributos: " +
                "\nVida = " + vida +
                "\nDano = " + dano +
                "\nEscudo = " + escudo +
                "\nAttackSpeed = " + attackSpeed +
                "\nMoveSpeed = " + moveSpeed;
    }
}

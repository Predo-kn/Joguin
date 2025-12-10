package data.loader;

import entities.classes.Classes;
import entities.classes.Archer;
import entities.classes.Knight;
import entities.classes.Mage;
import entities.classes.Tanker;
import game.exceptions.GameException;

public class ClassFactory {
    
    public static Classes criarClasse(String nomeClasse) {
        return criarClasse(nomeClasse, 0);
    }
    
    public static Classes criarClasse(String nomeClasse, int tipo) {
        switch (nomeClasse.toLowerCase()) {
            case "archer":
                return new Archer();
            case "knight":
                return new Knight();
            case "mage":
                return new Mage();
            case "tanker":
                return new Tanker();
            default:
                throw new GameException("Classe desconhecida: " + nomeClasse);
        }
    }
}

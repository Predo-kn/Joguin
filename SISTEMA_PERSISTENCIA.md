SISTEMA DE PERSISTÊNCIA COM ARQUIVOS

O sistema carrega dados de classes e inimigos de arquivos TXT automaticamente ao instanciar.

ESTRUTURA:
├── src/data/
│   ├── classes/
│   │   ├── Archer.txt
│   │   ├── Knight.txt
│   │   ├── Mage.txt
│   │   └── Tanker.txt
│   ├── inimigos/
│   │   ├── Aurelion.txt
│   │   ├── Carnical.txt
│   │   ├── CorvoSombrio.txt
│   │   ├── EcoAbismo.txt
│   │   ├── LarvaObservadora.txt
│   │   ├── LoboSilvestre.txt
│   │   └── SemRosto.txt
│   ├── players/
│   │   └── Arthur.txt (exemplo)
│   └── loader/
│       ├── DataLoader.java
│       └── PlayerData.java

FORMATO DOS ARQUIVOS:
vida=100
dano=12
escudo=5
attackSpeed=2
moveSpeed=2

COMO USAR:

1. Carregar uma classe com dados do arquivo:
    Knight knight = new Knight();
    System.out.println("Vida: " + knight.getVida());
    
2. Ou com valores customizados:
    Knight knight = new Knight(100, 20, 5, 3, 3);

3. Carregar um inimigo:
    Aurelion boss = new Aurelion();
    System.out.println("Vida do boss: " + boss.getVida());

4. Salvar dados do jogador:
    PlayerData.salvarJogador(knight);
    
5. Verificar se jogador existe:
    boolean existe = PlayerData.jogadorExiste("Arthur");

6. Carregar dados de um jogador:
    Map<String, Double> dados = PlayerData.carregarJogador("Arthur");

ARQUIVOS PADRÃO CRIADOS:

Classes (com stats balanceados):
- Archer: Vida=45, Dano=10, Escudo=2, AtkSpeed=3, MoveSpeed=4
- Knight: Vida=55, Dano=12, Escudo=4, AtkSpeed=2, MoveSpeed=2
- Mage: Vida=40, Dano=16, Escudo=0, AtkSpeed=2, MoveSpeed=2
- Tanker: Vida=75, Dano=8, Escudo=6, AtkSpeed=1, MoveSpeed=1

Inimigos (com stats baixos para começar):
- Aurelion: Vida=100, Dano=12, Escudo=5, AtkSpeed=2, MoveSpeed=2
- Carnical: Vida=40, Dano=7, Escudo=2, AtkSpeed=2, MoveSpeed=2
- CorvoSombrio: Vida=22, Dano=4, Escudo=0, AtkSpeed=4, MoveSpeed=5
- EcoAbismo: Vida=50, Dano=8, Escudo=3, AtkSpeed=2, MoveSpeed=2
- LarvaObservadora: Vida=35, Dano=6, Escudo=3, AtkSpeed=2, MoveSpeed=2
- LoboSilvestre: Vida=25, Dano=5, Escudo=1, AtkSpeed=3, MoveSpeed=4
- SemRosto: Vida=70, Dano=10, Escudo=4, AtkSpeed=3, MoveSpeed=3

CLASSES PRINCIPAIS:

DataLoader:
- carregarClasse(String nome)
- carregarInimigo(String nome)
- carregarJogador(String nome)
- carregarArquivo(String caminho)
- salvarDados(String caminho, Map<String, Double> dados)
- salvarJogador(String nome, Map<String, Double> dados)
- jogadorExiste(String nome)

PlayerData:
- salvarJogador(Player player)
- carregarJogador(String nome)
- jogadorExiste(String nome)

Classes e Inimigos:
- carregarAtributosDoArquivo(String nome) - lê do arquivo e aplica os valores

package entities.itens;

public abstract sealed class Item permits Teste {
    private String nome;
    private String info;
    private Raridade raridade;
    private ItemExclusivo itemExclusivo;

    public Item(String nome, String info, Raridade raridade){
        this.nome = nome;
        this.info = info;
        this.raridade = raridade;
        this.itemExclusivo = null;
    }

    public Item(String nome, String info, Raridade raridade, ItemExclusivo itemExclusivo){
        this.nome = nome;
        this.nome = info;
        this.raridade = raridade;
        this.itemExclusivo = itemExclusivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public ItemExclusivo getItemExclusivos() {
        return itemExclusivo;
    }

    public void setItemExclusivos(ItemExclusivo itemExclusivos) {
        this.itemExclusivo = itemExclusivos;
    }

    @Override
    public String toString() {
        if (this.itemExclusivo != null) {
            return "Item{" +
                    "nome='" + nome + '\'' +
                    ", info='" + info + '\'' +
                    ", raridade=" + raridade +
                    ", itemExclusivo=" + itemExclusivo +
                    '}';
        }else{
            return "Item{" +
                    "nome='" + nome + '\'' +
                    ", info='" + info + '\'' +
                    ", raridade=" + raridade +
                    '}';
        }
    }
}

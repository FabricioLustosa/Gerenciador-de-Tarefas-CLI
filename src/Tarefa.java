public class Tarefa {
    String nome;
    String descricao;
    boolean concluida;

    public Tarefa(){};

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setConcluida(boolean concluida){
      this.concluida = concluida;
    }

    @Override
    public String toString(){
        return "Tarefa: " + nome + "\n" + "Descrição: " + descricao;
    }

}

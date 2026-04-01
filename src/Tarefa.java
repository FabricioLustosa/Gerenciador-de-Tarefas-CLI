public class Tarefa {
    private Integer id;
    private String nome;
    private String descricao;
    boolean concluida;

    public Tarefa(){};

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.concluida = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isConcluida(){
        return concluida;
    }

    public void setConcluida(boolean concluida){
      this.concluida = concluida;
    }

    @Override
    public String toString(){
        return "Tarefa: " + nome + "\n" + "Descrição: " + descricao + "\n" + "Concluída " + (concluida ? "Sim" : "Não");
    }

}

public class Tarefa {
    private Integer id;
    private String nome;
    private String descricao;
    private TarefaStatus status;

    public Tarefa(){};

    public Tarefa(String nome, String descricao, TarefaStatus status) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
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

    public TarefaStatus getStatus(){
        return status;
    }

    public void setStatus(TarefaStatus status){
      this.status = status;
    }

    // Métodos auxiliares
    public boolean isConcluida() {
        return status == TarefaStatus.CONCLUIDA;
    }

    public boolean isPendente() {
        return status == TarefaStatus.PENDENTE;
    }

    @Override
    public String toString() {
        return String.format("ID: %d \n" +
                "Nome: %s \n" +
                "Descrição: %s \n" +
                "Status: %s",
                id, nome, descricao, status.getDescricao());
    }

}

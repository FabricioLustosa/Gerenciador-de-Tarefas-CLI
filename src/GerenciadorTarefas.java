import java.io.*;
import java.util.ArrayList;

public class GerenciadorTarefas {

    ArrayList<Tarefa> tarefas = new ArrayList<>();
    ArrayList<Tarefa> tarefasConcluidas = new ArrayList<>();
    Tarefa ultimaConcluida;

    File arquivo;

    public File criarArquivo(String path, String nomeArquivo){
        try{
            arquivo = new File(path, nomeArquivo);

            if(arquivo.createNewFile()){
                System.out.println("Arquivo criado: " + arquivo.getName());
            }else{
                System.out.println("O arquivo já existe.");
            }
            return arquivo;
        }catch(IOException e){
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
        return null;
    }
    public Tarefa addTarefa(String nome, String descricao){
            Tarefa tarefa = new Tarefa(nome, descricao);
            tarefas.add(tarefa);
            salvarArquivo();
            return tarefa;
    }

    public String listarTarefas(){

            StringBuilder sb = new StringBuilder();
            int count = 1;

            sb.append("\n=== PENDENTES ===.\n");
            if(!tarefas.isEmpty()){
                for(Tarefa tarefa : tarefas) {
                    sb.append("[").append(count).append("] ")
                            .append(tarefa.getNome()).append("\n");
                    sb.append("Descrição: ").append(tarefa.getDescricao()).append("\n");
                    count++;
                }
            }else{
                sb.append("Sem tarefas pendentes.");
            }
            sb.append("\n=== CONCLUÍDAS ===.\n");
            if(!tarefasConcluidas.isEmpty()){
                for(Tarefa tarefa : tarefasConcluidas) {
                    sb.append("[").append(count).append("] ")
                            .append(tarefa.getNome()).append("\n");
                    sb.append("Descrição: ").append(tarefa.getDescricao()).append("\n");
                    count++;
                }
            }else{
                sb.append("Nenhuma tarefa foi concluida.\n");
            }
            return sb.toString();

    }

    public String carregarTarefas(){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            String nome = null;
            String descricao;

            boolean secaoConcluidas = false;
            tarefas.clear();
            tarefasConcluidas.clear();

            while((linha = br.readLine()) != null){
                if(linha.equals("===CONCLUÍDAS===")){
                    secaoConcluidas = true;
                    continue;
                }
                if(linha.startsWith("Tarefa: ") ){
                    nome =  linha.substring("Tarefa: ".length());
                }else if((linha.startsWith("Descrição: "))) {
                    descricao = linha.substring("Descrição: ".length());

                    if(nome != null){
                        Tarefa tarefa = new Tarefa(nome, descricao);

                        if (secaoConcluidas) {
                            tarefasConcluidas.add(tarefa);
                        } else {
                            tarefas.add(tarefa);
                        }
                    }
                    nome = null;
                    descricao = null;
                }
            }
        }catch(IOException e){
            return "Erro: " + e.getMessage();
        }
        return "Existem " + tarefas.size() + " tarefas pendentes e " + tarefasConcluidas.size() + " concluídas.\n";
    }

    public boolean marcarConcluida(int input){
       if(input < 0 || input >= tarefas.size()){
           return false;
       }else {
           Tarefa tarefaConcluida = tarefas.remove(input);
           tarefaConcluida.setConcluida(true);
           tarefasConcluidas.add(tarefaConcluida);
           salvarArquivo();

           ultimaConcluida = tarefaConcluida;

           return true;
       }
    }


    public String mostrarOpecoesDaLista(){
        return "O que deseja fazer em sua lista de tarefas? \n" +
                "[1] - Criar tarefas \n" +
                "[2] - Listar tarefas \n" +
                "[3] - Marcar tarefas como concluída \n" +
                "[4] - Deletar tarefas \n" +
                "[5] - Sair";
    }

    public void salvarArquivo(){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {

            bw.write("===PENDENTES===");
            bw.newLine();
            if(tarefas.isEmpty()){
                bw.write("Todas as tarefas foram concluídas.");
                bw.newLine();
            }
            for(Tarefa tarefa : tarefas){
                bw.write("Tarefa: " + tarefa.getNome());
                bw.newLine();
                bw.write("Descrição: " + tarefa.getDescricao());
                bw.newLine();
            }
            bw.write("===CONCLUÍDAS===");
            bw.newLine();
            if(tarefasConcluidas.isEmpty()){
                bw.write("Nenhum tarefa foi concluída.");
            }
            for(Tarefa tarefasConcluidas : tarefasConcluidas){
                bw.write("Tarefa: " + tarefasConcluidas.getNome());
                bw.newLine();
                bw.write("Descrição: " + tarefasConcluidas.getDescricao());
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao criar a tarefa " + e.getMessage());
        }
    }

    public boolean deletarTarefa(ArrayList<Tarefa> lista, int indice){
        if(lista.isEmpty() || indice < 0 || indice >= lista.size()){
            return false;
        }else{
            lista.remove(indice);
            salvarArquivo();

            return true;
        }
    }

    public ArrayList<Tarefa> pegarListaDePendentes(){
        return tarefas;
    }
    public ArrayList<Tarefa> pegarListaDeConcluidas(){
        return tarefasConcluidas;
    }


    public void sair(){
        salvarArquivo();
        System.exit(0);
    }
}

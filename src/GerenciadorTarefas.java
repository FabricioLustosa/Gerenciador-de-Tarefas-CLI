import java.awt.*;
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
                Desktop.getDesktop().open(arquivo);
            }else{
                System.out.println("O arquivo já existe.");
                Desktop.getDesktop().open(arquivo);
            }
            return arquivo;
        }catch(IOException e){
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
        return null;
    }
    public Tarefa addtarefa(String nome, String descricao){
            Tarefa tarefa = new Tarefa(nome, descricao);
            tarefas.add(tarefa);
            salvarArquivo();
            return tarefa;
    }

    public String listarTarefas(){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){

            String linha;
            int count = 1;
            StringBuilder sb = new StringBuilder();

            while((linha = br.readLine()) != null){
                if(linha.startsWith("Tarefa: ")){
                    sb.append("[" + count + "] " + linha +"\n");
                }

                if(linha.startsWith("Descrição: ")){
                    sb.append(linha + "\n");
                    count++;
                }
            }
            return sb.toString();


        }catch(IOException e){
            return "Erro: " + e.getMessage();
        }
    }

    public String carregarTarefas(){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            String nome = "";
            String descricao;
            while((linha = br.readLine()) != null){
                if(linha.startsWith("Tarefa: ") ){
                    nome =  linha.substring("Tarefa: ".length());
                }else if((linha.startsWith("Descrição: "))){
                    descricao = linha.substring("Descrição: ".length());
                    Tarefa tarefa = new Tarefa(nome, descricao);
                    tarefas.add(tarefa);
                }
            }
        }catch(IOException e){
            return "Erro: " + e.getMessage();
        }
        return "Existem " + tarefas.size() + " tarefas na lista. \n";
    }

    public boolean marcarConluida(int input){
       if(input < 0 || input > tarefas.size()){
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
                bw.write(tarefa.toString());
                bw.newLine();
            }
            bw.write("===CONCLUÍDAS===");
            bw.newLine();
            for(Tarefa tarefasConcluidas : tarefasConcluidas){
                bw.write(tarefasConcluidas.toString());
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao criar a tarefa " + e.getMessage());
        }
    }

    public void sair(){
        System.exit(0);
    }
}

import java.io.*;
import java.util.ArrayList;

public class GerenciadorTarefas {

    ArrayList<Tarefa> tarefas = new ArrayList<>();

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
    public Tarefa addtarefa(String nome, String descricao){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {

            Tarefa tarefa = new Tarefa(nome, descricao);
            tarefas.add(tarefa);

            bw.write(tarefa.toString());
            bw.newLine();

            return tarefa;

        } catch (IOException e) {
            System.out.println("Erro ao criar a tarefa " + e.getMessage());
        }
        return null;
    }

    public void listarTarefas(){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            while((linha = br.readLine()) != null){
                System.out.println(linha);
                if(linha.startsWith("Descrição: ")){
                    System.out.println();
                }
            }
        }catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Existem " + tarefas.size() + " tarefas na lista");
    }

    public void carregarTarefas(){
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
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Existem " + tarefas.size() + " tarefas na lista");
    }

    public void marcarConluida(){
        System.out.println(tarefas);
    }

    public void mostrarOpecoesDaLista(){
        System.out.println();
        System.out.println("O que deseja fazer em sua lista de tarefas? \n" +
        "[1] - Criar tarefas \n" +
        "[2] - Listar tarefas \n" +
        "[3] - Marcar tarefas como concluída \n" +
        "[4] - Deletar tarefas \n" +
        "[5] - Sair");
    }

    public void sair(){
        System.exit(0);
    }
}

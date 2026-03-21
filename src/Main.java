import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o caminho para a criação do arquivo:");
        String path = sc.nextLine();
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = sc.nextLine();

        if(!nomeArquivo.toLowerCase().endsWith(".txt")){
            nomeArquivo += ".txt";
        }

        File arquivo = new File(path, nomeArquivo);

        ArrayList<Tarefa> tarefas = new ArrayList<>();


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
                while(true) {
                    System.out.println("Digite o nome da tarefa a ser adicionada: ");
                    String tarefaNome = sc.nextLine();
                    System.out.println("Digite a descrição da tarefa a ser adicionada: ");
                    String tarefaDescricao = sc.nextLine();
                    Tarefa tarefa = new Tarefa(tarefaNome, tarefaDescricao);
                    tarefas.add(tarefa);

                    System.out.println("Deseja adicionar outra tarefa? [s/n]");
                    String resposta = sc.nextLine();

                    if(resposta.equalsIgnoreCase("n")){
                        break;
                    }
                }
                for (Tarefa tar : tarefas) {
                    bw.write(tar.toString());
                    bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar a tarefa " + e.getMessage());
        }



        System.out.println(tarefas);
    }
}

import db.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();
        System.out.println("Conexão: " + conn);


        Scanner sc = new Scanner(System.in);
        GerenciadorTarefas gerenciadorTarefas = new GerenciadorTarefas();

        System.out.print("Digite o caminho para a criação do arquivo: ");
        String path = sc.nextLine();
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = sc.nextLine();

        if(!nomeArquivo.toLowerCase().endsWith(".txt")){
            nomeArquivo += ".txt";
        }
        gerenciadorTarefas.criarArquivo(path, nomeArquivo);


        while(true) {

            System.out.println(gerenciadorTarefas.mostrarOpecoesDaLista());

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    while (true) {
                        System.out.println("Digite o nome da tarefa a ser adicionada: ");
                        String tarefaNome = sc.nextLine();
                        System.out.println("Digite a descrição da tarefa a ser adicionada: ");
                        String tarefaDescricao = sc.nextLine();

                        gerenciadorTarefas.addTarefa(tarefaNome, tarefaDescricao);

                        System.out.println("Deseja adicionar outra tarefa? [s/n]");
                        String resposta = sc.nextLine();

                        if (resposta.equalsIgnoreCase("n")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println(gerenciadorTarefas.listarTarefas());
                    continue;
                case 3:
                    while(true){
                        System.out.println("Qual tarefa deseja marcar como concluída?");
                        System.out.println();
                        System.out.println(gerenciadorTarefas.listarTarefas());
                        int tarefaConcluida = sc.nextInt() - 1;
                        sc.nextLine();

                        if(!gerenciadorTarefas.marcarConcluida(tarefaConcluida)){
                            System.out.println("Tarefa não encontrada, digite um índice válido...");
                            continue;
                        }else{
                            System.out.println("Tarefa: " + gerenciadorTarefas.ultimaConcluida.getNome() + "\n Status: concluída. \n");
                        }
                        break;
                    }
                    break;
                case 4:
                    System.out.print("Digite o Id da tarefa que deseja deletar: ");
                    gerenciadorTarefas.listarTarefas();
                    int id = sc.nextInt();
                    gerenciadorTarefas.deletarTarefa(id);
                    break;
                case 5:
                    gerenciadorTarefas.sair();
            }
        }
    }
}

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GerenciadorTarefas gerenciadorTarefas = new GerenciadorTarefas();

        while(true) {
            List<Tarefa> tarefas = gerenciadorTarefas.listarTarefas();
            String numEscolha;
            System.out.println("O que deseja fazer em sua lista de tarefas?");

            while(true){
                System.out.println(gerenciadorTarefas.mostrarOpecoesDaLista());
                try{
                    numEscolha = sc.nextLine();
                    if(numEscolha.isBlank()){
                        System.out.println("O campo não pode ser vazio!");
                        System.out.println();
                        continue;
                    }

                    Integer.parseInt(numEscolha);
                    break;
                }catch(NumberFormatException e){
                    System.out.println("Digite apenas números!");
                }
            }

            int escolha = Integer.parseInt(numEscolha);
            switch (escolha) {
                case 1:
                    while (true) {
                        System.out.println("Digite o nome da tarefa a ser adicionada: ");
                        String tarefaNome = sc.nextLine();
                        if(tarefaNome.isBlank()){
                            System.out.println("FALHA: Digite um nome para a tarefa.");
                            continue;
                        }

                        String tarefaDescricao;
                        while(true){
                            System.out.println("Digite a descrição da tarefa a ser adicionada: ");
                            tarefaDescricao = sc.nextLine();
                            if(tarefaDescricao.isEmpty()){
                                System.out.println("FALHA: Digite uma descrição para a Tarefa.");
                                continue;
                            }
                            break;
                        }

                        gerenciadorTarefas.addTarefa(tarefaNome, tarefaDescricao);
                        System.out.println("TAREFA ADICIONADA!");
                        break;
                    }
                    break;
                case 2:
                    for(Tarefa tarefa : tarefas){
                        System.out.println(tarefa + "\n");
                    }
                    System.out.println();
                    continue;
                case 3:
                    while(true){
                        System.out.println(gerenciadorTarefas.listarTarefas());
                        int idAtualizar;

                        while(true){
                            System.out.print("Digite o ID da tarefa que deseja atualizar: ");
                            try{
                                idAtualizar = Integer.parseInt(sc.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("Digite apenas números!");
                                continue;
                            }
                            break;
                        }


                        boolean encontrou = false;

                        for(Tarefa tarefa : tarefas){
                            if(tarefa.getId() == idAtualizar){
                                System.out.print("Digite o novo nome da tarefa: ");
                                String novoNome = sc.nextLine();
                                System.out.print("Digite a nova descrição da tarefa: ");
                                String novaDescricao = sc.nextLine();
                                gerenciadorTarefas.atualizarTarefa(idAtualizar, novoNome, novaDescricao);
                                encontrou = true;
                                System.out.println("TAREFA ATUALIZADA!");
                                System.out.println();
                                break;
                            }
                        }
                        if(!encontrou){
                            System.out.println("ID inválido. Digite novamente...");
                            continue;
                        }
                        break;
                    }
                    break;
                case 4:
                    while(true){
                        System.out.println(gerenciadorTarefas.listarTarefas());
                        int idDeletar;
                        while(true){
                            System.out.print("Digite o id da tarefa que deseja deletar: ");
                            try {
                                idDeletar = Integer.parseInt(sc.nextLine());
                            }catch(NumberFormatException e){
                                System.out.println("ERRO: Digite um número válido!");
                                continue;
                            }
                            break;
                        }

                        try{
                            boolean deletou = gerenciadorTarefas.deletarTarefa(idDeletar);

                            if(deletou){
                                System.out.println("TAREFA DELETADA!");
                                System.out.println();
                            }else{
                                System.out.println("ERRO: Digite um id válido!");
                                System.out.println();
                                continue;
                            }
                        }catch(NumberFormatException e){
                            System.out.println(e.getMessage());
                        }
                    break;
                    }
                    break;
                case 5:
                    gerenciadorTarefas.sair();
                    break;
                default:
                    System.out.println("Escolha uma opção válida");
                    break;
            }
        }
    }
}

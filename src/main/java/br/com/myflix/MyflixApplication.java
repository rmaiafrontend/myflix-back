package br.com.myflix;

import br.com.myflix.entidades.Filme;
import br.com.myflix.repositorios.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MyflixApplication implements CommandLineRunner {
    @Autowired
   private  FilmeRepository filmeRepository;


    @Override
    public void run(String... args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        while(true) {
            System.out.println("Bem vindo ao MyFlix! Salve e avalie os filmes que você assistiu!");
            System.out.println("1. Adicionar Filme");
            System.out.println("2. Listar Filmes");
            System.out.println("3. Editar Filme");
            System.out.println("4. Excluir Filme");
            System.out.println("5. Sair");
            System.out.println("Digite a opção desejada:");
            int opcao = Integer.parseInt(teclado.nextLine());

            if(opcao == 1){
                System.out.println("Digite o nome: ");
                String nome = teclado.nextLine();
                System.out.println("Avalie o fime com uma nota de 0 à 5: ");
                int avaliacao = Integer.parseInt(teclado.nextLine());
                System.out.print("Digite um comentário: ");
                String comentario = teclado.nextLine();

                Filme filme = new Filme();
                filme.setNome(nome);
                filme.setAvaliacao(avaliacao);
                filme.setComentario(comentario);
                filmeRepository.save(filme);
            }

            if(opcao == 2){
                for(Filme f : filmeRepository.findAll()) {
                    System.out.println(f.getNome() + ", " + f.getAvaliacao() + ", " + f.getComentario());
                }

            }

            if(opcao == 3) {
                System.out.println("Digite o nome do filme que você deseja editar:");
                String nomeFilme = teclado.nextLine();

                boolean filmeEncontrado = false;
                for (Filme f : filmeRepository.findAll()) {
                    if (f.getNome().equals(nomeFilme)){
                        filmeEncontrado = true;
                        System.out.println("Digite a opção desejada:");
                        System.out.println("1. Editar nome do filme");
                        System.out.println("2. Editar avaliação do filme");
                        System.out.println("3. Editar comentário do filme");
                        int select = Integer.parseInt(teclado.nextLine());

                        if(select == 1){
                            System.out.println("Digite o novo nome do filme:");
                            String novoNome = teclado.nextLine();
                            f.setNome(novoNome);
                            filmeRepository.save(f);
                            System.out.println("Nome do filme atualizado com sucesso!");
                            break;
                        }
                        if(select == 2) {
                            System.out.println("Digite a nova avaliação do filme:");
                            int novaAvaliacao = Integer.parseInt(teclado.nextLine());
                            f.setAvaliacao(novaAvaliacao);
                            filmeRepository.save(f);
                            System.out.println("Avaliação do filme atualizada com sucesso!");
                            break;
                        }
                        if(select == 3) {
                            System.out.println("Digite o novo comentário do filme:");
                            String novoComentario = teclado.nextLine();
                            f.setComentario(novoComentario);
                            filmeRepository.save(f);
                            System.out.println("Comentário do filme atualizado com sucesso!");
                            break;
                        }
                        else {
                            System.out.println("Opção inválida");
                            break;
                        }

                    }
                }

                if (!filmeEncontrado) {
                    System.out.println("Filme não encontrado.");
                }
            }

            if(opcao == 4) {
                System.out.println("Digite o nome do filme a ser excluído:");
                String nomeFilme = teclado.nextLine();

                boolean filmeEncontrado = false;
                for (Filme f : filmeRepository.findAll()) {
                    if (f.getNome().equalsIgnoreCase(nomeFilme)) {
                        var id = f.getId();
                        filmeRepository.deleteById(id);
                        System.out.println("Filme excluído com sucesso!");
                        filmeEncontrado = true;
                        break;
                    }
                }

                if (!filmeEncontrado) {
                    System.out.println("Filme não encontrado.");
                }


            }


            if(opcao == 5){
                break;
            }

        }


    }

    public static void main(String[] args) {
        SpringApplication.run(MyflixApplication.class, args);
    }
}

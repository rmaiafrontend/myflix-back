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


            if(opcao == 5){
                break;
            }

        }


    }

    public static void main(String[] args) {
        SpringApplication.run(MyflixApplication.class, args);
    }
}

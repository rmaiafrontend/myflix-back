package br.com.myflix.repositorios;

import br.com.myflix.entidades.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}

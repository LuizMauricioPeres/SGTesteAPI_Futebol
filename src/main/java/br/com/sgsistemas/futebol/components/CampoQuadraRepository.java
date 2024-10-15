package br.com.sgsistemas.futebol.components;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sgsistemas.futebol.components.CampoQuadra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoQuadraRepository extends JpaRepository<CampoQuadra, Long> {
    @Query("FROM CampoQuadra c " +
            "WHERE LOWER(c.nome) = :nome " +
            "AND c.tipo = :tipo " +
            "AND c.capacidade >= :capacidade"
    )
    Page<CampoQuadra> search(
            @Param("nome") String nome,
            @Param("tipo") Tipo tipo,
            @Param("capacidade") int capacidade,
            Pageable pageable);

}

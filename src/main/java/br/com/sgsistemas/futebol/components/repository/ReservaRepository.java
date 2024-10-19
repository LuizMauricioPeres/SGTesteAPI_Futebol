package br.com.sgsistemas.futebol.components.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sgsistemas.futebol.components.models.Reserva;

import java.time.LocalDate;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {


    @Query("FROM Reserva r " +
            "WHERE LOWER(r.cliente) like :cliente " )
    Page<Reserva> search(
            @Param("cliente") String cliente,
            Pageable pageable);

//    @Query(value = "SELECT SUM(horasAlugadas) FROM Reserva r where r.dataDaReserva = :date", nativeQuery = true)
    @Query(value = "SELECT SUM(horasAlugadas) FROM Reserva r", nativeQuery = true)
    double tempoReserva(@Param("data") LocalDate date,
                        @Param("campo") Long idCampo );
}

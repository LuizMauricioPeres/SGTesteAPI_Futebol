package br.com.sgsistemas.futebol.components.services;

import br.com.sgsistemas.futebol.components.models.Reserva;
import br.com.sgsistemas.futebol.components.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    @Transactional
    public Reserva updateReserva(Reserva reserva) {

        double tempoReserva = this.getTempoReserva(reserva.getDataDaReserva(), reserva.getCampoQuadra().getId());
        tempoReserva += reserva.getHorasAlugadas();
        if(!reserva.getId().equals(0L)) {
            throw new IllegalArgumentException("ID gerado automaticamente");
        }else if(reserva.getDataDaReserva().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Para reserrva a data tem que ser futura!");
        } else if (tempoReserva>24.0) {
            throw new IllegalArgumentException("Sem tempo disponivel para essa reserva!");
        }

        return reservaRepository.save(reserva);
    }

    public Reserva findById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public double getTempoReserva(LocalDate date, Long id) {
        double tempoReserva = 0;
        tempoReserva =  reservaRepository.tempoReserva(date, id );
        return tempoReserva;
    }

    public Page<Reserva> search(
            String cliente,
            int page,
            int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC);

        return reservaRepository.search(
                cliente.toLowerCase(),
                pageRequest);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }
    @Transactional
    public void delete(Reserva reserva) {
        if(reserva.getDataDaReserva().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Impossível remover reserva com data passada!");
        }
        this.reservaRepository.delete(reserva);
    }
}
package br.com.sgsistemas.futebol.components;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        this.reservaRepository.delete(reserva);
    }
}
package br.com.sgsistemas.futebol.components.controller;

import br.com.sgsistemas.futebol.components.models.Reserva;
import br.com.sgsistemas.futebol.components.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaAPIController {
    private final ReservaService reservaService;

    @Autowired
    public ReservaAPIController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    private ResponseEntity<Reserva> post(@RequestBody Reserva reserva) {
        Reserva reservaSalva = this.reservaService.updateReserva(reserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(reserva.getId()).toUri();
        return  ResponseEntity.created(uri).body(reservaSalva);
    }
    @PutMapping
    private ResponseEntity<Reserva> post(@PathVariable Long id, @RequestBody Reserva reserva) {

        Reserva reservaSalva = this.reservaService.updateReserva(reserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(reserva.getId()).toUri();
        return  ResponseEntity.created(uri).body(reservaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAll() {
        List<Reserva> reservas = this.reservaService.findAll();
        if (reservas.equals(Collections.emptyList())){
            ResponseEntity.ok("{}");
        }
        return ResponseEntity.ok(reservas);

    }

    @GetMapping("/search")
    public Page<Reserva> search(
            @RequestParam("cliente") String cliente,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
        return reservaService.search(cliente, page, size);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> get(@PathVariable Long id) {
        Reserva reserva = this.reservaService.findById(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> delete(@PathVariable Long id) {
        Reserva reserva = this.reservaService.findById(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        this.reservaService.delete(reserva);
        return ResponseEntity.ok(reserva);
    }

}

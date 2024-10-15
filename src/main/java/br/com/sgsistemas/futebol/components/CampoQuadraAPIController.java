package br.com.sgsistemas.futebol.components;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/campo_quadra")
public class CampoQuadraAPIController {
    private final CampoQuadraService campoQuadraService;

    @Autowired
    public CampoQuadraAPIController(CampoQuadraService campoQuadraService) {
        this.campoQuadraService = campoQuadraService;
    }

    @Operation(summary = "Adicionar um novo campo/quadra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campo/Quadra criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID gerado automaticamente")
    })
    @PostMapping
    private ResponseEntity<CampoQuadra> post(@RequestBody CampoQuadra campoQuadra) {
        CampoQuadra campo;
        if(campoQuadra.getId().equals(0L)) {
            campo = this.campoQuadraService.addCampoQuadra(campoQuadra);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(campo.getId()).toUri();
            return  ResponseEntity.created(uri).body(campo);
        }else {
            throw new IllegalArgumentException("ID gerado automaticamente");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampoQuadra> put(@PathVariable Long id, @RequestBody CampoQuadra campoQuadra) {
        CampoQuadra campo;
        if(id.equals(0L)) {
            throw new IllegalArgumentException("ID é obrigatório");
        }
        if (campoQuadra.getId().equals(0L)) {
            campoQuadra.setId(id);
        }
        campo = this.campoQuadraService.updateCampoQuadra(campoQuadra);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(campo.getId()).toUri();

        return  ResponseEntity.created(uri).body(campo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(id.equals(0L)) {
            throw new IllegalArgumentException("ID é obrigatório");
        }
        this.campoQuadraService.deleteCampoQuadra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<CampoQuadra> search(
            @RequestParam("nome") String nome,
            @RequestParam("tipo") Tipo tipo,
            @RequestParam("capacidadeMinima") int capacidade,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
        return campoQuadraService.search(nome, tipo, capacidade , page, size);

    }

    @GetMapping
    public ResponseEntity<List<CampoQuadra>> getAll() {
        List<CampoQuadra> quadras = this.campoQuadraService.getAllCampoQuadras();
        if (quadras.equals(Collections.emptyList())){
            ResponseEntity.ok("{}");
        }
        return ResponseEntity.ok(quadras);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CampoQuadra> get(@PathVariable Long id) {
        CampoQuadra campo =this.campoQuadraService.getCampoQuadraById(id);
        if (campo == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(campo);
    }

}

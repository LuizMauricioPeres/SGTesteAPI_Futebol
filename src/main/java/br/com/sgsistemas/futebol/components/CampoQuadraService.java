package br.com.sgsistemas.futebol.components;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CampoQuadraService {
    private final CampoQuadraRepository campoQuadraRepository;

    @Autowired
    public CampoQuadraService(CampoQuadraRepository campoQuadraRepository) {
        this.campoQuadraRepository = campoQuadraRepository;
    }

    @Transactional
    public CampoQuadra addCampoQuadra(CampoQuadra campoQuadra) {
        return campoQuadraRepository.save(campoQuadra);
    }

    @Transactional
    public CampoQuadra updateCampoQuadra(CampoQuadra campoQuadra) {
        return campoQuadraRepository.save(campoQuadra);
    }

    @Transactional
    public void deleteCampoQuadra(Long id) {
        CampoQuadra campo = this.getCampoQuadraById(id);
        if (campo.equals(null)) {
            throw new RuntimeException("Campo/Quadra não cadastrado");
        }
        this.campoQuadraRepository.delete(campo);
    }

    public CampoQuadra getCampoQuadraById(Long id) {
        return this.campoQuadraRepository.findById(id).orElse(null);
    }

    public List<CampoQuadra> getAllCampoQuadras() {
        return  this.campoQuadraRepository.findAll();
    }

    public Page<CampoQuadra> search(
            String nome,
            Tipo tipo,
            int capacidade,
            int page,
            int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return campoQuadraRepository.search(
                nome.toLowerCase(),
                tipo,
                capacidade,
                pageRequest);
    }

}

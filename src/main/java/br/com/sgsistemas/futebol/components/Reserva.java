package br.com.sgsistemas.futebol.components;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    LocalDate dataDaReserva;
    double horasAlugadas;
    String cliente;
    Situacao situacao;
    BigDecimal valorTotal;
    @ManyToOne
    CampoQuadra campoQuadra;

    public Reserva(Long id, LocalDate dataDaReserva, double horasAlugadas, String cliente, Situacao situacao, BigDecimal valorTotal, CampoQuadra campoQuadra) {
        this.id = id;
        this.dataDaReserva = dataDaReserva;
        this.horasAlugadas = horasAlugadas;
        this.cliente = cliente;
        this.situacao = situacao;
        this.valorTotal = valorTotal;
        this.campoQuadra = campoQuadra;
    }

    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDaReserva() {
        return dataDaReserva;
    }

    public void setDataDaReserva(LocalDate dataDaReserva) {
        this.dataDaReserva = dataDaReserva;
    }

    public double getHorasAlugadas() {
        return horasAlugadas;
    }

    public void setHorasAlugadas(double horasAlugadas) {
        this.horasAlugadas = horasAlugadas;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public CampoQuadra getCampoQuadra() {
        return campoQuadra;
    }

    public void setCampoQuadra(CampoQuadra campoQuadra) {
        this.campoQuadra = campoQuadra;
    }
}

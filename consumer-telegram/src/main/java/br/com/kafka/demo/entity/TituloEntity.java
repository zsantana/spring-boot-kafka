package br.com.kafka.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TituloEntity {

    @Id
    // @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private UUID chaveControle;
    private String nossoNumero;
    private LocalDate dataRegistro;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getChaveControle() {
        return chaveControle;
    }
    public void setChaveControle(UUID chaveControle) {
        this.chaveControle = chaveControle;
    }
    public String getNossoNumero() {
        return nossoNumero;
    }
    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }
    public LocalDate getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }
    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

}


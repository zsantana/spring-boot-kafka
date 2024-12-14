package br.com.kafka.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PayloadDTO(
    
    @NotNull (message = "chaveRetorno é obrigatório") 
    UUID chaveControle,

    @NotBlank (message = "nossoNumero é obrigatório") 
    String nossoNumero, 

    @NotNull (message = "dataRegistro é obrigatório") 
    LocalDate dataRegistro, 

    @NotNull (message = "dataPagamento é obrigatório") 
    LocalDate dataPagamento, 

    @NotNull (message = "valorPagamento é obrigatório") 
    BigDecimal valorPagamento
    
    ) {}
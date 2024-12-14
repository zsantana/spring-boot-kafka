package br.com.kafka.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResponseDTO(
    UUID chaveControle,
    String nossoNumero, 
    LocalDate dataRegistro, 
    LocalDate dataPagamento, 
    BigDecimal valorPagamento
    ) { }
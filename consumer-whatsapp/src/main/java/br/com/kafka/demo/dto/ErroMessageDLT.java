package br.com.kafka.demo.dto;

public record ErroMessageDLT(String rootCause, String message, String stackTrace, String payload) {}

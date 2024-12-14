package br.com.kafka.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kafka.demo.entity.TituloEntity;

@Repository
public interface TituloRepository extends JpaRepository<TituloEntity, UUID> {
}


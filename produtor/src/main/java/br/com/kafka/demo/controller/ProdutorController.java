package br.com.kafka.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kafka.demo.dto.ResponseDTO;
import br.com.kafka.demo.kafka.ProdutorKafka;
import br.com.kafka.demo.dto.PayloadDTO;
import br.com.kafka.demo.dto.RequestDTO;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/kafka")
public class ProdutorController {

    @Autowired
    ProdutorKafka producer;
    
    @PostMapping("producer/{topic}")
    public ResponseEntity<ResponseDTO> execute(@PathVariable String topic, @RequestBody RequestDTO requestDTO) {

        var chaveControle = UUID.randomUUID();

        var responseDTO = new ResponseDTO(
                            chaveControle,
                            requestDTO.nossoNumero(),
                            requestDTO.dataRegistro(),
                            requestDTO.dataPagamento(),
                            requestDTO.valorPagamento()
                            );

        var payloadDTO = new PayloadDTO(
                            chaveControle,
                            requestDTO.nossoNumero(),
                            requestDTO.dataRegistro(),
                            requestDTO.dataPagamento(),
                            requestDTO.valorPagamento()
                            );                            
        
        producer.execute(topic, payloadDTO);
        
        return ResponseEntity.ok(responseDTO);

    }

}

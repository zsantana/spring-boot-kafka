package br.com.kafka.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import br.com.kafka.demo.dto.PayloadDTO;
import br.com.kafka.demo.entity.TituloEntity;
import br.com.kafka.demo.repository.TituloRepository;
import jakarta.transaction.Transactional;

@Service
@EnableAsync
public class TituloService {

    private static final Logger logger = LoggerFactory.getLogger(TituloService.class);

    private final TituloRepository repository;
    private final NotificacaoService notificacaoService;

    public TituloService(final TituloRepository repository,
                         final NotificacaoService notificacaoService) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
    }

    @Transactional
    @Async("")
    public void create(PayloadDTO payloadDTO){

        var dao = new TituloEntity();
        dao.setId(payloadDTO.chaveControle());
        dao.setChaveControle(payloadDTO.chaveControle());
        dao.setNossoNumero(payloadDTO.nossoNumero());
        dao.setDataRegistro(payloadDTO.dataRegistro());
        dao.setDataPagamento(payloadDTO.dataPagamento());
        dao.setValorPagamento(payloadDTO.valorPagamento());

        repository.save(dao);
        logger.info("### Registro no banco de dados com sucesso");

        notifyAsync(payloadDTO);

    }

    private void notifyAsync(PayloadDTO payloadDTO) {
        notificacaoService.publicarNotificacoes(payloadDTO);
    }

    
}

package api.erp.solarerp.domain.service;

import api.erp.solarerp.data.repository.ClienteRepository;
import api.erp.solarerp.domain.model.cliente.ClientePF;
import api.erp.solarerp.web.dto.ClientePfDTO;
import api.erp.solarerp.domain.mapper.ClienteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Slf4j
public class ClienteServiceImpl {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Long cadastrarClientePF(ClientePfDTO dto) {
        log.info("Cadastrando cliente PF: {}", dto.getNomeCompleto());

        ClientePF entity = clienteMapper.toEntity(dto);
        ClientePF savedEntity = clienteRepository.save(entity);

        log.info("Cliente PF cadastrado com sucesso - Nome =  {}, ID =  {}", savedEntity.getNomeCompleto(), savedEntity.getClienteID());

        return savedEntity.getClienteID();
    }
}

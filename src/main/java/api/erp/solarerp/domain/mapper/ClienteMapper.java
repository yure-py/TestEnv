package api.erp.solarerp.domain.mapper;

import api.erp.solarerp.domain.model.cliente.ClientePF;
import api.erp.solarerp.domain.model.objectvalues.Phone;
import api.erp.solarerp.web.dto.ClientePfDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(source = "telefonePrincipal", target = "telefonePrincipal", qualifiedByName = "phoneToString")
    @Mapping(source = "telefoneSecundario", target = "telefoneSecundario", qualifiedByName = "phoneToString")
    ClientePfDTO toDto(ClientePF clientePF);
    
    @Mapping(source = "telefonePrincipal", target = "telefonePrincipal", qualifiedByName = "stringToPhone")
    @Mapping(source = "telefoneSecundario", target = "telefoneSecundario", qualifiedByName = "stringToPhone")
    ClientePF toEntity(ClientePfDTO clientePfDTO);
    
    @Named("phoneToString")
    default String phoneToString(Phone phone) {
        return phone != null ? phone.getNumber() : null;
    }
    
    @Named("stringToPhone")
    default Phone stringToPhone(String phoneNumber) {
        return phoneNumber != null ? new Phone(phoneNumber) : null;
    }
}
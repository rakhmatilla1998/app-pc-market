package pdp.uz.helper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import pdp.uz.domain.Address;
import pdp.uz.payload.AddressDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    Address toAddress(AddressDto dto);

    AddressDto toAddressDto(Address address);

//    List<Address> toAddress(List<AddressDto> addressDto);
}

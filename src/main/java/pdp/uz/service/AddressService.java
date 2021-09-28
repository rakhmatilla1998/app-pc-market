package pdp.uz.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pdp.uz.domain.Address;
import pdp.uz.payload.AddressDto;
import pdp.uz.payload.ApiResponse;

import java.util.List;

public interface AddressService {

    ResponseEntity<ApiResponse<Address>> add(AddressDto dto);

    ResponseEntity<ApiResponse<List<Address>>> getAll(Pageable pageable);

    ResponseEntity<ApiResponse<Address>> get(Long id);

    ResponseEntity<ApiResponse<Address>> delete(Long id);

    ResponseEntity<ApiResponse<Address>> edit(Long id, AddressDto dto);
}

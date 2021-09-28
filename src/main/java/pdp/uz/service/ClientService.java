package pdp.uz.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pdp.uz.domain.Client;
import pdp.uz.payload.ApiResponse;
import pdp.uz.payload.ClientDto;

import java.util.List;

public interface ClientService {
    ResponseEntity<ApiResponse<Client>> add(ClientDto dto);

    ResponseEntity<ApiResponse<List<Client>>> getAll(Pageable pageable);

    ResponseEntity<ApiResponse<Client>> get(Long id);

    ResponseEntity<ApiResponse<Client>> delete(Long id);

    ResponseEntity<ApiResponse<Client>> edit(Long id, ClientDto dto);
}

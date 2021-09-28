package pdp.uz.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.domain.Client;
import pdp.uz.payload.ApiResponse;
import pdp.uz.payload.ClientDto;
import pdp.uz.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public ResponseEntity<ApiResponse<Client>> add(ClientDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Client>>> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Client>> edit(Long id, ClientDto dto) {
        return null;
    }
}

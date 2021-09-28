package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.domain.Client;
import pdp.uz.payload.ApiResponse;
import pdp.uz.payload.ClientDto;
import pdp.uz.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasAuthority('ADD')")
    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<Client>> add(@RequestBody ClientDto dto) {
        return clientService.add(dto);
    }

    @PreAuthorize("hasAuthority('GET_ALL')")
    @GetMapping(value = "/getAll")
    public ResponseEntity<ApiResponse<List<Client>>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientService.getAll(pageable);
    }

    @PreAuthorize("hasAuthority('GET')")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ApiResponse<Client>> get(@PathVariable Long id) {
        return clientService.get(id);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponse<Client>> delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

    @PreAuthorize("hasAuthority('EDIT')")
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<ApiResponse<Client>> edit(@PathVariable Long id, @RequestBody ClientDto dto) {
        return clientService.edit(id, dto);
    }
}

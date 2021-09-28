package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.domain.Address;
import pdp.uz.payload.AddressDto;
import pdp.uz.payload.ApiResponse;
import pdp.uz.service.AddressService;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PreAuthorize("hasAuthority('ADD')")
    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<Address>> add(@RequestBody AddressDto dto) {
        return addressService.add(dto);
    }

    @PreAuthorize("hasAuthority('GET_ALL')")
    @GetMapping(value = "/getAll")
    public ResponseEntity<ApiResponse<List<Address>>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return addressService.getAll(pageable);
    }

    @PreAuthorize("hasAuthority('GET')")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ApiResponse<Address>> get(@PathVariable Long id) {
        return addressService.get(id);
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponse<Address>> delete(@PathVariable Long id) {
        return addressService.delete(id);
    }

    @PreAuthorize("hasAuthority('EDIT')")
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<ApiResponse<Address>> edit(@PathVariable Long id, @RequestBody AddressDto dto) {
        return addressService.edit(id, dto);
    }
}

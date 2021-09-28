package pdp.uz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.domain.Address;
import pdp.uz.helper.MapstructMapper;
import pdp.uz.payload.AddressDto;
import pdp.uz.payload.ApiResponse;
import pdp.uz.repository.AddressRepo;
import pdp.uz.service.AddressService;

import java.util.List;
import java.util.Optional;

import static pdp.uz.payload.ApiResponse.response;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;
    private final MapstructMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepo addressRepo, MapstructMapper mapper) {
        this.addressRepo = addressRepo;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ApiResponse<Address>> add(AddressDto dto) {
        Address address = new Address();
        address.setDistrict(dto.getDistrict());
        address.setRegion(dto.getRegion());
        address.setStreet(dto.getStreet());

        return response(addressRepo.save(address), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<List<Address>>> getAll(Pageable pageable) {
        Page<Address> addressPage = addressRepo.findAll(pageable);

        if (addressPage.isEmpty()) {
            return response(HttpStatus.NOT_FOUND);
        }

        return response(addressPage.getContent(), addressRepo.count());
    }

    @Override
    public ResponseEntity<ApiResponse<Address>> get(Long id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        return optionalAddress.map(address -> response(address, HttpStatus.OK)).orElseGet(() -> response(HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<ApiResponse<Address>> delete(Long id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (!optionalAddress.isPresent()) {
            return response(HttpStatus.NOT_FOUND);
        }
        Address deletedAddress = optionalAddress.get();
        addressRepo.delete(deletedAddress);

        return response(deletedAddress);
    }

    @Override
    public ResponseEntity<ApiResponse<Address>> edit(Long id, AddressDto dto) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (!optionalAddress.isPresent()) {
            return response(HttpStatus.NOT_FOUND);
        }

        Address address = optionalAddress.get();
        address.setStreet(dto.getStreet());
        address.setDistrict(dto.getDistrict());
        address.setRegion(dto.getRegion());

        return response(addressRepo.save(address));
    }
}

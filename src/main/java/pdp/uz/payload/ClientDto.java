package pdp.uz.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDto {

    private String fullName;

    private String email;

    private String phoneNumber;

    private Long addressId;

}

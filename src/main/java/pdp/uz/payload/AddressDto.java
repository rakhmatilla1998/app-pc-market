package pdp.uz.payload;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AddressDto implements Serializable {

    private String street;

    private String region;

    private String district;

}

package pdp.uz.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.uz.domain.Address;

@Projection(types = Address.class)
public interface CustomAddress {

    Long getId();

    String getRegion();

    String getDistrict();

    String getStreet();
}

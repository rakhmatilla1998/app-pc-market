package pdp.uz.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.uz.domain.Client;

@Projection(types = Client.class)
public interface CustomClient {

    Long getId();

    String getFullName();

    String getEmail();

    String getPhoneNumber();

    Object getAddress();
}

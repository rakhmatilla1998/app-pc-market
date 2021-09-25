package pdp.uz.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.uz.domain.Order;

import java.util.List;

@Projection(types = Order.class)
public interface CustomOrder {

    Long getId();

    Object getClient();

    List<Long> getProducts();
}

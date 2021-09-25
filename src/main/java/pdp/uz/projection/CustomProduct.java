package pdp.uz.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.uz.domain.Product;

@Projection(types = Product.class)
public interface CustomProduct {

    Long getId();

    String getName();

    String getModel();

    String getMark();

    String getDescription();

    boolean getActive();

    Object getCategory();

    Object getAttachment();
}

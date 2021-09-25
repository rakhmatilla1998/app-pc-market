package pdp.uz.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.uz.domain.Category;

@Projection(types = Category.class)
public interface CustomCategory {

    Long getId();

    String getName();

    boolean getActive();
}

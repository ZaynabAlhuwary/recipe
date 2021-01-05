
package recipe.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Base {

    @org.springframework.data.annotation.Id
    private String id;
}

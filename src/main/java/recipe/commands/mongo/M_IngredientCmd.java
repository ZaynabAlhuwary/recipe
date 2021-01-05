package recipe.commands.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class M_IngredientCmd {

    private String id;
    private String description;
    private BigDecimal amount;
    private M_UOMCmd unitOfMeasure;


}

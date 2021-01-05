package recipe.converters.mongo;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.mongo.M_CategoryCmd;
import recipe.domain.mongo.M_Category;

@Component
public class M_CategoryToCategoryCmd implements Converter<M_Category, M_CategoryCmd> {

    @Synchronized
    @Nullable
    @Override
    public M_CategoryCmd convert(M_Category m_category) {

        if (null == m_category) {
            return null;
        }

        final M_CategoryCmd categoryCmd = new M_CategoryCmd();
        categoryCmd.setId(m_category.getId());
        categoryCmd.setDescription(m_category.getDescription());
        return categoryCmd;
    }
}

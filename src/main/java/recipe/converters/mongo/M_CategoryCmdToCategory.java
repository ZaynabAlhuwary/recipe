package recipe.converters.mongo;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.mongo.M_CategoryCmd;
import recipe.domain.mongo.M_Category;

@Component
public class M_CategoryCmdToCategory implements Converter<M_CategoryCmd, M_Category> {

    @Synchronized
    @Nullable
    @Override
    public M_Category convert(M_CategoryCmd mCategoryCmd) {

        if (null == mCategoryCmd) {
            return null;
        }

        final M_Category category = new M_Category(mCategoryCmd.getDescription());
        category.setId(mCategoryCmd.getId());
        return category;
    }
}

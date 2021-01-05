package recipe.converters.mongo;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.mongo.M_UOMCmd;
import recipe.domain.mongo.M_UOM;

@Component
public class M_UOMCmdToUOM implements Converter<M_UOMCmd, M_UOM> {

    @Synchronized
    @Nullable
    @Override
    public M_UOM convert(M_UOMCmd mUomCmd) {

        if (null == mUomCmd) {
            return null;
        }

        final M_UOM mUom = new M_UOM(mUomCmd.getUom());
        mUom.setId(mUomCmd.getId());
        return mUom;
    }
}

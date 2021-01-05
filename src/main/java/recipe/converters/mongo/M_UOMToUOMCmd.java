package recipe.converters.mongo;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.mongo.M_UOMCmd;
import recipe.domain.mongo.M_UOM;

@Component
public class M_UOMToUOMCmd implements Converter<M_UOM, M_UOMCmd> {


    @Synchronized
    @Nullable
    @Override
    public M_UOMCmd convert(M_UOM m_uom) {

        if (null == m_uom) {
            return null;
        }

        M_UOMCmd mUomCmd = new M_UOMCmd();
        mUomCmd.setId(m_uom.getId());
        mUomCmd.setUom(m_uom.getUom());
        return mUomCmd;
    }
}

package recipe.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import recipe.domain.mongo.M_UOM;

@Repository
@Profile("mongoDb")
public interface UnitOfMeasureMongoRepo extends MongoRepository<M_UOM, String> {

    M_UOM findByUom(String uom);
}

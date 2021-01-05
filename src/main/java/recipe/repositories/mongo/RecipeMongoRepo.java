package recipe.repositories.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import recipe.domain.mongo.M_Recipe;

@Repository
@Profile("mongoDb")
public interface RecipeMongoRepo extends MongoRepository<M_Recipe, String> {
}

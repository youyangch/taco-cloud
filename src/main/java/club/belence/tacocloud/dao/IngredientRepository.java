package club.belence.tacocloud.dao;

import club.belence.tacocloud.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
   Iterable<Ingredient> findAll();

   Optional<Ingredient> findById(String id);

   Ingredient save(Ingredient ingredient);
}

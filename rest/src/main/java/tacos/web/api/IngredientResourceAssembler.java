package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.domain.Ingredient;

public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(Ingredientcontroller.class, IngredientResource.class);
    }

    @Override
    protected IngredientResource instantiateModel(Ingredient entity) {
        return new IngredientResource(entity);
    }

    @Override
    public IngredientResource toModel(Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }
}

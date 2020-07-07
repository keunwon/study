package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import tacos.domain.Taco;

import java.time.LocalDateTime;

public class TacoResource extends RepresentationModel<TacoResource> {
    private static final IngredientResourceAssembler ingredientAssembler
            = new IngredientResourceAssembler();

    @Getter
    private final String name;

    @Getter
    private final LocalDateTime createAt;

    @Getter
    private final CollectionModel<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createAt = taco.getCreateAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}

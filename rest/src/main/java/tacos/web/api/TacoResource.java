package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

import java.time.LocalDateTime;
import java.util.List;

public class TacoResource extends RepresentationModel<TacoResource> {

    @Getter
    private final String name;

    @Getter
    private final LocalDateTime createAt;

    @Getter
    private final List<Ingredient> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createAt = taco.getCreateAt();
        this.ingredients = taco.getIngredients();
    }
}

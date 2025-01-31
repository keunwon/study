package tacos.restclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.domain.Ingredient;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TacoCloudClient {
    private final RestTemplate restTemplate;
    private final Traverson traverson;

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8080/ingredient/{id}", Ingredient.class, ingredientId);
    }

    public List<Ingredient> getAllIngredient() {
        return restTemplate.exchange("http://localhost:8080/ingredients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>(){}).getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients{id}", ingredient, ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

   public Ingredient addIngredient(Ingredient ingredient) {
        String ingredietsUrl = traverson.follow("ingredients")
                .follow("ingredients")
                .asLink()
                .getHref();
        return restTemplate.postForObject(ingredietsUrl, ingredient, Ingredient.class);
   }
}

package tacos.domian.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author liutiantian
 * @create 2020-04-06 17:42
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

package ru.bell.manabu.domain.helpObject;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter @Setter
public abstract class Deck{
    private String _id;

    @NotEmpty
    @Field("name")
    private String name;

    @NotEmpty
    private List<Level> levels;

    public void addLevel(Level level){
        if(levels == null) levels = new ArrayList<>();
        levels.add(level);
    }
}

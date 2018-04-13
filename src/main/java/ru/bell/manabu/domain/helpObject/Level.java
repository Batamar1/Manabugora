package ru.bell.manabu.domain.helpObject;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter @Setter
public class Level {
    private String id;

    private String name;
    private List<Card> cards;
    private boolean available;

    public void addCard(Card card){
        if(cards == null) cards = new ArrayList<>();
        cards.add(card);
    }
}

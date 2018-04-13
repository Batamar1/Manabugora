package ru.bell.manabu.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.bell.manabu.domain.helpObject.Deck;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "USER_DECK")
public class UserDeck extends Deck {
    private String token;
}

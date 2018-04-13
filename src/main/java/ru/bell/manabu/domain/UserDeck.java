package ru.bell.manabu.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.bell.manabu.domain.helpObject.Deck;

@Document(collection = "USER_DECK")
public class UserDeck extends Deck {
}

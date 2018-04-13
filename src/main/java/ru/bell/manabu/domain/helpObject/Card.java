package ru.bell.manabu.domain.helpObject;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Setter
public class Card {
    private String id;
    private String description;
    private String name;
}

package todo.todolistjsp.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Entity {
    @Getter
    @Setter
    private UUID id;

    public Entity() {
        id = UUID.randomUUID();
    }
}

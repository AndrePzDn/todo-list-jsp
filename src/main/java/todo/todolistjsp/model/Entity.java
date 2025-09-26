package todo.todolistjsp.model;

import java.util.UUID;

import lombok.Getter;

public class Entity {
    @Getter
    private UUID id;

    public Entity(UUID id) {
        this.id = id;
    }
}

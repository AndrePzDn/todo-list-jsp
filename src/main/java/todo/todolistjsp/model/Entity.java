package todo.todolistjsp.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Entity {
    @Getter
    @Setter
    private UUID id;

    public Entity(UUID id) {
        this.id = id;
    }
}

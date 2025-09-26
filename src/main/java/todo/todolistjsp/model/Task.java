package todo.todolistjsp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
public class Task extends Entity {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Status status;

    @Getter
    @Setter
    private LocalDate targetDate;

    @Getter
    @Setter
    private LocalDate startDate;

    public Task(UUID id, String title, String description, Status status, LocalDate targetDate, LocalDate startDate) {
        super(id);
        this.title = title;
        this.description = description;
        this.status = status;
        this.targetDate = targetDate;
        this.startDate = startDate;
    }
}

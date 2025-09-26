package todo.todolistjsp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Task extends Entity {
    private String title;
    private String description;
    private Status status;
    private LocalDate targetDate;
    private LocalDate startDate;

    public Task(String title, String description, Status status, LocalDate targetDate, LocalDate startDate) {
        super();
        this.title = title;
        this.description = description;
        this.status = status;
        this.targetDate = targetDate;
        this.startDate = startDate;
    }
}

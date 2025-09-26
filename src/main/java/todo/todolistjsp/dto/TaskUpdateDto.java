package todo.todolistjsp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import todo.todolistjsp.model.Status;

@Getter
@AllArgsConstructor
public class TaskUpdateDto extends UpdateDto {
    private String title;
    private String description;
    private Status status;
    private LocalDate targetDate;
    private LocalDate startDate;
}

package todo.todolistjsp.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import todo.todolistjsp.model.Status;
import todo.todolistjsp.validation.Validator;

@Getter
@AllArgsConstructor
public class TaskUpdateDto extends UpdateDto {
    private String title;
    private String description;
    private Status status;
    private LocalDate targetDate;
    private LocalDate startDate;

    @Override
    public List<String> validate() {
        Validator validator = new Validator.ValidatorBuilder()
                .isNotEmpty(this.getTitle(), "Title should not be empty")
                .isNotEmpty(this.getDescription(), "Description should not be empty")
                .isAfter(this.getTargetDate(), this.getStartDate(), "Target date should be after Start date")
                .validate();

        return validator.getViolations();
    }
}

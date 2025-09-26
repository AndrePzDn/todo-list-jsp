package todo.todolistjsp.mapper;

import todo.todolistjsp.dto.TaskCreateDto;
import todo.todolistjsp.dto.TaskUpdateDto;
import todo.todolistjsp.model.Task;

public class TaskMapper implements Mapper<TaskCreateDto, TaskUpdateDto, Task> {

    @Override
    public Task fromCreateDto(TaskCreateDto dto) {
        return new Task(dto.getTitle(), dto.getDescription(), dto.getStatus(), dto.getTargetDate(), dto.getStartDate());
    }

    @Override
    public void updateEntity(Task task, TaskUpdateDto dto) {
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setTargetDate(dto.getTargetDate());
        task.setStartDate(dto.getStartDate());
    }

}

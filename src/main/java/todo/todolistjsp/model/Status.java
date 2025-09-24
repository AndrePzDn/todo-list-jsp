package todo.todolistjsp.model;

public enum Status {
    PENDING,
    COMPLETE;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().toLowerCase().substring(1).toLowerCase();
    }
}

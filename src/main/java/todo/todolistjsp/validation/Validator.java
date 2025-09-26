package todo.todolistjsp.validation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Validator {
    private List<String> violations = new ArrayList<>();

    public Validator(List<String> violations) {
        this.violations = violations;
    }

    @Getter
    @Setter
    public static class ValidatorBuilder {
        private List<String> violations = new ArrayList<>();

        public ValidatorBuilder isNotNull(Object obj, String errorMessage) {
            if (obj == null) {
                violations.add(errorMessage);
            }

            return this;
        }

        public ValidatorBuilder isNotEmpty(String obj, String errorMessage) {
            this.isNotNull(obj, errorMessage);

            if (obj.isEmpty()) {
                this.violations.add(errorMessage);
            }

            return this;
        }

        public ValidatorBuilder isAfter(LocalDate after, LocalDate before, String errorMessage) {
            this.isNotNull(before, errorMessage);
            this.isNotNull(after, errorMessage);

            if (before.isAfter(after)) {
                this.violations.add(errorMessage);
            }

            return this;
        }

        public Validator validate() {
            return new Validator(this.violations);
        }
    }
}

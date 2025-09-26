package todo.todolistjsp.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpRequest {
    private final HTTPMethod method;
    private final String requestUri;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        HttpRequest that = (HttpRequest) obj;

        return method == that.method && Objects.equals(requestUri, that.getRequestUri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, requestUri);
    }
}

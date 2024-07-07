package org.learning.jmthd.multithreading.returningvalues;

import java.util.Objects;

public class TaskResult<S, R> {
    public final S taskId;
    public final R result;

    public TaskResult(S taskId, R result) {
        this.taskId = taskId;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResult<?, ?> that = (TaskResult<?, ?>) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, result);
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "taskId=" + taskId +
                ", result=" + result +
                '}';
    }
}

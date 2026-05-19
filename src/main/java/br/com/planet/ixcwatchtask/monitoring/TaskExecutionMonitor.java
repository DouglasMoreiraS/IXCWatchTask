package br.com.planet.ixcwatchtask.monitoring;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TaskExecutionMonitor {

    private final AtomicBoolean running = new AtomicBoolean(false);
    private volatile String lastExecutionId;
    private volatile String lastStatus = "NEVER_EXECUTED";
    private volatile LocalDateTime lastStartedAt;
    private volatile LocalDateTime lastFinishedAt;
    private volatile String lastError = "";
    private volatile Map<String, Object> lastSummary = Map.of();

    public boolean start(String executionId) {
        if (!running.compareAndSet(false, true)) {
            return false;
        }

        lastExecutionId = executionId;
        lastStatus = "RUNNING";
        lastStartedAt = LocalDateTime.now();
        lastFinishedAt = null;
        lastError = "";
        lastSummary = Map.of();
        return true;
    }

    public void finish(Map<String, Object> summary) {
        lastStatus = "FINISHED";
        lastFinishedAt = LocalDateTime.now();
        lastSummary = summary;
        running.set(false);
    }

    public void abort(String reason) {
        lastStatus = "ABORTED";
        lastError = reason;
        lastFinishedAt = LocalDateTime.now();
        running.set(false);
    }

    public void fail(RuntimeException ex) {
        lastStatus = "ERROR";
        lastError = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        lastFinishedAt = LocalDateTime.now();
        running.set(false);
    }

    public Map<String, Object> status() {
        //IA: endpoint pensado para Uptime Kuma e leitura operacional rapida.
        return Map.of(
                "application", "IXCWatchTask",
                "status", "UP",
                "taskRunning", running.get(),
                "lastExecutionId", valueOrEmpty(lastExecutionId),
                "lastExecutionStatus", lastStatus,
                "lastStartedAt", lastStartedAt == null ? "" : lastStartedAt,
                "lastFinishedAt", lastFinishedAt == null ? "" : lastFinishedAt,
                "lastError", valueOrEmpty(lastError),
                "lastSummary", lastSummary
        );
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}

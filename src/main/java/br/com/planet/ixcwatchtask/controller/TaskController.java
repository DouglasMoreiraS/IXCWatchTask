package br.com.planet.ixcwatchtask.controller;

import br.com.planet.ixcwatchtask.task.MainTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/tasks/ixc-watch")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final MainTask mainTask;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public TaskController(MainTask mainTask) {
        this.mainTask = mainTask;
    }

    @PostMapping("/run")
    public ResponseEntity<Map<String, Object>> runIxcWatchTask() {
        //IA: evita execucao manual concorrente com outra chamada manual ainda em andamento.
        if (!running.compareAndSet(false, true)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", "already_running",
                    "message", "Rotina IXC Watch ja esta em execucao",
                    "timestamp", LocalDateTime.now()
            ));
        }

        try {
            log.info("Execucao manual da rotina IXC Watch solicitada via controller");
            mainTask.mainTask();

            return ResponseEntity.accepted().body(Map.of(
                    "status", "finished",
                    "message", "Rotina IXC Watch executada manualmente",
                    "timestamp", LocalDateTime.now()
            ));
        } catch (RuntimeException ex) {
            log.error("Erro ao executar rotina IXC Watch manualmente: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Erro ao executar rotina IXC Watch manualmente",
                    "error", ex.getMessage() == null ? "" : ex.getMessage(),
                    "timestamp", LocalDateTime.now()
            ));
        } finally {
            running.set(false);
        }
    }
}

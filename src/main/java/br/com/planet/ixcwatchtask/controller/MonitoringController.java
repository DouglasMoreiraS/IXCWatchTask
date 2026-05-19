package br.com.planet.ixcwatchtask.controller;

import br.com.planet.ixcwatchtask.monitoring.TaskExecutionMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private final TaskExecutionMonitor monitor;

    public MonitoringController(TaskExecutionMonitor monitor) {
        this.monitor = monitor;
    }

    @GetMapping("/task-status")
    public Map<String, Object> taskStatus() {
        return monitor.status();
    }
}

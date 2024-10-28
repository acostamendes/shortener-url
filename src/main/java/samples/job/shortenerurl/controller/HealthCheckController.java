package samples.job.shortenerurl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class HealthCheckController {

    private final LocalDateTime startTime = LocalDateTime.now();

    @GetMapping("/health")
    public Map<String, ?> healthCheck() {
        return Map.of(
                "status", "UP",
                "uptime", Duration.between(startTime, LocalDateTime.now()).toMillis() + "ms"
        );
    }
}

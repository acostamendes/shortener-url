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
        Duration uptime = Duration.between(startTime, LocalDateTime.now());
        long seconds = uptime.getSeconds();

        String uptimeFormatted;
        if (seconds < 60) {
            uptimeFormatted = seconds + " seconds";
        } else if (seconds < 3600) { // 60 seconds * 60 minutes
            long minutes = seconds / 60;
            uptimeFormatted = minutes + " minute" + (minutes > 1 ? "s" : "");
        } else if (seconds < 86400) { // 60 seconds * 60 minutes * 24 hours
            long hours = seconds / 3600;
            uptimeFormatted = hours + " hour" + (hours > 1 ? "s" : "");
        } else {
            long days = seconds / 86400;
            uptimeFormatted = days + " day" + (days > 1 ? "s" : "");
        }

        return Map.of(
                "status", "UP",
                "uptime", uptimeFormatted
        );
    }
}

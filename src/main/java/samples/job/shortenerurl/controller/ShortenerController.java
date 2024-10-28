package samples.job.shortenerurl.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.service.ShortenerService;

import java.io.IOException;

@RestController
public class ShortenerController {
    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping("/shortener")
    public Shortener createShortener(@RequestBody Shortener shortener) {
        return shortenerService.createShortener(shortener);
    }

    @DeleteMapping("/shortener/{id}")
    public void deleteShortener(@PathVariable Long id) {
        shortenerService.deleteShortener(id);
    }

    @GetMapping("/shortener/{hash}")
    public Shortener findByHash(@PathVariable String hash) {
        return shortenerService.findByHash(hash);
    }

    @GetMapping("/shortener/redirect/{hash}")
    public void redirect(@PathVariable String hash, HttpServletResponse response) throws IOException {
        Shortener shortener = shortenerService.findByHash(hash);
        shortener.setCount(String.valueOf(Integer.parseInt(shortener.getCount()) + 1));
        shortenerService.updateShortener(shortener);
        response.sendRedirect(shortener.getOriginLocation());
    }

    @GetMapping("/shortener")
    public Iterable<Shortener> findAll() {
        return shortenerService.findAll();
    }
}

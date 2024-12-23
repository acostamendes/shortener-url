package samples.job.shortenerurl.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.service.ShortenerService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Map;
import java.util.zip.CRC32;

@RestController
@RequestMapping ("/api/v1/shortener")
public class ShortenerController {
    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping
    public ResponseEntity<Shortener> createShortener(@RequestBody Map<String, String> request) {
        String originLocation = request.get("origin_location");
        if (originLocation == null || originLocation.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Criar o objeto Shortener
        Shortener shortener = new Shortener();
        shortener.setOriginLocation(originLocation);
        shortener.setTimestamp(new Timestamp(System.currentTimeMillis())); // Converter para Timestamp
        shortener.setCount(String.valueOf(0L)); // Converter long para String

        // Gerar hash com CRC32
        CRC32 crc32 = new CRC32();
        crc32.update(originLocation.getBytes(StandardCharsets.UTF_8));
        String hash = Long.toHexString(crc32.getValue());

        shortener.setHash(hash);

        // Salvar no banco de dados
        Shortener savedShortener = shortenerService.createShortener(shortener);

        return ResponseEntity.ok(savedShortener);
    }


    @DeleteMapping("/{id}")
    public void deleteShortener(@PathVariable Long id) {
        shortenerService.deleteShortener(id);
    }

    @GetMapping("/{hash}")
    public Shortener findByHash(@PathVariable String hash) {
        return shortenerService.findByHash(hash);
    }

    @GetMapping("/redirect/{hash}")
    public void redirect(@PathVariable String hash, HttpServletResponse response) throws IOException {
        Shortener shortener = shortenerService.findByHash(hash);
        shortener.setCount(String.valueOf(Integer.parseInt(shortener.getCount()) + 1));
        shortenerService.updateShortener(shortener);
        response.sendRedirect(shortener.getOriginLocation());
    }

    @GetMapping
    public Iterable<Shortener> findAll() {
        return shortenerService.findAll();
    }
}


package samples.job.shortenerurl.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.ShotenerDTO.ShortenerDTO;
import samples.job.shortenerurl.service.ServiceEncrypt;
import samples.job.shortenerurl.service.ShortenerService;

import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping ("/api/v1/shortener")
public class ShortenerController {
    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping
    public ResponseEntity<ShortenerDTO> createShortener(@RequestBody ShortenerDTO dto) {
        String originLocation = dto.getOriginLocation();
        if (originLocation == null || originLocation.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Criar o objeto Shortener
        ShortenerDTO shortener = new ShortenerDTO();
        shortener.setOriginLocation(originLocation);
        shortener.setTimestamp(new Timestamp(System.currentTimeMillis())); // Converter para Timestamp
        shortener.setCount(0);

        // Gerar hash com CRC32
        String hash = ServiceEncrypt.generateCRC32Hash(originLocation);

        shortener.setHash(hash);

        // Salvar no banco de dados
        Shortener savedShortener = shortenerService.createShortener(shortener);

        ShortenerDTO responseDTO = new ShortenerDTO(savedShortener);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
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
        shortener.setCount(shortener.getCount() + 1);
        shortenerService.updateShortener(shortener);
        response.sendRedirect(shortener.getOriginLocation());
    }

    @GetMapping
    public Iterable<Shortener> findAll() {
        return shortenerService.findAll();
    }
}


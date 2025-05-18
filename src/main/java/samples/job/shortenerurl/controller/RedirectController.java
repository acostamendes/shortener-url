package samples.job.shortenerurl.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.service.ShortenerService;

import java.io.IOException;

@RestController
public class RedirectController {

    private final ShortenerService shortenerService;

    public RedirectController (ShortenerService shortenerService){
        this.shortenerService = shortenerService;
    }

    @GetMapping("/{hash}")
    public void redirect(@PathVariable String hash, HttpServletResponse response) throws IOException {
        Shortener shortener = shortenerService.findByHash(hash);
        if (shortener == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"Hash not found");
            return;
        }
        shortener.setCount(shortener.getCount() + 1);  // Incrementa o contador de acessos
        shortenerService.updateShortener(shortener);  // Incrementa o contador de acessos
        response.sendRedirect(shortener.getOriginLocation()); // Redireciona para a URL original

    }
}

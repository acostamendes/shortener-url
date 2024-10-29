package samples.job.shortenerurl.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.repository.ShortenerRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;

    @Autowired
    public ShortenerService(ShortenerRepository repository) {
        this.shortenerRepository = repository;
    }

    @Transactional
    public Shortener createShortener(Shortener shortener) {
        shortener.setEventTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        shortener.setCount("0");
        return shortenerRepository.save(shortener);
    }

    @Transactional
    public Shortener updateShortener(Shortener shortener) {
        return shortenerRepository.save(shortener);
    }

    @Transactional
    public void deleteShortener(Long id) {
        shortenerRepository.deleteById(id);
    }

    public Shortener findByHash(String hash) {
        return shortenerRepository.findByHash(hash);
    }

    public Iterable<Shortener> findAll() {
        return shortenerRepository.findAll();
    }
}

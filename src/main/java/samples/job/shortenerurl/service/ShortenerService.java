package samples.job.shortenerurl.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.repository.ShortenerRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.zip.CRC32;

@Service
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;

    @Autowired
    public ShortenerService(ShortenerRepository repository) {
        this.shortenerRepository = repository;
    }

    //@Transactional
    //public Shortener createShortener(Shortener shortener) {
  //      shortener.setTimestamp(Timestamp.valueOf(LocalDateTime.now())); // Atribui o timestamp diretamente
    //shortener.setCount("0");
    // return shortenerRepository.save(shortener);
   // }

    @Transactional
    public Shortener updateShortener(Shortener shortener) {
        return shortenerRepository.save(shortener);
    }

    @Transactional
    public void deleteShortener(Long id) {
        shortenerRepository.deleteById(id);
    }

    public Shortener findByHash(String hash) {
        return shortenerRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("Hash not found: " + hash));
    }

    public Iterable<Shortener> findAll() {
        return shortenerRepository.findAll();
    }

    @Transactional
    public Shortener createShortener(Shortener shortener) {
        if (shortener.getOriginLocation() == null || shortener.getOriginLocation().isEmpty()) {
            throw new IllegalArgumentException("Origin location cannot be null or empty");
        }
        shortener.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        CRC32 crc32 = new CRC32();
        crc32.update(shortener.getOriginLocation().getBytes());
        long hashValue = crc32.getValue();

        String hasString = Long.toHexString(hashValue);

        shortener.setHash(hasString);
        shortener.setCount("0");

        return shortenerRepository.save(shortener);
    }
}


package samples.job.shortenerurl.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samples.job.shortenerurl.ShotenerDTO.ShortenerDTO;
import samples.job.shortenerurl.model.Shortener;
import samples.job.shortenerurl.repository.ShortenerRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;
    private final ServiceEncrypt serviceEncrypt;


    @Autowired
    public ShortenerService(ShortenerRepository repository, ServiceEncrypt serviceEncrypt) {
        this.shortenerRepository = repository;
        this.serviceEncrypt = serviceEncrypt;
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
    public Shortener createShortener(ShortenerDTO dto) {
        if (dto.getOriginLocation() == null || dto.getOriginLocation().isEmpty()) {
            throw new IllegalArgumentException("Origin location cannot be null or empty");
        }
        dto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        String originLocation = dto.getOriginLocation();
        String hash = ServiceEncrypt.generateCRC32Hash(dto.getOriginLocation());
        dto.setHash(hash);

        dto.setHash(hash);
        dto.setCount(0);

        Shortener shortener = new Shortener();
        shortener.setOriginLocation(dto.getOriginLocation());
        shortener.setHash(dto.getHash());
        shortener.setTimestamp(dto.getTimestamp());
        shortener.setCount(dto.getCount());

        return shortenerRepository.save(shortener);
    }
}


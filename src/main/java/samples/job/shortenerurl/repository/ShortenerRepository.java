package samples.job.shortenerurl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import samples.job.shortenerurl.model.Shortener;

@Repository
public interface ShortenerRepository extends CrudRepository<Shortener, Long> {
    Shortener findByHash(String hash);
}


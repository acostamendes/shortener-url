package samples.job.shortenerurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samples.job.shortenerurl.model.Shortener;

import java.util.Optional;

@Repository
public interface ShortenerRepository extends JpaRepository<Shortener, Long> {

    Optional<Shortener> findByHash(String hash);
}



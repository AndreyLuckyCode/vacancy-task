package andrey.code.store.repository;

import andrey.code.store.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    List<QuoteEntity> findTop10ByOrderByUpvoteDesc();

    List<QuoteEntity> findTop10ByOrderByUpvoteAsc();
}

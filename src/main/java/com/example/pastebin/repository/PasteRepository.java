package com.example.pastebin.repository;

import com.example.pastebin.model.Paste;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Long> {
    Paste findAllByUrlLike(String url);
    void deleteAllByLastTimeIsBefore(Instant now);
    @Query(
            value = "select * from paste p where p.status in ('PUBLIC') order by publicate_time desc limit 10",
            nativeQuery = true)
    List<Paste> findTenLastPaste();

    List<Paste> findAll(Specification<Paste> specification);

}

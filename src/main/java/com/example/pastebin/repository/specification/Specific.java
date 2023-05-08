package com.example.pastebin.repository.specification;

import com.example.pastebin.enumss.Status;
import com.example.pastebin.model.Paste;
import org.springframework.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class Specific  {
    public static Specification<Paste> byTitle(String title){
        return StringUtils.hasText(title) ? (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("title"), title),
                        criteriaBuilder.equal(root.get("status"), Status.PUBLIC)
                ) : null;
    }
    public static Specification<Paste> byBody(String body){
        return StringUtils.hasText(body) ? (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get("body"), "%" + body + "%"),
                        criteriaBuilder.equal(root.get("status"), Status.PUBLIC)
                ) : null;
    }
    public static Specification<Paste> byNotExpired() {
        return (root, query, cb) -> cb.greaterThan(root.get("lastTime"), Instant.now());
    }
}


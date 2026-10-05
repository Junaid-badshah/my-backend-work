package com.example.book.demo.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("caffeine")
class CacheEvictionTest {

    @Autowired
    private CacheManager cacheManager;

    @Test
    void testCacheInitializationAndEvictionProperties() {
        Cache booksCache = cacheManager.getCache("books");
        assertThat(booksCache).isNotNull();

        // Put an item into the cache
        booksCache.put(1, "Spring Boot in Action");
        assertThat(booksCache.get(1, String.class)).isEqualTo("Spring Boot in Action");

        // Evict/Clear cache
        booksCache.evict(1);
        assertThat(booksCache.get(1)).isNull();
    }
}
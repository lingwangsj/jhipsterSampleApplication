package io.github.jhipster.application.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tenant.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tenant.class.getName() + ".ownedVenues", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tenant.class.getName() + ".ownedNetworks", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tenant.class.getName() + ".ownedTags", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Venue.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Venue.class.getName() + ".ownedAPS", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.AP.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.AP.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Network.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Network.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.APConfig.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.APConfig.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tag.class.getName() + ".aps", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tag.class.getName() + ".networks", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tag.class.getName() + ".apConfigs", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}

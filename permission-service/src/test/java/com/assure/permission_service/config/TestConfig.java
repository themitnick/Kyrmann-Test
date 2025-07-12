package com.assure.permission_service.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;

/**
 * Configuration de test pour désactiver les dépendances externes
 */
@TestConfiguration
@Profile("test")
public class TestConfig {

    /**
     * Mock du client Eureka pour éviter les tentatives de connexion
     */
    @MockitoBean
    private EurekaClientConfigBean eurekaClientConfigBean;
}

package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("externalized")
@TestPropertySource("classpath:yanny.properties")
@SpringJUnitConfig(PropertiesInterpreterTest.TestConfig.class)
class PropertiesInterpreterTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Profile("externalized")
    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class TestConfig {

    }

    @Test
    void whatIheard() {
        String word = hearingInterpreter.whatIheard();

        assertEquals("YANNY", word);
    }
}

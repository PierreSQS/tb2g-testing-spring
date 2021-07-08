package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LaurelConfig {

    @Bean
    public WordProducer wordProducer() {
        return new LaurelWordProducer();
    }

    @Bean
    public HearingInterpreter hearingInterpreter() {
        return new HearingInterpreter(wordProducer());
    }
}

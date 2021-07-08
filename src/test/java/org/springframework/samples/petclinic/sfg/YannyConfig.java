package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YannyConfig {

    @Bean
    public WordProducer wordProducer() {
        return new YannyWordProducer();
    }

    @Bean
    public HearingInterpreter hearingInterpreter() {
        return new HearingInterpreter(wordProducer());
    }
}

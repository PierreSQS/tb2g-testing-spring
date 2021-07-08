package org.springframework.samples.petclinic.sfg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HearingInterpreterWithoutSpringTest {

    HearingInterpreter hearingInterpreter;

    @Before
    public void setUp() {
        hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());
    }

    @Test
    public void whatIheard() {
        String word = hearingInterpreter.whatIheard();
        assertEquals("Laurel",word);
    }
}
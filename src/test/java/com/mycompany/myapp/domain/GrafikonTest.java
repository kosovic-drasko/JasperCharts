package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrafikonTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Grafikon.class);
        Grafikon grafikon1 = new Grafikon();
        grafikon1.setId(1L);
        Grafikon grafikon2 = new Grafikon();
        grafikon2.setId(grafikon1.getId());
        assertThat(grafikon1).isEqualTo(grafikon2);
        grafikon2.setId(2L);
        assertThat(grafikon1).isNotEqualTo(grafikon2);
        grafikon1.setId(null);
        assertThat(grafikon1).isNotEqualTo(grafikon2);
    }
}

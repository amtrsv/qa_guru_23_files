package ru.sberbank.model;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;

public class BonusJsonTest {

    private final ClassLoader cl = BonusJsonTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка содержимого JSON в ответе")
    void checkJsonInAnswer() throws Exception {
        try (InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(cl.getResourceAsStream("bonus.json"))))
        {
            BonusJson bj = objectMapper.readValue(isr, BonusJson.class);
            assertThat(bj.getAccount()).isEqualTo("42305810638260605983");
            assertThat(bj.getTb()).isEqualTo(38);
            assertThat(bj.getGroup()).isEqualTo(116);
            assertThat(bj.getAgreement()).contains("331", "9", "2022-12-16");
        }
    }
}

package com.leave;

import com.leave.config.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UtilsTest {

	@Test
	public void checkDurationOnWeekends() {
		int duration = Utils.calculateDuration(LocalDate.of(2021,3,27),LocalDate.of(2021,3,29));
		assertThat(duration).isEqualTo(1);
	}
}

package ch.fhnw.cpib.project.km.base;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import ch.fhnw.cpib.project.km.code.IMLTestPrograms;

public abstract class AbstractTest {

	@Test
	void testAll() throws Exception {
		for (String program : IMLTestPrograms.getValidPrograms()) {
			assertNotNull(test(program));
		}
	}
	
	protected abstract Object test(String input) throws Exception;
}

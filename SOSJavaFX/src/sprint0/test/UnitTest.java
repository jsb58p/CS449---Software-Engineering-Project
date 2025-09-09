package sprint0.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;

/**
 * Unit Tests
 */
class UnitTest {

	@Test
	void testAddition() {
	    assertEquals(4, 2 + 2);
	}

	@Test
	void testGreaterThan() {
	    assertTrue(10 > 5);
	}

	@Test
	void testLessThan() {
	    assertFalse(3 > 7);
	}

	@Test
	void testNotNull() {
	    assertNotNull("Hello");
	}


}

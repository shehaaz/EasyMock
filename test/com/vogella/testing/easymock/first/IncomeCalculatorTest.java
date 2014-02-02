package com.vogella.testing.easymock.first;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class IncomeCalculatorTest {

	private ICalcMethod calcMethod;
	private IncomeCalculator calc;

	@Before
	public void setUp() throws Exception {
		// NiceMocks return default values for
		// unimplemented methods
		calcMethod = createNiceMock(ICalcMethod.class);
		calc = new IncomeCalculator();
	}

	@Test
	public void testCalc1() {
		
		//Mocked Interface is given as parameter
		calc.setCalcMethod(calcMethod);
		
		// Setting up the expected value of the method call calc
		expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0).times(2);
		expect(calcMethod.calc(Position.PROGRAMMER)).andReturn(50000.0);
		// Setup is finished need to activate the mock
		replay(calcMethod); //Need to reply() after expect()

		try {
			calc.calc();
			fail("Exception did not occur");
		} catch (RuntimeException e) {

		}
		
		calc.setPosition(Position.BOSS);
		assertEquals(70000.0, calc.calc(), 0);
		assertEquals(70000.0, calc.calc(), 0);
		
		calc.setPosition(Position.PROGRAMMER);
		assertEquals(50000.0, calc.calc(), 0);
		
		calc.setPosition(Position.SURFER);
		
		verify(calcMethod);
	}

	@Test (expected = RuntimeException.class)
	public void testNoCalc() {
		calc.setPosition(Position.SURFER);
		calc.calc();
		
//		expect(calcMethod.calc(Position.SURFER)).andReturn(10000.0);
//		replay(calcMethod); //Need to reply after expect
//		
//		calc.setCalcMethod(calcMethod);
//		calc.setPosition(Position.SURFER);
//		
//		assertEquals(10000.0, calc.calc(), 0);	
	}

	//RuntimeException because ImcomeCalculator Position not set.
	@Test (expected = RuntimeException.class)
	public void testNoPosition() {
		expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0);
		replay(calcMethod);
		calc.setCalcMethod(calcMethod);
		assertEquals(70000.0, calc.calc(), 0);
		verify(calcMethod);
		
//		expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0);
//		replay(calcMethod);
//		calc.setCalcMethod(calcMethod);
//		calc.setPosition(Position.BOSS);
//		assertEquals(70000.0, calc.calc(), 0);
//		verify(calcMethod);
	}

	@Test(expected = RuntimeException.class)
	public void testCalc2() {
		// Setting up the expected value of the method call calc
		expect(calcMethod.calc(Position.SURFER)).andThrow(new RuntimeException("Don't know this guy")).times(1);

		// Setup is finished need to activate the mock
		replay(calcMethod);
		calc.setPosition(Position.SURFER);
		calc.setCalcMethod(calcMethod);
		calc.calc();
	}

} 


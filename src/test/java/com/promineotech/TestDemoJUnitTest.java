package com.promineotech;
import static org.assertj.core.api.Assertions.assertThat;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.promineotech.TestDemo;

import static org.junit.jupiter.params.provider.Arguments.*;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	
	@Test
	void assertThreePlusTwoEqualsFive() {
		assertEquals(5, testDemo.addPositive(3, 2));
		assertThat(testDemo.addPositive(3, 2)).isEqualTo(5);
	}
	
	@Test
	void assertThatTestDemoAddsTwoDigits() {
		assertEquals(60, testDemo.addPositive(20, 40));
		assertThat(testDemo.addPositive(10, 50)).isEqualTo(60);
	}
	
	
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argsForAddsTwoDigits")
	void assertThatTestDemoAddsTwoDigits(int a, int b, int expected, Class<? extends Exception> exceptionClass) {
		if(Objects.isNull(exceptionClass)) {
			int actual = testDemo.addPositive(a, b);
			assertThat(actual).isEqualTo(expected);
		} else {
		assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(exceptionClass);
		}
	}


	static Stream<Arguments> argsForAddsTwoDigits() {
//	@formatter:off
		return Stream.of(
				arguments(5, 6, 11, null),
				arguments(100, 1, 101, null),
				arguments(0, 2, 0, IllegalArgumentException.class)
				
				);
//		@formatter:on
	}
	
	
	@ExtendWith(MockitoExtension.class)
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(testDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
}

package com.intraway.fizzbuzz;

import com.intraway.fizzbuzz.exceptions.BadRequest;
import com.intraway.fizzbuzz.model.Operation;
import com.intraway.fizzbuzz.repository.OperationsRepository;
import com.intraway.fizzbuzz.service.FizzBuzzService;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FizzbuzzApplicationTests {

	private final String NO_MULTIPLOS = "No se encontraron múltiplos";
	private final String MULTIPLOS_DE_3 = "Se encontraron múltiplos de 3";
	private final String MULTIPLOS_DE_5 = "Se encontraron múltiplos de 5";
	private final String MULTIPLOS_DE_3_Y_5 = "Se encontraron múltiplos de 3 y de 5";

	@Test(expected = BadRequest.class)
	public void testVaidateRange() {
		int min = ThreadLocalRandom.current().nextInt(10, 20);
		int max = ThreadLocalRandom.current().nextInt(1, 9);

		FizzBuzzService fizzBuzzService = new FizzBuzzService();
		fizzBuzzService.validateRange(min, max);
	}

	@Test
	public void testValidateNumbersMultiples() {
		String items;
		String expected1 = "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz";
		String expected2 = "Buzz,-4,Fizz,-2,-1,FizzBuzz,1,2,Fizz,4,Buzz";
		String expected3 = "1,2,Fizz";

		FizzBuzzService fizzBuzzService = new FizzBuzzService();

		items = fizzBuzzService.validateNumbersMultiples(1,15);
		Assert.assertEquals(expected1,items);

		items = fizzBuzzService.validateNumbersMultiples(-5,5);
		Assert.assertEquals(expected2,items);

		items = fizzBuzzService.validateNumbersMultiples(1,3);
		Assert.assertEquals(expected3,items);
	}

	@Test
	public void testMessages() {
		OperationsRepository operationsRepositoryMock = mock(OperationsRepository.class);
		FizzBuzzService fizzBuzzService = new FizzBuzzService(operationsRepositoryMock);

		Operation operation = new Operation();
		operation.setId(1L);
		when(operationsRepositoryMock.save(any(Operation.class))).thenReturn(operation);

		Assert.assertEquals(NO_MULTIPLOS, fizzBuzzService.getNumbersList(1,2).getDescription());
		Assert.assertEquals(MULTIPLOS_DE_3, fizzBuzzService.getNumbersList(1,3).getDescription());
		Assert.assertEquals(MULTIPLOS_DE_5, fizzBuzzService.getNumbersList(5,5).getDescription());
		Assert.assertEquals(MULTIPLOS_DE_3_Y_5, fizzBuzzService.getNumbersList(1,5).getDescription());
	}

}

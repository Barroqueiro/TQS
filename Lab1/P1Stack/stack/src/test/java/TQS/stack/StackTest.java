/**
 * 
 */
package TQS.stack;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import TQS.stack.Stack;


public class StackTest {

	@Test
	public void isEmpty() {
		Stack<String> s = new Stack<>();
		
		assertTrue(s.isEmpty(), "Empty stack should be empty!");
	}
	
	@Test
	public void sizeOnConstruction() {
		Stack<String> s = new Stack<>();
		
		assertEquals( 0,s.size());
	}
	
	@Test
	public void sizeAfterNPushes() {
		Stack<String> s = new Stack<>();
		s.push("Ganda String 1");
		s.push("Ganda String 2");
		s.push("Ganda String 3");
		s.push("Ganda String 4");
		
		assertEquals( 4,s.size());
		assertFalse(s.isEmpty());
	}
	
	@Test
	public void theValuePoped() {
		Stack<String> s = new Stack<>();
		String toInsert = "Ganda String 1";
		s.push(toInsert);
		assertEquals( toInsert, s.pop());
	}
	
	@Test
	public void theValuePeeked() {
		Stack<String> s = new Stack<>();
		String toInsert = "Ganda String 1";
		s.push(toInsert);
		int sizeAtStart = s.size();
		assertEquals( toInsert, s.peek());
		assertEquals(sizeAtStart,s.size());
	}
	
	@Test
	public void afterNPops() {
		Stack<String> s = new Stack<>();
		s.push("Ganda String 1");
		s.push("Ganda String 2");
		s.push("Ganda String 3");
		s.push("Ganda String 4");
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		assertTrue(s.isEmpty());
		assertEquals(0,s.size());
	}
	
	@Test
	public void popingFromEmptyStack() {
		Stack<String> s = new Stack<>();
		assertThrows(NoSuchElementException.class, () -> s.pop());
	}
	
	@Test
	public void peekingFromEmptyStack() {
		Stack<String> s = new Stack<>();
		assertThrows(NoSuchElementException.class, () -> s.peek());
	}

}

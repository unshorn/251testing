package testingTutorial;

import org.junit.jupiter.api.Test;
import static
        org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

public class CaseTester {
	//** Lecturer vs Student Contractual Violation **//
	@Test
	public void LectureEqualsInvalidTest()
	{
		/* 
		 * If a lecture has the same id and firstname, 
		 * but a different surname, the contract between
		 * hashcode and equals IS violated. 
		 */
		int id = 1;
		String firstName = "john";
	
		// Negation
		Lecturer lectA = new Lecturer(id, firstName, "smith");
		Lecturer lectB = new Lecturer(id, firstName, "davy");
		
		// Lecturer A should NOT equal Lecturer B
		// This assumption should fail, but it does not!
		assumeTrue(lectA.equals(lectB));
		// This proves the notion that having 2 
		// lecturers who are 'equal' does not imply that they have identical hashcodes
		assertEquals(lectA.hashCode(), lectB.hashCode(), "Contract violated!");
	}
	@Test
	public void LectureEqualsValidTest()
	{
		int id = 1;
		String firstName = "john";
	
		Lecturer lectC = new Lecturer(id, firstName, "smith");
		Lecturer lectD = new Lecturer(id, firstName, "smith");
		
		// Lecturer C should equal Lecturer D
		assumeTrue(lectC.equals(lectD));
		// Lecturer C's hashcode should equal Lecturer D's hashcode
		assertEquals(lectC.hashCode(), lectD.hashCode());
	}
	@Test
	public void StudentEqualsInvalidTest()
	{
		/* 
		 * If a student has the same id and firstname, 
		 * but a different surname, the contract between
		 * hashcode and equals is NOT violated. 
		 * We can assume that if Student A equals Student B, then they will have 
		 * the same hashcode. Conversely, if Student A does not equal Student B, 
		 * then they will not have the same hashcode
		 */
		int id = 1;
		String firstName = "john";

		// Negation
		Student stuA = new Student(id, firstName, "smith");
		Student stuB = new Student(id, firstName, "davy");
		
		// Student A should NOT equal student B
		// This assumption should fail
		assumeTrue(stuA.equals(stuB));
		// Student A's hashcode should NOT equal student B's hashcode
		assertEquals(stuA.hashCode(), stuB.hashCode());
	}
	@Test
	public void StudentEqualsValidTest()
	{
		int id = 1;
		String firstName = "john";
		
		Student stuC = new Student(id, firstName, "smith");
		Student stuD = new Student(id, firstName, "smith");
		
		// Student C should equal student D
		assumeTrue(stuC.equals(stuD));
		// Student C's hashcode should equal student D's hashcode
		assertEquals(stuC.hashCode(), stuD.hashCode());
	
	}

	//** 2. IdentityHashMap **//
	@Test
	public void HashMapValid()
	{
		String sameValue = "key";
		String key1 = new String(sameValue); // Same key
		String key2 = new String(sameValue); // Same key
		String val = "random_value";
		HashMap<String, String> IMap = new HashMap<String, String>();
		
		IMap.put(key1, val);
		
		assumeTrue(key1.equals(key2));
		assertTrue(IMap.get(key1) == IMap.get(key2));
	}
	@Test
	public void IdentityHashMapInvalid()
	{
		String sameValue = "key";
		String key1 = new String(sameValue); // Same key
		String key2 = new String(sameValue); // Same key
		String val = "random_value";
		IdentityHashMap<String, String> IMap = new IdentityHashMap<String, String>();
		
		IMap.put(key1, val);
		
		// This will succeed with a standard Map implementation		
		assumeTrue(key1.equals(key2));
		assertTrue(IMap.get(key1) == IMap.get(key2), "Different values in map!");
	}

	//** 3. Unmodifiable Collections **//
	@Test
	public void UnmodifiableAddTest()
	{	
		ArrayList<String> myList = new ArrayList<String>();
		
		myList.add("1st val");
		myList.add("2nd val");
		myList.add("3rd val");
		
		List<String> unmodList = Collections.unmodifiableList(myList);

		// We cannot add to an unmodifiable list
		assertThrows(UnsupportedOperationException.class, () -> unmodList.add("4th val"));		
	}
	@Test
	public void UnmodifiableRemoveTest()
	{
		ArrayList<String> myList = new ArrayList<String>();
		
		myList.add("1st val");
		myList.add("2nd val");
		myList.add("3rd val");
		
		List<String> unmodList = Collections.unmodifiableList(myList);

		// We cannot remove from an unmodifiable list
		assertThrows(UnsupportedOperationException.class, () -> unmodList.remove(0));		
	}
	@Test
	public void UnmodifiableIterateTest()
	{
		ArrayList<String> myList = new ArrayList<String>();
		
		myList.add("1st val");
		myList.add("2nd val");
		myList.add("3rd val");
		
		List<String> unmodList = Collections.unmodifiableList(myList);

		// We can still iterate over an unmodifiable list
		assertTrue(unmodList instanceof Iterable);		
	}
}

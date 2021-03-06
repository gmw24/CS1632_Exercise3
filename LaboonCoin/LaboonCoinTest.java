//Gabriel Wells and Daniel Critchfield
import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
	LaboonCoin l = new LaboonCoin();
	assertNotNull(l);
    }
    
	//Assert hash function works as expected
	//tests String "boo" is hashed as in documentation
	@Test
	public void testLaboonHash() {
		LaboonCoin l = new LaboonCoin();
		int hash = l.hash("boo");
		int res = 1428150834;
		assertEquals(res, hash);
	}
	
	// Asserts that the createBlock method is converting values to a string representation that is
	// equal to the hex conversions made in the test
	@Test
	public void testCreateBlock() {
		StringBuilder expected = new StringBuilder();
		expected.append("apple|");
		LaboonCoin one = new LaboonCoin();
		String test = one.createBlock("apple", 0003, 124, 30924);
		String prevHashHex = String.format("%08x", 0003);
		expected.append(prevHashHex);
		expected.append('|');
		String nonceHex = String.format("%08x", 124);
		expected.append(nonceHex);
		expected.append('|');
		String hashHex = String.format("%08x", 30924);
		expected.append(hashHex);
		assertEquals(test, expected.toString());
	}
	
	// tests the createblock method when an empty string is passed
	@Test
	public void createBlockData() {
		LaboonCoin one = new LaboonCoin();
		String res = one.createBlock("", 10, 10, 22);
		assertEquals("", res);
	}
	
	
	// test that the conversions to hex are working as expected
	@Test
	public void createBlockHashes() {
		LaboonCoin one = new LaboonCoin();
		String test = "apple|0000000a|0000000b|0000000c";
		String expected = one.createBlock("apple", 10, 11, 12);
		assertEquals(test, expected);
	}
	
	// assert that the getBlockChain method has three values after createBlock is called three times
	@Test
	public void formatBlockChain() {
		LaboonCoin one = new LaboonCoin();
		one.createBlock("apple", 10, 11, 12);
		one.createBlock("peach", 8970, 2121, 12132);
		one.createBlock("cool", 132, 001, 11232);
		String result = one.getBlockChain();
		String[] newLines = result.split("\\n");
		assertEquals(newLines.length, 3);
	}
	
	
	// Test that the getBlockChain() is printing out correctly
	@Test 
	public void testPrintFormat(){
		StringBuilder expected = new StringBuilder();
		expected.append("apple|0000000a|0000000b|0000000c\n");
		expected.append("apple|0000000b|0000000c|0000000c");
		LaboonCoin one = new LaboonCoin();
		one.createBlock("apple", 10, 11, 12);
		one.createBlock("apple", 11, 12, 12);
		assertEquals(expected.toString(), one.getBlockChain());
	}
	
	
	//test if user passes in '\n' as an argument and see how getBlockChain() handles it
	@Test public void newLineCharacterTest(){
		StringBuilder expected = new StringBuilder();
		expected.append("\n|00000001|0000000f|00000002");
		LaboonCoin one = new LaboonCoin();
		one.createBlock("\n", 1, 15, 2);
		assertEquals(expected.toString(), one.getBlockChain());
	}
		
	//Tests when hash function is called with an empty string
	//This is an edge case
	@Test
	public void testLaboonHashEmptyString() {
		LaboonCoin l = new LaboonCoin();
		int hash = l.hash("");
		int res = 10000000;
		assertEquals(res, hash);
	}
	
	//Test when hash function is called with a combination of chars and ints 
	@Test
	public void testLaboonHashInvalidInput() {
		LaboonCoin l = new LaboonCoin();
		int hash = l.hash("GABEWELLS1994");
		int res = 1052816540;
		assertEquals(res, hash);
	}
	
	//Test whether a truly valid hash is valid
	@Test
	public void testIsValidHash() {
		LaboonCoin l = new LaboonCoin();
		//1038730 = 0x000fd98a
		boolean res = l.validHash(3, 1038730);
		assertTrue(res);
	}
	
	//Test whether an invalid hash is truly invalid
	@Test
	public void testIsInvalidHash() {
		LaboonCoin l = new LaboonCoin();
		//2147483647 = 0x7FFFFFFF
		boolean res = l.validHash(3, Integer.MAX_VALUE);
		assertFalse(res);
	}
	
	//Tests whether a hash that has three nonleading zeros is valid
	//This is an edge case
	@Test
	public void testIsInvalidHashWithZeros() {
		LaboonCoin l = new LaboonCoin();
		//16619920 = 0x00FD9990
		boolean res = l.validHash(3, 16619920);
		assertFalse(res);
	}
}

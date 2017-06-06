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
	@Test
	public void testIsInvalidHashWithZeros() {
		LaboonCoin l = new LaboonCoin();
		//16619920 = 0x00FD9990
		boolean res = l.validHash(3, 16619920);
		assertFalse(res);
	}
}

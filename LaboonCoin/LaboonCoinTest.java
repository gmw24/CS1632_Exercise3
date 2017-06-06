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
}

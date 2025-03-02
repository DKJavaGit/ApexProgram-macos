package org.example.apex.test;

import org.example.apex.security.PassGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PassGeneratorTest {
    @Test
    public void testEmptyPassword() {
        assertEquals((byte) 0, PassGenerator.calculateReliability(""));
    }

    @Test
    public void testNullPassword() {
        assertEquals((byte) 0, PassGenerator.calculateReliability(null));
    }

    @Test
    public void testVeryShortPassword() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("abc"));
    }

    @Test
    public void testShortPasswordOnlyLowercase() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("abcdefg"));
    }

    @Test
    public void testShortPasswordLowercaseAndUppercase() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("Abcdefg"));
    }

    @Test
    public void testMediumPasswordOnlyLowercase() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("abcdefgh"));
    }

    @Test
    public void testMediumPasswordLowercaseAndNumber() {
        assertEquals((byte) 2, PassGenerator.calculateReliability("abcdefg8"));
    }

    @Test
    public void testMediumPasswordLowercaseAndUppercase() {
        assertEquals((byte) 2, PassGenerator.calculateReliability("Abcdefgh"));
    }

    @Test
    public void testNormalPasswordLowercaseUppercaseAndNumber() {
        assertEquals((byte) 3, PassGenerator.calculateReliability("Abcvcdefgh12"));
    }

    @Test
    public void testNormalPasswordLowercaseUppercaseAndSpecial() {
        assertEquals((byte) 3, PassGenerator.calculateReliability("Abcdefgfcgh!"));
    }

    @Test
    public void testNormalPasswordLowercaseNumberAndSpecial() {
        assertEquals((byte) 3, PassGenerator.calculateReliability("abcdsbsbefg8!"));
    }

    @Test
    public void testStrongPasswordAllChars() {
        assertEquals((byte) 4, PassGenerator.calculateReliability("Abasdasdcdefgh12!@#$"));
    }

    @Test
    public void testStrongPasswordLonger() {
        assertEquals((byte) 4, PassGenerator.calculateReliability("A1b2c3d4e5f6g7h8i9j0k$"));
    }

    @Test
    public void testPasswordJustUnderStrong() {
        assertEquals((byte) 3, PassGenerator.calculateReliability("Abcdefgh12!@#")); // 15 characters, meets category requirement
    }

    @Test
    public void testPasswordAllLowerAndNumberLong() {
        assertEquals((byte) 2, PassGenerator.calculateReliability("abcdefghijklmn12"));
    }

    @Test
    public void testPasswordOnlyNumberLong() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("123456789012345"));
    }

    @Test
    public void testPasswordOnlyUpperLong() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("ABCDEFGHIJKLMNOPQRS"));
    }

    @Test
    public void testPasswordOnlySymbolLong() {
        assertEquals((byte) 1, PassGenerator.calculateReliability("!@#$%^&*()_+=-`~"));
    }

    @Test
    public void testPasswordWithUnicodeCharacters() {
        // Test with Unicode characters; might need adjustments based on your encoding
        assertEquals((byte) 1, PassGenerator.calculateReliability("你好世界"));
    }

    @Test
    public void testPasswordWithMixedUnicodeAndAscii() {
        // Test with mixed Unicode and ASCII characters; might need adjustments based on your encoding
        assertEquals((byte) 1, PassGenerator.calculateReliability("hello你好"));
    }
    @Test
    public void testLengthJustBelowMedium(){
        assertEquals((byte) 1,PassGenerator.calculateReliability("abcdefg"));
    }
    @Test
    public void testCategoryJustBelowNormal(){
        assertEquals((byte) 2, PassGenerator.calculateReliability("abcdefg7"));
    }

}

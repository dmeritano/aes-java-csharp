package com.dmeritano.javaaes;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AESChiperTest {
    
    private String key;
    private String textToEncrypt;
    private String textEncrypted;
    
    @Before
    public void setUp() throws Exception {
        key = TestUtilities.getAlphaNumericRandomString(10);
        textToEncrypt = TestUtilities.getAlphaNumericRandomString(50);
        textEncrypted = AESChiper.encrypt(textToEncrypt, key);
    }
    
     @Test
    public void testEncrypt() throws Exception {
        assertEquals(textEncrypted, AESChiper.encrypt(textToEncrypt, key));
    }

    @Test
    public void testDecrypt() throws Exception {
        assertEquals(textToEncrypt, AESChiper.decrypt(textEncrypted, key));
    }   
    
}

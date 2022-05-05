using System;
using CSharpAES;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CSharAESTest
{
    [TestClass]
    public class AESChiperTest
    {
        private string key;
        private string data;
        private string dataEncrypted;

        public AESChiperTest()
        {
            key = TestsUtilities.GetAlphaNumericRandomString(10);
            data = TestsUtilities.GetAlphaNumericRandomString(50);
            dataEncrypted = AESChiper.Encrypt(data, key);
        }

        [TestMethod]
        public void testEncrypt()
        { 
            Assert.AreEqual(dataEncrypted, AESChiper.Encrypt(data, key));
        }

        [TestMethod]
        public void testDecrypt()
        {
            Assert.AreEqual(data, AESChiper.Decrypt(dataEncrypted, key));
        }
    }
}

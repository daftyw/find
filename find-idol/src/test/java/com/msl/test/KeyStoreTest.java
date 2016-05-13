package com.msl.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.*;
import org.springframework.security.saml.key.JKSKeyManager;

import javax.crypto.SecretKeyFactory;
import java.security.KeyStore;
import java.security.spec.EncodedKeySpec;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wind on 5/13/2016 AD.
 */
public class KeyStoreTest {

    @Test
    public void testJKS() throws Exception {

        String storePass = "drowssap";

        Map<String, String> passwords = new HashMap<>();

        passwords.put("pockey", "pocpassword");
        String defaultKey = "pockey";

        JKSKeyManager jksKeyManager = new JKSKeyManager
                (new UrlResource("file:///Users/wind/.keystore"), storePass, passwords, defaultKey);

        System.out.println(jksKeyManager.getDefaultCredential().getEntityId());
    }
}

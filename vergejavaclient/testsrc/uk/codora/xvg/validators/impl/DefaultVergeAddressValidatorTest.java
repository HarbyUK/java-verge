package uk.codora.xvg.validators.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.codora.xvg.validators.VergeAddressValidator;

public class DefaultVergeAddressValidatorTest {

    VergeAddressValidator validator;
    
    @Before
    public void setUp() throws Exception {
        validator = new DefaultVergeAddressValidator();
    }

    @Test
    public void validVergeAddressReturnsTrue() {
        assertTrue(validator.isValidVergeAddress("DEZP6kAGD7jRPQfjtSKN7kKBq6w33pDrkf"));
        assertTrue(validator.isValidVergeAddress("DCLWDv5q2MkMrFFoZUdczWD71FQ2AxEDNH"));
        assertTrue(validator.isValidVergeAddress("DLNoi2yqQzci2n5NoHXjKXFFTgiF255yNs"));
        assertTrue(validator.isValidVergeAddress("DAFW6m7JXC2oqkgeJ2aw8cDDUWN9oF1QML"));
        assertTrue(validator.isValidVergeAddress("DNSbK6MJgMejAkds3d3qffq8heQKEedXtF"));
        assertTrue(validator.isValidVergeAddress("DBD3aoXH2WCas7jV4h5jQv6WZnwwMtjV5y"));
    }
    
    @Test
    public void addressWithTooFewCharactersReturnsFalse() {
        assertFalse(validator.isValidVergeAddress("abc123"));
    }
    
    @Test
    public void addressWithInvalidCharactersReturnsFalse() {
        assertFalse(validator.isValidVergeAddress("#$%&*$*(*@#(#*((*$%(*(*%#(*#$%(#$*"));
    }
    
    @Test
    public void madeUpVergeLookingAddressesReturnsFalse() {
        assertFalse(validator.isValidVergeAddress("Dddddddddddddddddddddddddddddddddd"));
        assertFalse(validator.isValidVergeAddress("DPaE4vz8Q9mRp92uUKnB7n8Hd34GgJ87er"));
        assertFalse(validator.isValidVergeAddress("DT82Ne5JksFueGo9asCXrP3F2cvH1yUY4P"));
    }
    
    @Test
    public void validCryptoButInvalidVergeAddressReturnsFalse() {
        //Ethereum Address
        assertFalse(validator.isValidVergeAddress("0x87f63D09b49170bbd7A0bF81c86133D2824826be"));
        //Bitcoin Address
        assertFalse(validator.isValidVergeAddress("1LKrAqndXrzg9x2MRWMsyFecAyjXKTpxef"));
        //Litecoin Address
        assertFalse(validator.isValidVergeAddress("LTQCNnaEq5cgVQcsKAp7dfTBZW8fxSgJzF"));
    }
    
    @Test
    public void validStealthAddressReturnsTrue() {
        assertTrue(validator.isValidVergeAddress("smYk6Pt1J1C8vjhu8rxvVQht3DLhEbVw6iC94BFfRRpZmUVGZG4NHByYK1vJpKdYj8zApoTjRVcbckf63yxHBpyHnBodwgzD4K5hvi"));
        assertTrue(validator.isValidVergeAddress("smYi4qxo3D8fj6ngTnuqLjJaAVzPVHogeyUdy6ZyVBfVXKbWcStzeV9TmuC5j52ErtPymEEUcaCynto4grK9Yjz13cx5T3hwr85RxU"));
        assertTrue(validator.isValidVergeAddress("smYn1EQJ4KgUSNWzoiCPJgpEgan9JDb3zSMgn6UZgCiB2Yh9vtTr6Cj9iCo6Jz6o88rZWPv5cz75CKLrBnpu457xxqUvicd6YCDnpr"));
        assertTrue(validator.isValidVergeAddress("smYhZTSeiVYcgKgmCE6mpD4rgMScCVqTMKA99ZXu415K56We9Zz3ip6QjfvuooDQVdiJCcGpzARAz6pbgLGSdWnxWKGyHs8pe4u9kg"));
        assertTrue(validator.isValidVergeAddress("smYi3tUQwuqsMdHZRCmLRtMiEDQycHedJ4uibbhfB96KwtN22x3SvCX2sG4AYxjc6pruVsmyu3AXKbdRMSXBMQqCc1trHoBFjsXZgC"));
        assertTrue(validator.isValidVergeAddress("smYhjPqcvsze7ar6YEou4oDzPz3kn4Ky54FDvGFsrgKrfFNogVqb8ghQ22qk9hSwC3gyCti2XcAvuGHBfTo5DYSV1vJiMPL7DagVZq"));
    }
    
    @Test
    public void addressWithTooManyCharactersForStandardButTooFewCharactersForStealthReturnsFalse() {
        assertFalse(validator.isValidVergeAddress("smYhjPqcvsze7ar6YEou4oDzPz3kn4Ky54FDvGFsrgKrfFNogVqb8ghQ22qk9hSwC3gyCti2X"));
    }
    
    @Test
    public void stealthAddressWithInvalidCharactersReturnsFalse() {
        assertFalse(validator.isValidVergeAddress("#$%&*$*(*@#(#*((*$%(*(*%#(*#$%(#$*#$%&*$*(*@#(#*((*$%(*(*%#(*#$%(#$*#$%&*$*(*@#(#*((*$%(*(*%#(*#$%(#$"));
    }
    
    @Test
    public void madeUpVergeLookingStealthAddressesReturnsFalse() {
        assertFalse(validator.isValidVergeAddress("smYyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
        assertFalse(validator.isValidVergeAddress("smYhjPqcvUSNWzoiCPJgpEgan9JDb3zSMgn6UZgMScCVqTMKA99ZXu415K56We9Zz3ijfvuooDQVdiJCcGpzARACj9iCo6Jz6o88r"));
        assertFalse(validator.isValidVergeAddress("smYe9Zz3ipTnuqLjJaAVzPEou4oDzPz3kn4Ky54FD6We9Zz3ip6QjfvuooNogVqb8ghQ22qk9hSwC3gyCti2XcAvuGHBfT2Yh9vtT"));
    }
}

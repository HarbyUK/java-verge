package uk.codora.xvg.validators.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Base58;

import uk.codora.xvg.validators.VergeAddressValidator;

public class DefaultVergeAddressValidator implements VergeAddressValidator {
    
    private static final String ENCRYPTION_ALGORITHM_NAME = "SHA-256";

    @Override
    public boolean isValidVergeAddress(String address) {
        if(addressIsPotentialStealthAddress(address))
            return decodeAddressAndCompareChecksum(address, Arrays.asList(40, 0));
        if(addressLengthIsOutOfBounds(address))
            return false;
        return decodeAddressAndCompareChecksum(address, Arrays.asList(30));
    }
    
    private boolean addressIsPotentialStealthAddress(String address) {
        return address.length() >= 75;
    }

    private boolean addressLengthIsOutOfBounds(String address) {
        return address.length() < 26 || address.length() > 35;
    }

    private boolean decodeAddressAndCompareChecksum(String address, List<Integer> validAddressPrefix) {
        byte[] decodedAddress;
        try {
            decodedAddress = Base58.decode(address);
            byte[] hash1 = encryptSha256(Arrays.copyOfRange(decodedAddress, 0, decodedAddress.length - 4));
            byte[] hash2 = encryptSha256(hash1);
            return checksumIsValid(decodedAddress, hash2) && decodedAddressIsVergeAddress(decodedAddress, validAddressPrefix);
        } catch (AddressFormatException | NoSuchAlgorithmException e) {
            return false;
        }
    }

    private static byte[] encryptSha256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha256Digest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM_NAME);
        sha256Digest.update(data);
        return sha256Digest.digest();
    }

    private boolean checksumIsValid(byte[] decodedAddress, byte[] hash2) {
        return Arrays.equals(Arrays.copyOfRange(hash2, 0, 4), Arrays.copyOfRange(decodedAddress, decodedAddress.length - 4, decodedAddress.length));
    }

    private boolean decodedAddressIsVergeAddress(byte[] decodedAddress, List<Integer> validAddressPrefix) {
        for(int i = 0; i < validAddressPrefix.size(); i++)
            if(decodedAddress[i] != validAddressPrefix.get(i))
                return false;
        return true;
    }
    
}

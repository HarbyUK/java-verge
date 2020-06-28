package uk.codora.xvg.populators.response.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.codora.xvg.config.ConfigurationService;
import uk.codora.xvg.dto.JsonRpcErrorData;
import uk.codora.xvg.dto.JsonRpcResponseData;
import uk.codora.xvg.exception.JsonRpcResponseException;
import uk.codora.xvg.populators.Populator;

public class GetAccountRpcResponsePopulatorTest {
    
    private static final String ACCOUNT = "abc1234";
    private static final String ERROR_CODE = "12345";
    private static final String ERROR_MESSAGE = "Error Message";
    
    private Populator<JsonRpcResponseData, StringBuilder> populator;
    private JsonRpcResponseData jsonRpcResponseData;
    
    @Before
    public void setUp() throws ConfigurationException {
        instantiateRealObjects();
        prepareResponseData();
        MockitoAnnotations.initMocks(this);
    }

    private void instantiateRealObjects() {
        populator = new GetAccountRpcResponsePopulator();
        jsonRpcResponseData = new JsonRpcResponseData();
    }

    private void prepareResponseData() {
        List<String> result = new ArrayList<String>();
        result.add(ACCOUNT);
        jsonRpcResponseData.setResult(result);
    }
    
    @Test
    public void populatorReturnsAccountAsString() {
        StringBuilder account = new StringBuilder();
        populator.populate(jsonRpcResponseData, account);
        assertEquals(ACCOUNT, account.toString());
    }

    @Test
    public void populatorThrowsExceptionWhenJsonRpcResponseDataIsNull() {
        try {
            populator.populate(null, new StringBuilder());
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Populator should be throwing exception when StringBuilder is not instantiated.");
    }
    
    @Test
    public void populatorThrowsExceptionWhenStringBuilderIsNull() {
        try {
            populator.populate(jsonRpcResponseData, null);
        } catch(IllegalArgumentException e) {
            return;
        }
        fail("Populator should be throwing exception when StringBuilder is not instantiated.");
    }
    
    @Test
    public void populatorThrowsExceptionWhenErrorIsReturned() {
        jsonRpcResponseData.setResult(null);
        JsonRpcErrorData error = new JsonRpcErrorData();
        error.setCode(ERROR_CODE);
        error.setMessage(ERROR_MESSAGE);
        jsonRpcResponseData.setError(error);
        try {
            populator.populate(jsonRpcResponseData, new StringBuilder());
        } catch(JsonRpcResponseException e) {
            assertEquals(String.format("%s: %s", ERROR_CODE, ERROR_MESSAGE), e.getMessage());
            return;
        }
        fail("Populator should be throwing JsonRpcResponseException due to RPC data containing error.");
    }
    
    @Test
    public void populatorThrowsExceptionWhenBothResultAndErrorIsReturned() {
        JsonRpcErrorData error = new JsonRpcErrorData();
        error.setCode(ERROR_CODE);
        error.setMessage(ERROR_MESSAGE);
        jsonRpcResponseData.setError(error);
        try {
            populator.populate(jsonRpcResponseData, new StringBuilder());
        } catch(JsonRpcResponseException e) {
            assertEquals(String.format("%s: %s", ERROR_CODE, ERROR_MESSAGE), e.getMessage());
            return;
        }
        fail("Populator should be throwing JsonRpcResponseException due to RPC data containing error.");
    }
    
    @Test
    public void populatorThrowsExceptionWhenBothResultAndErrorAreNull() {
        jsonRpcResponseData.setResult(null);
        try {
            populator.populate(jsonRpcResponseData, new StringBuilder());
        } catch(JsonRpcResponseException e) {
            assertEquals("No result or error data returned with JSON-RPC response", e.getMessage());
            return;
        }
        fail("Populator should be throwing JsonRpcResponseException due to RPC data missing results and/or error.");
    }
}

package uk.codora.xvg.populators.request.impl;

import static org.junit.Assert.*;

import java.util.List;

import static org.mockito.Mockito.*;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import uk.codora.xvg.config.ConfigurationService;
import uk.codora.xvg.dto.JsonRpcRequestData;
import uk.codora.xvg.exception.InvalidVergeAddressException;
import uk.codora.xvg.incremental.RpcIdCounter;
import uk.codora.xvg.populators.Populator;
import uk.codora.xvg.populators.request.impl.GetAccountRpcRequestPopulator;
import uk.codora.xvg.validators.VergeAddressValidator;
import uk.codora.xvg.validators.impl.DefaultVergeAddressValidator;

public class GetAccountRpcRequestPopulatorTest {
    
    private static final String VERGE_ADDRESS = "DEZP6kAGD7jRPQfjtSKN7kKBq6w33pDrkf";
    private static final String INVALID_VERGE_ADDRESS = "1LKrAqndXrzg9x2MRWMsyFecAyjXKTpxef";
    private static final String ID = "1";
    private static final String JSON_RPC_VERSION_KEY = "json.rpc.version";
    private static final String JSON_RPC_VERSION = "2.0";
    private static final String METHOD_NAME = "getAccount";
    
    private Populator<String, JsonRpcRequestData> populator;
    private JsonRpcRequestData jsonRpcRequestData;
    private RpcIdCounter count;
    private VergeAddressValidator vergeAddressValidator;
    
    @Mock
    private ConfigurationService configurationService;
    
    @Mock
    private Configuration configuration;

    @Before
    public void setUp() throws ConfigurationException {
        populator = new GetAccountRpcRequestPopulator<String, JsonRpcRequestData>();
        jsonRpcRequestData = new JsonRpcRequestData();
        vergeAddressValidator = new DefaultVergeAddressValidator();
        setupMockData();
        resetRpcIdCount();
    }

    private void setupMockData() {
        MockitoAnnotations.initMocks(this);
        ((GetAccountRpcRequestPopulator<String, JsonRpcRequestData>)populator).setVergeAddressValidator(vergeAddressValidator);
        ((GetAccountRpcRequestPopulator<String, JsonRpcRequestData>)populator).setConfigurationService(configurationService);
        prepareMockScenarios();
    }

    private void prepareMockScenarios() {
        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(configuration.getString(JSON_RPC_VERSION_KEY, JSON_RPC_VERSION)).thenReturn(JSON_RPC_VERSION);
    }

    private void resetRpcIdCount() {
        count = RpcIdCounter.getInstance();
        count.reset();
    }
    
    @Test
    public void populatorSetsParametersCorrectly() {
        populator.populate(VERGE_ADDRESS, jsonRpcRequestData);
        
        List<String> params = jsonRpcRequestData.getParams();
        assertNotNull(params);
        assertEquals(1, params.size());
        assertEquals(VERGE_ADDRESS, params.get(0));
    }
    
    @Test
    public void populatorThrowsExceptionWhenStringIsNull() {
        try {
            populator.populate(VERGE_ADDRESS, null);
        } catch(IllegalArgumentException e) {
            return;
        }
        fail("Populator should be throwing exception when JsonRpcRequestData is not instantiated.");
    }
    
    @Test
    public void populatorThrowsExceptionWhenJsonRpcRequestDataIsNull() {
        try {
            populator.populate(null, new JsonRpcRequestData());
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Populator should be throwing exception when String is null.");
    }
    
    @Test
    public void populatorThrowsExceptionWhenAddressIsNotValid() {
        try {
            populator.populate(INVALID_VERGE_ADDRESS, jsonRpcRequestData);
        } catch (InvalidVergeAddressException e) {
            assertTrue(CollectionUtils.isEmpty(jsonRpcRequestData.getParams()));
            return;
        }
        fail("Populator should be throwing exception when address is invalid.");
    }
    
    @Test
    public void populatorSetsIdToOneAfterResetIdCounter() {
        populator.populate(VERGE_ADDRESS, jsonRpcRequestData);
        assertEquals(ID, jsonRpcRequestData.getId());
    }
    
    @Test
    public void populatorSetsValidJsonRpcVersion() {
        populator.populate(VERGE_ADDRESS, jsonRpcRequestData);
        assertEquals(JSON_RPC_VERSION, jsonRpcRequestData.getJsonRpcVersion());
    }
    
    @Test
    public void populatorSetsCorrectMethodName() {
        populator.populate(VERGE_ADDRESS, jsonRpcRequestData);
        assertEquals(METHOD_NAME, jsonRpcRequestData.getMethodName());
    }
}

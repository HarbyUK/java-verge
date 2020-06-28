package uk.codora.xvg.populators.request.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.beans.factory.annotation.Required;

import uk.codora.xvg.config.ConfigurationService;
import uk.codora.xvg.dto.JsonRpcRequestData;
import uk.codora.xvg.exception.InvalidVergeAddressException;
import uk.codora.xvg.incremental.RpcIdCounter;
import uk.codora.xvg.populators.Populator;
import uk.codora.xvg.populators.response.impl.PopulatorValidationHelper;
import uk.codora.xvg.validators.VergeAddressValidator;

public class GetAccountRpcRequestPopulator<S, T extends JsonRpcRequestData> implements Populator<S, T> {
    
    private static final String RPC_METHOD_NAME = "getAccount";
    private static final String JSON_RPC_VERSION_KEY = "json.rpc.version";
    private static final String JSON_RPC_VERSION_DEFAULT_VALUE = "2.0";
    
    private VergeAddressValidator vergeAddressValidator;
    private ConfigurationService configurationService;
    
    @Override
    public void populate(S source, T target) {
        validateSource(source, target);
        setParamsFromSource(source, target);
        setRpcId(target);
        setJsonRpcVersionFromConfigurationFile(target);
        target.setMethodName(RPC_METHOD_NAME);
    }

    private void validateSource(S source, T target) {
        PopulatorValidationHelper.validateArguments(source, target);
        if (!vergeAddressValidator.isValidVergeAddress((String)source))
            throw new InvalidVergeAddressException("Verge address provided is invalid. Will not call RPC Method getaccount.");
    }

    private void setParamsFromSource(S source, T target) {
        List<String> params = new ArrayList<>();
        params.add((String)source);
        target.setParams(params);
    }

    private void setRpcId(T target) {
        RpcIdCounter id = RpcIdCounter.getInstance();
        target.setId(String.valueOf(id.increment()));
    }

    private void setJsonRpcVersionFromConfigurationFile(T target) {
        Configuration configuration = configurationService.getConfiguration();
        target.setJsonRpcVersion(configuration.getString(JSON_RPC_VERSION_KEY, JSON_RPC_VERSION_DEFAULT_VALUE));
    }

    @Required
    public void setVergeAddressValidator(VergeAddressValidator vergeAddressValidator) {
        this.vergeAddressValidator = vergeAddressValidator;
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
}

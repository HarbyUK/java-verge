package uk.codora.xvg.populators.response.impl;

import uk.codora.xvg.dto.JsonRpcResponseData;
import uk.codora.xvg.populators.Populator;

public class GetAccountRpcResponsePopulator implements Populator<JsonRpcResponseData, StringBuilder> {

    @Override
    public void populate(JsonRpcResponseData source, StringBuilder target) {
        PopulatorValidationHelper.validateArguments(source, target);
        PopulatorValidationHelper.validateJsonRpcResponse(source);
        target.append(source.getResult().get(0));
    }
}

package uk.codora.xvg.populators.response.impl;

import org.springframework.util.CollectionUtils;

import uk.codora.xvg.dto.JsonRpcErrorData;
import uk.codora.xvg.dto.JsonRpcResponseData;
import uk.codora.xvg.exception.JsonRpcResponseException;

public class PopulatorValidationHelper {
    
    private PopulatorValidationHelper() {}

    public static <S, T> void validateArguments(S source, T target) {
        if (source == null)
            throw new IllegalArgumentException("Source provided is null when it should be instantiated");
        if (target == null)
            throw new IllegalArgumentException("Target provided is null when it should be instantiated");
    }
    
    public static <S extends JsonRpcResponseData> void validateJsonRpcResponse(S source) {
        JsonRpcErrorData error = source.getError();
        if (error != null)
            throw new JsonRpcResponseException(String.format("%s: %s", error.getCode(), error.getMessage()));
        if (CollectionUtils.isEmpty(source.getResult()))
            throw new JsonRpcResponseException("No result or error data returned with JSON-RPC response");
    }
}
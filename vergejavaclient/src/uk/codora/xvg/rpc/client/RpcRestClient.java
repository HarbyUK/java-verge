package uk.codora.xvg.rpc.client;

import uk.codora.xvg.dto.JsonRpcRequestData;
import uk.codora.xvg.dto.JsonRpcResponseData;

public interface RpcRestClient {
    
    public JsonRpcResponseData call(JsonRpcRequestData data);
    
}

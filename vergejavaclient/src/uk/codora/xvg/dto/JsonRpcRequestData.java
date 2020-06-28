package uk.codora.xvg.dto;

import java.util.List;

public class JsonRpcRequestData {
    
    private String jsonRpcVersion;
    private String methodName;
    private List<String> params;
    private String id;

    public String getJsonRpcVersion() {
        return jsonRpcVersion;
    }

    public void setJsonRpcVersion(String jsonRpcVersion) {
        this.jsonRpcVersion = jsonRpcVersion;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}

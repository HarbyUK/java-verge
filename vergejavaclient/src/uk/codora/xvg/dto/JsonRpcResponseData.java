package uk.codora.xvg.dto;

import java.util.List;

public class JsonRpcResponseData {

    private String jsonRpcVersion;
    private List<String> result;
    private JsonRpcErrorData error;
    private String id;

    public String getJsonRpcVersion() {
        return jsonRpcVersion;
    }

    public void setJsonRpcVersion(String jsonRpcVersion) {
        this.jsonRpcVersion = jsonRpcVersion;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public JsonRpcErrorData getError() {
        return error;
    }

    public void setError(JsonRpcErrorData error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

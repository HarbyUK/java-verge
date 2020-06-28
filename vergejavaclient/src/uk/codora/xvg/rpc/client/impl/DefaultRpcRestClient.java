package uk.codora.xvg.rpc.client.impl;

import org.apache.commons.configuration2.Configuration;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import uk.codora.xvg.config.ConfigurationService;
import uk.codora.xvg.dto.JsonRpcRequestData;
import uk.codora.xvg.dto.JsonRpcResponseData;
import uk.codora.xvg.rpc.client.RpcRestClient;

public class DefaultRpcRestClient implements RpcRestClient {
    
    private static final String DAEMON_SERVER_URI_KEY = "daemon.server.uri";
    private static final String DAEMON_SERVER_URI = "http://localhost:20102";
    
    RestTemplate restTemplate;
    ConfigurationService configurationService;

    @Override
    public JsonRpcResponseData call(JsonRpcRequestData data) {
        final Configuration configuration = configurationService.getConfiguration();
        HttpEntity<JsonRpcRequestData> entity = new HttpEntity<>(data);
        return restTemplate.postForObject(configuration.getString(DAEMON_SERVER_URI_KEY, DAEMON_SERVER_URI), entity, JsonRpcResponseData.class);
    }

    @Required
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
}

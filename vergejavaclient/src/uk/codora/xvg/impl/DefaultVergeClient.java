package uk.codora.xvg.impl;

import java.util.List;

import uk.codora.xvg.VergeClient;
import uk.codora.xvg.dto.JsonRpcRequestData;
import uk.codora.xvg.dto.JsonRpcResponseData;
import uk.codora.xvg.populators.Populator;
import uk.codora.xvg.rpc.client.RpcRestClient;

public class DefaultVergeClient implements VergeClient {

    private Populator<String, JsonRpcRequestData> getAccountRequestPopulator;
    private Populator<JsonRpcResponseData, StringBuilder> getAccountResponsePopulator;
    private RpcRestClient restClient;

    @Override
    public String getAddress(String account) {
        throw new UnsupportedOperationException("getAddress(String account) not available yet");
    }

    @Override
    public String getAccount(String address) {
        JsonRpcRequestData requestData = new JsonRpcRequestData();
        getAccountRequestPopulator.populate(address, requestData);
        StringBuilder account = new StringBuilder();
        getAccountResponsePopulator.populate(restClient.call(requestData), account);
        return account.toString();
    }

    @Override
    public String getNewAddress(String account) {
        throw new UnsupportedOperationException("getNewAddress(String account) not available yet");
    }

    @Override
    public List<String> listAccounts() {
        throw new UnsupportedOperationException("listAccounts() not available yet");
    }

    @Override
    public List<String> getTransaction(String transactionId) {
        throw new UnsupportedOperationException("getTransaction(String transactionId) not available yet");
    }

    @Override
    public void setAccount(String address, String account) {
        throw new UnsupportedOperationException("setAccount(String address, String account) not available yet");
    }

    @Override
    public long getBalance(String account, int minConf) {
        throw new UnsupportedOperationException("getBalance(String account, int minConf) not available yet");
    }

    @Override
    public String move(String accountFrom, String accountTo, long amount) {
        throw new UnsupportedOperationException(
                "move(String accountFrom, String accountTo, long amount) not available yet");
    }

    @Override
    public String send(String account, String toAddress, long amount) {
        throw new UnsupportedOperationException(
                "move(String accountFrom, String accountTo, long amount) not available yet");
    }

    @Override
    public List<String> validateAddress(String address) {
        throw new UnsupportedOperationException("validateAddress(String address) not available yet");
    }

}

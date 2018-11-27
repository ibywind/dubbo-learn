package cn.bywind.rpc.v_two;

import java.io.Serializable;

public class RpcResponse implements Serializable {
    private String requestId;
    private Object result;

    public RpcResponse(){

    }
    public RpcResponse(String requestId, Object result) {
        this.requestId = requestId;
        this.result = result;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

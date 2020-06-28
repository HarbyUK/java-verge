package uk.codora.xvg.incremental;

import java.util.concurrent.atomic.AtomicInteger;

public class RpcIdCounter {
    
    private static RpcIdCounter instance = new RpcIdCounter();
    private AtomicInteger count = new AtomicInteger();
    
    private RpcIdCounter(){}
    
    public static RpcIdCounter getInstance() {
        return instance;
    }
    
    public void reset() {
        count.set(0);
    }
    
    public int increment() {
        return count.incrementAndGet();
    }
    
    public int increment(int amount) {
        return count.addAndGet(amount);
    }
    
    public int getCount() {
        return count.get();
    }
    
}

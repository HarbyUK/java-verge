package uk.codora.xvg.populators;

public interface Populator<S, T> {
    
    public void populate(S source, T target);
    
}

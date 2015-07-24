package org.ar.stat4j.data;

/**
 * Created by Andriy Rymar on 24.07.15.
 */
public class MutableLong {
    private long value;

    public MutableLong(Long value){
        this.value = value;
    }

    public void add(Long value){
        this.value+=value;
    }

    public void sub(Long value){
        this.value-=value;
    }

    public void div(Long value){
        this.value/=value;
    }

    public void mux(Long value){
        this.value*=value;
    }

    public void setValue(Long value){
        this.value=value;
    }


    public long getValue(){
        return value;
    }
}

package org.ar.stat4j;


/**
 * Created by Andriy Rymar on 06.07.15.
 */
public class Stat4J {

    private static Stat4J instance;

    private Stat4J() {
    }

    public static Stat4J instance(){
        if(instance==null){
            synchronized (Stat4J.class){
                if(instance==null){
                    instance = new Stat4J();
                }
            }
        }
        return instance;
    }

    public void startTrack(String componentName, String pointName){
        System.out.println( componentName + " : " +pointName);
    }

    public void stopTrack(String componentName, String pointName){
        System.out.println( componentName + " : " +pointName);
    }

}

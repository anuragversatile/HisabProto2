package com.mycompany.hisabproto2;

/**
 * Created by Abhishek PC on 10-Dec-15.
 */
import java.util.*;
public class Edge<T> {
    private final Vertex<T> f;
    private final Vertex<T> t;
    private int amount;
    public Vertex<T> getFrom(){
        return f;
    }
    public Vertex<T> getTo(){
        return t;
    }
    public int getAmount(){
        return amount;
    }
    public boolean appendCost(int c){
        amount+=c;
        return true;
    }
    public Edge(Vertex<T> from,Vertex<T> to, int cost){
        f=from;
        t=to;
        amount=cost;
    }
}

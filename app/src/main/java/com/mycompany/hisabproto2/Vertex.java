package com.mycompany.hisabproto2;

/**
 * Created by Abhishek PC on 10-Dec-15.
 */
import java.util.*;
public class Vertex<T> {
    private String name=null;
    private List<Edge<T>> outGoing;
    private List<Edge<T>> inComing;
    int am;
    public String getName(){
        return name;
    }
    public List<Edge<T>> getOutGoing() {
        return outGoing;
    }
    public List<Edge<T>> getInComing(){
        return inComing;
    }
    public boolean addOutGoing(Edge<T> e){
        outGoing.add(e);
        return true;
    }
    public boolean addIncoming(Edge<T> e){
        inComing.add(e);
        return true;
    }
    public boolean setAm(int am){this.am=am; return true;}
    public int getAm(){return am;}
    public Vertex(String name){
        this.name=name;
        outGoing=new ArrayList<>();
        inComing=new ArrayList<>();
        am=0;
    }
    public Vertex(String name, int am){
        this.name=name;
        outGoing=new ArrayList<>();
        inComing=new ArrayList<>();
        this.am=am;
    }
}


package com.mycompany.hisabproto2;
import java.util.*;
import android.util.Log;
/**
 * Created by Abhishek PC on 10-Dec-15.
 */
public class Graph<T>{
    private final String name;
    private int pos;
    private List<Vertex<T>> vertices;//(+)outGoing,inComing(-)
    private List<Edge<T>> edges;//getFrom(),getTo()
    public int getPos(){return pos;}
    public Graph(String name){
        this.name=name;
        vertices=new ArrayList<>();
        edges=new ArrayList<>();
    }
    class Comp implements Comparator<Vertex>{
        public int compare(Vertex h1, Vertex h2){
            return h1.getAm()-h2.getAm();
        }
    }


    /*public int compare(HisabRecord h1, HisabRecord h2){
        return h1.getAmount()-h2.getAmount();
    }*/
    public boolean addToGraph(String from, String to, int amount){
        boolean fP=false,tP=false;
        Vertex<T> f=null,t=null;
        for(Vertex<T> v:vertices){
            if((v.getName()).equals(from)){
                fP=true;
                f=v;
            }
            if((v.getName()).equals(to)){
                tP=true;
                t=v;
            }
        }
        if(fP==true && tP==true){
            boolean edgeExists=false;
            for(Edge<T> e:edges){//todo-make it iterate in f.outGoing
                if((e.getFrom())==f &&(e.getTo())==t) {
                    e.appendCost(amount);
                    edgeExists=true;
                    return true;
                }
            }
            if(edgeExists==false){
                Edge<T> e=new Edge<>(f,t,amount);
                f.addOutGoing(e);
                t.addIncoming(e);
                edges.add(e);
                return true;
            }
        }
        else if(fP==true&& tP==false){
            t = new Vertex<>(to);
            Edge<T> e=new Edge<>(f,t,amount);
            vertices.add(t);
            edges.add(e);
            f.addOutGoing(e);
            t.addIncoming(e);
            return true;
        }
        else if(fP==false && tP==true){
            f=new Vertex<>(from);
            Edge<T> e=new Edge<>(f,t,amount);
            vertices.add(f);
            edges.add(e);
            f.addOutGoing(e);
            t.addIncoming(e);
            return true;
        }
        else{
            f=new Vertex<>(from);
            t=new Vertex<>(to);
            Edge<T> e=new Edge<>(f,t,amount);
            vertices.add(f);
            vertices.add(t);
            edges.add(e);
            f.addOutGoing(e);
            t.addIncoming(e);
            return true;
        }
        return false;
    }
    public List<Vertex> performCalculate(){
        List<Vertex> dO=new ArrayList<>();
        pos=0;
        for(Vertex v:vertices){
            String n=v.getName();
            int lent=0;
            int borrowed=0;
            List<Edge<T>> out,in;
            out=v.getOutGoing();
            in=v.getInComing();
            for(Edge e:out){
                lent+=e.getAmount();
                }
            for(Edge e:in){
                borrowed+=e.getAmount();
                }
            int am=lent-borrowed;
            v.setAm(am);
            if(am<0)
                pos++;
            dO.add(new Vertex(n,am));
        }
        Collections.sort(dO, new Comp());
        performDistribution(dO, pos);
        return dO;
    }
    public List<Vertex> performDistribution(List<Vertex> hr, int pos){//hr is sorted in ascending order.
        //distribute from negative to positive
        int len=hr.size()-1;
        int i=0;
        while(i<pos){
            Vertex a=hr.get(i);
            Vertex b=hr.get(len);
            int valA=Math.abs(a.getAm());
            int valB=b.getAm();
            if(valA<=valB){
                b.setAm(b.getAm()-valA);
                a.setAm(0);
                Edge e=new Edge(a,b,valA);
                a.addOutGoing(e);
                b.addIncoming(e);
                i++;
                if(b.getAm()==0)
                    len--;
            }
            else if(valA>valB){
                b.setAm(0);
                a.setAm(-valA + valB);
                Edge e=new Edge(a,b,valB);
                a.addOutGoing(e);
                b.addIncoming(e);
                len--;
            }
        }
        return hr;
    }
}


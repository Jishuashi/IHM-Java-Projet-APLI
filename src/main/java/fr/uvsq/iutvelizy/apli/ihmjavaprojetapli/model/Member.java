package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

import java.util.Optional;

public class Member {
    protected String name;
    protected String city;
    protected Member buyer;

    public Member(String pName, String pCity, Member pBuyer){
        Optional<Member> lBy = Optional.ofNullable(pBuyer);
        name = pName;
        city = pCity;
        buyer = lBy.isPresent() ? lBy.get() : null;
    }

    public String getName(){
        return name;
    }

    public String getCity(){
        return city;
    }

    public Member getBuyer(){
        return buyer;
    }

    public String toString(){
        if (buyer == null){
            return name + " " + city;
        }
        return name + " " + city + " -> " + buyer.getName() +" " + buyer.getCity();
    }

}

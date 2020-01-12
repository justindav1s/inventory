package org.jnd.microservices.model;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by justin on 13/10/2015.
 */
public class Payment {

    private String id;
    private String cardType = null;
    private String cardNum = null;
    private String cardName = null;
    private String expiry =null;
    private String cvc = null;

    public Payment() {}

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}

package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class HistoryChecked extends DomainEvent {

    private String UUID;

    public HistoryChecked(){
        super("shop.historyChecked");
    }

    public HistoryChecked(String UUID){
        super("shop.historyChecked");
        this.UUID = UUID;
    }

}

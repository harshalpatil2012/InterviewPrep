package com.lld.elevator;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Floor {
    private final int floorNumber;
    private final Set<String> authorizedCards;
    private final boolean isRestricted;

    public Floor(int floorNumber, boolean isRestricted) {
        this.floorNumber = floorNumber;
        this.isRestricted = isRestricted;
        this.authorizedCards = new HashSet<>();
    }

    public boolean canAccess(String cardId) {
        return !isRestricted || authorizedCards.contains(cardId);
    }

    public void addAuthorizedCard(String cardId) {
        authorizedCards.add(cardId);
    }
}
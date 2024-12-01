package kr.co.vacgom.persistence.global.util;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public class UuidUtility {

    public static UUID generateRandomUUID() {
        return UuidCreator.getTimeOrderedEpochPlus1();
    }
}

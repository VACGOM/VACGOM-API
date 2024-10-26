package kr.co.vacgom.persistence.member.constants;

import java.util.Arrays;

public enum ProviderType {
    KAKAO("kakao"),
    APPLE("apple"),
    DUMMY("dummy");

    private final String provider;

    ProviderType(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public static ProviderType from(String provider) {
        return Arrays.stream(values())
                .filter(type -> type.getProvider().equals(provider))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("todo"));
    }

    public boolean isKakao() {
        return this == KAKAO;
    }

    public boolean isApple() {
        return this == APPLE;
    }
}

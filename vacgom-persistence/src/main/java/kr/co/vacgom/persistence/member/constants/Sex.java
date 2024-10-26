package kr.co.vacgom.persistence.member.constants;

public enum Sex {
    MALE("M"),
    FEMALE("F");

    private final String value;

    Sex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Sex getSexByValue(String value) {
        for (Sex sex : Sex.values()) {
            if (sex.getValue().equals(value)) {
                return sex;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    public static Sex getSexBySsn(String ssn) {
        if (ssn.length() != 13 || !ssn.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Invalid SSN: " + ssn);
        }

        return switch (ssn.charAt(6)) {
            case '1', '3' -> MALE;
            case '2', '4' -> FEMALE;
            default -> throw new IllegalArgumentException("Invalid SSN gender identifier: " + ssn.charAt(6));
        };
    }
}

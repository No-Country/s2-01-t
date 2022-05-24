package fiados.com.models.enums;

public enum EnumRoles {

    USER("USER"),
    CLIENT("STAFF"),
    MERCHANT("MERCHANT");

    private static final String ROLE_PREFIX = "ROLE_";
    private final String name;

    EnumRoles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullRoleName() {
        return ROLE_PREFIX + name;
    }


}

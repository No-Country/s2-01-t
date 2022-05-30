package fiados.com.models.enums;

public enum EnumRoles {

    USER("USER"),
    CUSTOMER("CUSTOMER"),
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

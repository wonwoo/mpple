package ml.wonwoo.autoconfigure;

public enum MapperType {

    NONE(DefaultAutoConfiguration.class),
    MODELMAPPER(ModelMapperAutoConfiguration.class),
    DOZER(DozerAutoConfiguration.class);

    private final Class<?> configClass;

    MapperType(Class<?> configClass) {
        this.configClass = configClass;
    }

    public static MapperType getType(String configClass) {
        for (MapperType mapperType : values()) {
            if (mapperType.configClass != null) {
                if (mapperType.configClass.getName().equals(configClass)) {
                    return mapperType;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}

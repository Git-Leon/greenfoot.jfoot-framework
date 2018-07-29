package com.github.git_leon.jetpack_survivor_maven.system;

import java.util.Map;
import java.util.Properties;

public class JFProperty {
    private final Properties properties;
    private final JFootPropertyType propertyType;

    public JFProperty(String propertyName) {
        this(JFootPropertyType.getValueOf(propertyName));
    }

    public JFProperty(JFootPropertyType jFootPropertyType) {
        this.propertyType = jFootPropertyType;
        this.properties = new Properties();
    }

    public String get() {
        return propertyType.get(properties);
    }

    public JFProperty set(String propertyValue) {
        if (propertyValue != null)
            propertyType.set(properties, propertyValue);
        return this;
    }

    public JFootPropertyType getPropertyType() {
        return propertyType;
    }

    public Map<?, ?> getProperty() {
        return properties;
    }
}

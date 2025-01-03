/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the "Elastic License
 * 2.0", the "GNU Affero General Public License v3.0 only", and the "Server Side
 * Public License v 1"; you may not use this file except in compliance with, at
 * your election, the "Elastic License 2.0", the "GNU Affero General Public
 * License v3.0 only", or the "Server Side Public License, v 1".
 */

package org.elasticsearch.inference.configuration;

import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.xcontent.ConstructingObjectParser;
import org.elasticsearch.xcontent.ObjectParser;
import org.elasticsearch.xcontent.ParseField;
import org.elasticsearch.xcontent.ToXContentObject;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentParseException;
import org.elasticsearch.xcontent.XContentParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.elasticsearch.xcontent.ConstructingObjectParser.constructorArg;

/**
 * Represents a dependency within a connector configuration, defining a specific field and its associated value.
 * This class is used to encapsulate configuration dependencies in a structured format.
 */
public class SettingsConfigurationDependency implements Writeable, ToXContentObject {

    private final String field;
    private final Object value;

    /**
     * Constructs a new instance of SettingsConfigurationDependency.
     *
     * @param field The name of the field in the service dependency.
     * @param value The value associated with the field.
     */
    public SettingsConfigurationDependency(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public SettingsConfigurationDependency(StreamInput in) throws IOException {
        this.field = in.readString();
        this.value = in.readGenericValue();
    }

    private static final ParseField FIELD_FIELD = new ParseField("field");
    private static final ParseField VALUE_FIELD = new ParseField("value");

    private static final ConstructingObjectParser<SettingsConfigurationDependency, Void> PARSER = new ConstructingObjectParser<>(
        "service_configuration_dependency",
        true,
        args -> new SettingsConfigurationDependency.Builder().setField((String) args[0]).setValue(args[1]).build()
    );

    static {
        PARSER.declareString(constructorArg(), FIELD_FIELD);
        PARSER.declareField(constructorArg(), (p, c) -> {
            if (p.currentToken() == XContentParser.Token.VALUE_STRING) {
                return p.text();
            } else if (p.currentToken() == XContentParser.Token.VALUE_NUMBER) {
                return p.numberValue();
            } else if (p.currentToken() == XContentParser.Token.VALUE_BOOLEAN) {
                return p.booleanValue();
            } else if (p.currentToken() == XContentParser.Token.VALUE_NULL) {
                return null;
            }
            throw new XContentParseException("Unsupported token [" + p.currentToken() + "]");
        }, VALUE_FIELD, ObjectParser.ValueType.VALUE);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject();
        {
            builder.field(FIELD_FIELD.getPreferredName(), field);
            builder.field(VALUE_FIELD.getPreferredName(), value);
        }
        builder.endObject();
        return builder;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(FIELD_FIELD.getPreferredName(), field);
        map.put(VALUE_FIELD.getPreferredName(), value);
        return map;
    }

    public static SettingsConfigurationDependency fromXContent(XContentParser parser) throws IOException {
        return PARSER.parse(parser, null);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(field);
        out.writeGenericValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsConfigurationDependency that = (SettingsConfigurationDependency) o;
        return Objects.equals(field, that.field) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, value);
    }

    public static class Builder {

        private String field;
        private Object value;

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public SettingsConfigurationDependency build() {
            return new SettingsConfigurationDependency(field, value);
        }
    }
}

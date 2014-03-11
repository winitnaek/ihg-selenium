package com.ihg.selenium;

public class TestFailure {
    private String field;
    private String expected;
    private String actual;

    public TestFailure(String failedField, String expectedValue, String actualValue) {
        field = failedField;
        expected = expectedValue;
        actual = actualValue;
    }

    public String display() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(field);
        stringBuilder.append(": Expected<");
        stringBuilder.append(expected);
        stringBuilder.append(">, Actual<");
        stringBuilder.append(actual);
        stringBuilder.append(">.");
        return stringBuilder.toString();
    }
}

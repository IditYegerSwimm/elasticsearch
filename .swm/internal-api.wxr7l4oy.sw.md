---
title: Internal API
---
## Introduction

In this doc we will describe the API for {{API Name (e.g., sending Analytic Events)}} and how to use it correctly.

We use this API when {{use cases}}.

## API definition

<SwmSnippet path="/build-tools-internal/gradle/verification-metadata.xml" line="3">

---

&nbsp;

```xml
   <configuration>
      <verify-metadata>true</verify-metadata>
      <verify-signatures>false</verify-signatures>
   </configuration>
   <components/>
</verification-metadata>
```

---

</SwmSnippet>

## Simple usage

<SwmSnippet path="/build-tools-internal/gradle/verification-metadata.xml" line="2">

---

&nbsp;

```xml
<verification-metadata xmlns="https://schema.gradle.org/dependency-verification" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="https://schema.gradle.org/dependency-verification https://schema.gradle.org/dependency-verification/dependency-verification-1.2.xsd">
```

---

</SwmSnippet>

## Advanced usage: {{explain a scenario where this is needed}}

## Best practices and additional notes

When using this API, it is important to follow a few best practices and avoid some common mistakes.

<SwmSnippetPlaceholder>

Show an example of a best practice and explain why it is important to implement the API this way

</SwmSnippetPlaceholder>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBZWxhc3RpY3NlYXJjaCUzQSUzQUlkaXRZZWdlclN3aW1t" repo-name="elasticsearch"><sup>Powered by [Swimm](https://staging.swimm.cloud/)</sup></SwmMeta>

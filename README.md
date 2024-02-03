[![latest_version](https://img.shields.io/github/v/release/sodp5/retrofit-unit-adapter?label=latest_version)](https://github.com/sodp5/retrofit-unit-adapter/releases)

# UnitCallAdapterFactory
This is a library that prevents NPE(NullPointerException) from occurring when receiving a response through Retrofit
especially in cases where the response like 204 has no body.

## Usage

For instance, if you write a function as follows and receive a 204 response, it would typically lead to NPE
```kotlin
interface MyApiService {
    @GET("/path")
    suspend fun foo() // The return type is not declared as a 204 response is expected.
}
```

However, using this converter, it gets transformed into the Unit type, preventing the occurrence of NPE.
```kotlin
Retrofit.Builder()
    .baseUrl("Your Url")
    /* Add Here! */
    .addCallAdapterFactory(UnitCallAdapterFactory())
    .build()
```

## Dependency
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

```
dependencies {
    implementation "com.github.sodp5:retrofit-unit-adapter:$latest_version"
}
```

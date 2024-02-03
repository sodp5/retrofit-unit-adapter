# UnitCallAdapterFactory
This is a library that prevents NullPointerException from occurring when receiving a response through Retrofit
especially in cases where the response like 204 has no body.

## Using

For instance, if you write a function as follows and receive a 204 response, it would typically lead to NPE
```kotlin
interface MyApiService {
  @GET("/path")
  fun foo() // The return type is not declared as a 204 response is expected.
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


## `Retrofit` Explanation

### Why is Retrofit Useful?

1. **Type-Safety:** Retrofit uses annotations to define API endpoints and parameters, ensuring compile-time safety.

2. **Simplicity:** With just a few annotations, you can define an entire API endpoint. Retrofit handles the underlying 
HTTP communication, allowing you to focus on the app's business logic.

3. **Converter Flexibility:** Retrofit allows you to easily add converters (like Gson, Moshi, or Jackson) to handle serialization
and deserialization of data.

4. **Integration with OkHttp:** Retrofit uses OkHttp internally, so you get all the benefits of OkHttp's robustness, 
speed, and flexibility. Plus, you can customize the OkHttp client to suit your needs (e.g., by adding interceptors).

5. **Async Requests:** Retrofit provides built-in support for coroutines, RxJava, and other async mechanisms to make 
asynchronous requests cleaner and more manageable.

6. **Error Handling:** Retrofit can distinguish between different types of errors and helps handle them in a structured
manner.

7. **Customizability:** Retrofit allows for customization at every step, from headers and parameters to how responses are 
processed.

8. **Active Community & Maintenance:** Being one of the most popular libraries for Android networking, Retrofit has 
a vibrant community, ensuring regular updates and a plethora of resources.

### How does it work in this project?

Simple! We are creating the Retrofit object with a **Base URL** and a type **converter** for the object we will receive 
from our calls. This will then handle all the boiler plate for us and define our endpoint with simplicity, providing 
the above benefits.

If we go over the the `SomethingApi` class, we can see how this might work.

```
@GET("something")
suspend fun getSomethings(): SomethingResponseObject
```

This is the network call we are making, concatenated with the projects `baseURL`. For a more applicable example, we can
set the `baseURL` to the pokemon apis `https://pokeapi.co/api/v2/`, and then change the `SomethingApi` function.

```
@GET("pokemon?limit=15&offset=0")
suspend fun getPokemons(): PokemonResponseObject
```

This will return the response, where the parameters for the query determine that we want the first 15 pokemon. We also 
need to define our JSON return object according to the API data's schema, otherwise the application will crash. 
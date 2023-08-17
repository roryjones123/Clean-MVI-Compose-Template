## `Dependency Injection` Explanation

### What?

Often incredibly overcomplicated - dependency injection just means giving an object its instance variables without it 
having to construct them themselves.

### Why?

Loose coupling is the basic answer. Easy mocking (faking) for the purpose of testing another.

Separating concerns by not creating objects within an object but instead injecting them makes code a lot easier to 
maintain. These dependencies can then be reusied without having to write more code or re-instantiate the object.

### How?

In most modern applications you are going to find that dependency injection is done via a framework. You can do it 
manually, but this requires a lot of boiler plate code. Frameworks give a consistent way of injecting dependencies 
throughout the project. 

The frameworks usually also provide good tools for testing, are designed to be efficient, have advanced features 
like lazy instantiation, and so on.

### Which?

There are a few options for the framework you can use for DI.

- *Koin*
  - Written in Kotlin.
  - DSL (Domain-Specific Language) using keywords to create a clear syntax.
  - Code generated at runtime.
  - Multiplatform support. 
  - Crash at runtime / slower at runtime.

  *Hilt*
  - Written in Java
  - Annotations used instead of DSL to define/specify with regards to dependency injection.
  - Code generated at build time.
  - Larger package size.
  - Just for Android.
  - Crash at build time / slower at build time.
  
*Dagger*
  - Simply, this is Hilt with more boiler plate.
  - The main benefit is that it is more granular and provides more control of how the developer wants to inject.
  - Also not only for Android.
    - Crash at build time / slower at build time.

There's others like Kodein and Toothpick but we won't get into those now.

For this project we use Hilt. A lot of this is personal preference, but generally I find it easy to set up. 

### Hilt

We provide:

```
@HiltAndroidApp
class MainApplication : Application() {
```

And specify the apps name in the manifest. From there it's pretty simple. On our viewmodel we specify:

```
@Inject constructor(
  private val getHomepageDataUseCase: GetHomepageDataUseCase,
  savedStateHandle: SavedStateHandle,
  coinsInitialState: HomepageUiState
)
```

The `@Inject` annotation declares that we are going to inject our dependencies. We need to make sure these 
dependencies are provided within a module. Here is an example for the use case:

```
@Provides
fun provideGetHomepageData(
  PokemonRepository: PokemonRepository
): GetHomepageDataUseCase {
    return GetHomepageDataUseCase {
      getHomepageData(PokemonRepository)
  }
}
```

If all dependencies are provided, we are good to go.
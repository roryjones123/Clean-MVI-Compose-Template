## `Why Compose?`

Compose is the newest framework for building views in Android. There a few reasons that it is better than XML:

- Declarative, this means that we don't need to imperatively command the UI to change, it should change when the 
  state changes, which provides good interoperability with `Flows`, especially `StateFlows`.

- Kotlin based, providing a uniform language for the app, and allowing us to make use of features like extensions, 
  lambdas, transformations, and so on.

- Less code. It is less code than the XML, especially when setting up views such as lists. This makes it signicantly 
  easier to build views and understand them.

- Tooling like previews provided by Android studio.

- Theming, animations all provided out of the box and gives the user a large amount of control in creating their 
  user interface.

- Performance optimisations.

- Ease of testing. Incredibly easy to test individual composables as well as entire screens.

- Easy to create and maintain custom views, and reuse them in a way that reduces code bloat.

- Google will support it going forward ;) 

## `HomepageRoute`

This is a Composable function that serves as an entry point for the homepage feature in your app. For more 
information on how to build a user interface in compose I recommend checking out Google's documentation and having a 
go at making something from scratch.

- **Parameters**:
  - `viewModel`: An instance of `HomepageViewModel`. Defaults to an instance provided by Hilt's `hiltViewModel()` function.

- **Functionality**:
  - Collects the `uiState` from the `HomepageViewModel`.
  - Passes the state to the `HomepageScreen` Composable.

---

## `HomepageScreen`

This is an internal Composable function tasked with displaying the main content for the homepage based on the provided `HomepageUiState`.

- **Parameters**:
  - `uiState`: Represents the UI state for the homepage.
  - `onIntent`: A function to process user intents.

- **UI Components**:
  - **SnackbarHost**: Used to display snackbars. Snackbars appear temporarily at bottom of the screen to inform the 
    user.
  - **Floating Action Button (FAB)**: With a refresh icon, triggers the `GetHomepageData` intent on click.
  - **Content Area**: Changes based on the `uiState`:
    - Displays a loading indicator via `HomepageLoadingContent()` when loading.
    - Shows the list of content via `HomepageListContent()` if available.
    - Indicates an error through `HomepageErrorContent()` if any errors occur.

---
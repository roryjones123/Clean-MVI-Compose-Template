## `BaseViewModel` Class Explanation

### Purpose

The `BaseViewModel` is an **abstract class**, meaning it's intended to be subclassed. It provides a foundation for other ViewModel classes, laying out a standard structure for handling UI states and user intents.

### Generic Types

- `UI_STATE`: Represents the entire state of the UI. It's required to be `Parcelable` to enable easy saving and restoration.
- `PARTIAL_UI_STATE`: Represents an incremental or partial modification to the UI state.
- `INTENT`: Denotes the action or intention of the user when interacting with the UI.

### Properties

- `intentFlow`: A flow designed to gather and process user actions/intents.
- `continuousPartialStateFlow`: A flow intended for collecting continuous or repeated changes to the UI state.
- The remaining properties are internally used to ensure the flows begin listening at the correct time.

### Initialization Block (`init`)

Upon creation of an instance of this class (or its descendant), this block:

- Combines user intentions and continuous state alterations.
- Processes them to update the UI state.
- Logs errors if they arise.
- Saves the updated UI state for future reference.

### User Intents

- `userIntents()`: Transforms user intentions into partial UI states, using the `mapIntents` function. This function is expected to be detailed in the subclasses.
- `acceptIntent()`: Externally, entities like a UI controller can use this method to provide a fresh user intent.

### Continuous State Changes

- `continuousFlows()`: Listens for constant state changes.
- `observeContinuousChanges()`: Gives subclasses the ability to observe ongoing changes and feed them into the main flow.

### State Reducer

The `reduceUiState()` function is responsible for:

- Accepting the previously known full UI state and a partial modification.
- Merging them to produce a new, updated UI state.
- This function should be elaborated on by subclasses to specify how the state update should occur.

### Analogy for Beginners

Imagine the process of painting a picture:

- The `UI_STATE` is analogous to the current image you have on your canvas.
- Each brushstroke you make (i.e., every user action or system update) can be thought of as a `PARTIAL_UI_STATE` or an `INTENT`.

This class determines how each brushstroke (intent or partial state) modifies your main artwork (UI state). Instead of painting directly on the primary canvas, you first make your brushstrokes on a transparent overlay (`intentFlow` and `continuousPartialStateFlow`). Once satisfied, you transfer those strokes onto the main canvas, updating the `UI_STATE` in the process.

### Important Keywords

In order of appearance.

**Abstract Class**:
Abstract classes allow us to define the functionality of a base class, that can then be reused in 
numerous places. We make our `BaseViewModel` abstract so that it can be reused for all of our view models which:

- means we can keep the format of view models uniform
- means we don't need to recreate the boiler plate every time we make a view model.

**Parcelable**:
An object that is `Parcelable` can be serialised and de-serialsied (flattened into a data stream and reformed). This is 
useful for:

- passing data between components such as in `Intents`.
- recreating and preserving a state when activity is killed, (such as if there is a configuration change when phone 
  switches, or app backgrounded)

By ensuring that `UI_STATE` is `Parcelable`, it can be efficiently saved and restored using the `SavedStateHandle`.

It is more efficient and faster than `Serialisable`, another way of providing serialisation. 

**SavedStateHandle**
This is just a map that saves the state and persists it through aforementioned states where the activity is killed. 
We save our `UI_STATE` here and use it in the constructor of the view model.

**MutableSharedFlow**
A `MutableSharedFlow` allows for multiple subscribers, and allows us to handle backpressure if needed. 

**Why Flows?**
Why not livedata? Flows offer a range of useful operators that allow us to handle the data as it comes 
through to the presentation layer. This includes the aforementioned backpressure handling, error handling, 
transformations.

For simple projects this is not always necessary due to the potential complexity of setup/learning.

**CompletableDeferred**

CompletableDeferred<Unit>() provides a way to coordinate or synchronize asynchronous actions within the ViewModel.
Here they are being used to ensure that the flows have started. For example:

`continuousPartialStateFlowListenerStarted.await()` is called to check the flow started before emitting values.

**ViewModelScope.launch()**

The view model scope is a coroutine scope that is cleared when the view model is cleared. A coroutine scope is the 
context in which we launch the coroutine. All the coroutines are cancelled within the scope when the scope is cleared. 

A coroutine is a is a lightweight thread-like structure that allows you to perform asynchronous and non-blocking operations 
in a more readable and concise way.

When calling launch we are "firing and forgetting", in that we do not need to await for any value as opposed to async(),
a different launch function.

**Flows collect()**

A terminal flow operator. This means it is the final call that consumes the upstream data, ready for it to be acted 
upon (in our case, passed to the `SavedStateHandle`, to be presented in the UI). 

**Flows scan()**

Scan takes each value, transforms it, but produces each intermediate value, as opposed to fold() which only produces the final value. 

**Flows flatMapConcat()**

Transforms values emitted from original flow, and flattens them one after the other, returning another flow. This is 
different from flatMapMerge() which does the same, but concurrently. 

**Flows emit()**

Values upstream are "emitted" downstream where they can be collected.

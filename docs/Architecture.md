## `Architecture` Explanation

## Architecture

Architecture is how we structure and design our codebase. For a simple app, architecture shouldn't be a significant detail. 
Scalable architecture can involve a lot of boiler plate and setup - and can be difficult to understand at first.

### SOLID Principles in Coding

SOLID represents a set of five design principles aimed to make software designs more understandable, flexible, 
and maintainable. They are fundamental in object-oriented design and programming.

#### 1. **S - Single Responsibility Principle (SRP)**
- **What**: A class should have one, and only one, reason to change. This implies a class should only have one job or 
- responsibility.
- **Why**:
  - Ensures a class tackles only one concern.
  - Makes it more robust and easier to refactor.
  - Enhances understandability and maintainability.

#### 2. **O - Open/Closed Principle (OCP)**
- **What**: Software entities (like classes, modules, functions) should be open for extension but closed for modification.
- **Why**:
  - Allows new features or behaviors without altering existing code.
  - Reduces risk of introducing new bugs in established features.

#### 3. **L - Liskov Substitution Principle (LSP)**
- **What**: Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of 
- the program.
- **Why**:
  - Ensures derived classes are genuine subtypes of their base classes.
  - Leads to more reliable and coherent class hierarchies.

#### 4. **I - Interface Segregation Principle (ISP)**
- **What**: No client should be compelled to depend on interfaces it doesn't use.
- **Why**:
  - Promotes creation of narrow interfaces specific to each client.
  - Makes the system more modular and easier to refactor.

#### 5. **D - Dependency Inversion Principle (DIP)**
- **What**: High-level modules shouldn't depend on low-level ones. Both should depend on abstractions. Moreover, 
- abstractions shouldn't rely on details, but details should depend on abstractions.
- **Why**:
  - Fosters decoupling, making the system more modular.
  - Facilitates easier changes and maintenance.
  - Serves as a basis for techniques like Dependency Injection.

#### Overall Importance of SOLID:
- **Maintainability**: Easier extension, refactoring, or modification of code.
- **Reduced Bugs**: Minimizes chances of bugs during changes.
- **Scalability**: Accommodates a more scalable architecture.
- **Reusability**: Offers more decoupled and well-defined components.
- **Collaboration**: Aids developers in understanding, reviewing, and collaborating efficiently.

In summary, SOLID provides guidelines leading to cleaner, more robust, and more readable code.

### Clean Android Architecture
Clean Architecture is a software design philosophy that focuses on the separation of concerns, maintainability, 
testability, and scalability. There are several approaches to Clean, and different words used to describe the same 
things.

In this application, we have split our project into three modules.

**Core**: This contains code that is shared across the project. Such as our manual `CoroutineScopes` and `Retrofit` 
**App**: This contains our Android Framework components, our entry points to the application. 
**Features**: Contains all of our codes for our features within the app. This is the bulk of the code.

Features is broken down into three layers to adhere to clean philosophies. 

**data**: Data sources, DAO's, implementations of repositories, everything for fetching and storing data.
**domain**: Platform agnostic business logic. Use cases and interfaces for repositories.
**presentation**: The user interface layer, such as the compose code and the view models.

Each layer has its own data objects and mappers (for mapping said data objects). This can end up being a bit more setup
and boiler plate, but is necessary for separating concerns, as each layer's data object may have different needs.

### Architectural Patterns Explained

The definitions are fluid, to say the least - but this is what I know.

---

#### **1. MVC (Model-View-Controller)**:

- **Model**:
  - Represents the data and the business logic of the application.
  - Responsible for retrieving, storing, and processing data.

- **View**:
  - Represents the UI of the application.
  - Displays the data that the Model provides and sends user commands to the Controller.

- **Controller**:
  - Acts as an interface between Model and View.
  - Takes the user input from the View, processes it (with potential updates to the Model), and returns the display output to the View.

  **In Essence**: User interacts with the View, which sends commands to the Controller, which updates the Model, and 
  the View then displays the updated data.

---

#### **2. MVP (Model-View-Presenter)**:

- **Model**:
  - Similar to MVC's model, it represents data and business logic.

- **View**:
  - Represents the UI.
  - Usually implemented by the activities or fragments.
  - Sends user actions to the Presenter and displays the data provided by the Presenter.

- **Presenter**:
  - Contains the UI business logic for the View.
  - Retrieves data from the Model, processes it, and updates the View.

  **In Essence**: User interacts with the View, which sends actions to the Presenter, which retrieves/updates the Model 
- and then updates the View accordingly.

---

#### **3. MVVM (Model-View-ViewModel)**:

- **Model**:
  - As in MVC and MVP, represents the core data and business logic.

- **View**:
  - Represents the UI and is usually passive.
  - Displays what the ViewModel tells it to.

- **ViewModel**:
  - The main point of the MVVM pattern.
  - Represents the data for the View.
  - Doesn't know about the View that uses it.

  **In Essence**: The View observes the ViewModel. When data in the ViewModel changes, the View reacts by updating itself. 
  The ViewModel fetches and updates the Model.

---

#### **4. MVI (Model-View-Intent)**:

- **Model**:
  - Represents the application's data and business logic.

- **View**:
  - Represents the UI.
  - Displays states and sends user intentions or actions.

- **Intent**:
  - Represents the intention or desire to change a state.
  - A result of a user action, like a button click.

  **In Essence**: User interacts with the View, creating Intents. These Intents guide how the Model should change. 
  Once the Model is updated, it sends the updated state to the View to reflect the changes.


### Why MVI?

1) It works well with compose and more generally with reactive programming.
2) Unidirectional data flow makes logic easier to debug and follow.
3) Concerns are separated well, and logic is encapsulated.
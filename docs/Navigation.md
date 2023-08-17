## `Navigation` Explanation

NAVIGATION NOT CURRENTLY DEMOED IN APP

### Compose Navigation

In this project we will be using Google's Compose navigation framework. This is not a particularly beloved framework,
and comes with several built-in features that might be considered, by some, to be steps back.

With XML navigation, we had the ability to pass primitive types and serialised objects by passing them within a bundle.
This allowed type checking out-of-the-box, and the ability to pass more complex objects - which is currently lacking 
from Compose Navigation.

While it is annoying to lose that functionality, this basic app won't have need of it - and in simple use cases we 
can just pass an id or a basic primitive with the built-in compose navigation.

### How does it work?

Like XML, we have a navigation host and a navigation controller. The host is an empty container that contains the 
controller that is responsible for switching the view.

Unlike XML navigation, where actions and destinations are contained with a navigation graph - compose navigation 
uses something more akin to web-based URL searching.

Here is our way of defining the routes, where the `route` is the "URL"

```
sealed class NavigationDestination(
    val route: String,
) {
    data object Homepage : NavigationDestination("homepageDestination")
    data object Back : NavigationDestination("navigationBack")
}
```

Which we reference when we inject the `NavigationHomepageFactory`

```
builder.composable(Homepage.route) {
    HomepageRoute()
}
```

If we wanted to add parameters, ie a userId:

```
composable(
    "home/{userId}",
    arguments = listOf(navArgument("userId") { type = NavType.StringType })
) 
```

Which would require changing our original implementation.

The `NavigationHost` is in the highest layer of our Compose layout and can be seen in the `MainActivity`, where we 
pass our navigation factories but also the starting route, where we want to begin.

### What other options are there?

Several third party solutions have been created to deal with potential issues with the Compose navigation framework, 
and to add benefits. A couple are:

*Compose Destinations* 

Uses annotation processing to generate a lot of the boiler plate required for setting up a safer Compose navigation. 

*AppyX*

Bumble's node base navigation that deals with the aforementioned type-safety issue, but also allows us to move 
navigation logic away from the UI to follow proper separation, unit test navigation, and some easily implementable 
animations for navigation.
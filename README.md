# Project Template: MVI + Compose/flows + Clean Architecture

Hello! After slogging through the setup of a dozen projects, I decided to create a template (with hopes of expanding into a series of templates) to speed up this process. This is still very much a WIP.

This template features **MVI + Compose/flows + Clean Architecture**, accompanied by detailed explanations for the design decisions and the associated code. For an in-depth dive, please visit the `docs` folder.

## How to Use

To use this project template, follow these easy steps to fetch network data from an API in an MVI app:

1. **Choose Your API**: Find an API you'd like to fetch data from.
2. **API Base URL**: Copy its base URL and paste in the designated spot:
    ```kotlin
    defaultConfig {
        buildConfigField("String", "SOMETHING_API_URL", “PASTE URL HERE”)
    }
    ```
3. **Request Setup**: Structure your request in the `SomethingAPI` class.
4. **Endpoint Configuration**: At this point, you should have a proper endpoint setup.
5. **Data Handling**:
   - In `SomethingResponse`, devise a class to manage the API data response. I often resort to [QuickType](https://quicktype.io/) for crafting a data object based on a schema.
   - Revise our mappers and objects, ensuring we don't disclose unnecessary data.
   - Stream this down to the presentation layer.
   - Make the presentation object reflecting the provided data. For a hands-on example, explore the `Pokemon` branch.

💡 **Note**: When transforming this into an official project or interview submission, make sure to:
- Rename all instances of “Something” or “Somethings”.
- Change the app name.
- Adjust all package names.
- In essence, personalize it!

❗ Although I admire the audacity of attempting to wing it, I strongly urge you (especially if this serves as an interview template) to go through the `docs`. This will improve your grasp on the concepts, functions, keywords, etc., that build this project.

## TODOs:

- [ ] Add local cache.
- [ ] Create a two-screen version of the app with enhanced UI.
- [ ] Incorporate tests.
- [ ] Branch for SQLDelight.
- [ ] Branch for RxJava.
- [ ] Branch for alternate navigation.

---

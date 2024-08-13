# Zentale

An Android App that turns toy photos into educational stories for adults and kids with the help of Gemini AI. 

This app was built for the [Gemini API Developer Competition](https://ai.google.dev/competition/).

<img src="https://github.com/ivandrag/zentale-android/blob/main/thumbnail.png" width="1000">

### Demo

If you want to try the app, you can download it from this [Google Drive Link](https://drive.google.com/file/d/10hb6Zcy_XEyWcBH-1bNa2FtObnJlkAeq/view?usp=sharing).
Currently, the app is not available on the Play Store, but you can download it from the above Google Drive link.

### Known issues

* Sign in with Google is not working on One Plus devices.

### How it works

1. The user takes a photo of a toy or adds a photo from the gallery.
   * The user can choose a language for the story from English, Spanish, and Romanian.
2. The app uploads the photo to Firebase Storage and gets a public URL.
3. The app sends the public URL to the Zentale API.
4. The Zentale API returns a success message and the story is saved by BE in Firestore.
5. The app fetches the story from Firestore and displays it.

## Screens

### Login
The app uses Google Sign-In to authenticate the user by using One Tap Sign In.

### Home
The Home screen shows a list of demo stories that the user can choose from.

### Library
The Library screen shows a list of private stories ( stories generated by the user ) that the user can choose from.

### Create Story
The Create Story screen allows the user to take a photo of a toy or add a photo from gallery, choose a language for the story, and send it to the Zentale API to generate a story.

### Story
The Story screen shows the stories that the user generated.

### Profile
The Profile screen shows the user's profile information and allows the user to logout.

## Building

1. Clone the git repository
2. Run `./gradlew build`

## Libraries

- [Firebase][firebase]
- [Compose][compose]
- [Navigation Component][navigation]
- [Retrofit][retrofit]
- [OkHttp][okhttp]
- [Koin][koin]
- [Kotlin-Serialization][kotlin-serialization]
- [Coroutines][coroutines]
- [CameraX][camerax]
- [Lottie][lottie]
- [Material3][material3]
- [Coil][coil]
- [OneTapCompose][onetapcompose]
- [Accompanist Permissions][accompanist]
- [GoogleFonts][googlefonts]

[firebase]: https://firebase.google.com/
[navigation]: https://developer.android.com/guide/navigation
[retrofit]: https://square.github.io/retrofit/
[okhttp]: https://square.github.io/okhttp/
[koin]: https://insert-koin.io/
[compose]: https://developer.android.com/jetpack/compose
[coroutines]: https://kotlinlang.org/docs/coroutines-overview.html
[camerax]: https://developer.android.com/training/camerax
[lottie]: https://airbnb.io/lottie/
[material3]: https://m3.material.io/
[kotlin-serialization]: https://github.com/Kotlin/kotlinx.serialization
[coil]: https://github.com/coil-kt/coil
[onetapcompose]: https://github.com/stevdzasan/OneTapCompose
[accompanist]: https://google.github.io/accompanist/permissions/
[googlefonts]: https://developer.android.com/reference/kotlin/androidx/compose/ui/text/googlefonts/package-summary

## Architecture
This project uses Model-View-ViewModel with Clean Architecture.

## Project Structure
**di** - dependency injection package where we created:

appModule : responsible for some Singleton classes like OkHttp, Retrofit, FirebaseAuth, FirebaseFirestore, FirebaseStorage
domainModule : responsible for domain classes : repositories, managers
dataModule : responsible for datasource
viewModelModule : responsible for view model classes

**domain** - this package is split into several other packages and is the "middle-man" between the presentation layer and the data layer.

* repository ( interface ) : middle class in our domain layer that it is responsible to pass data to the right data source. A mediator in our code.
  Usually, it s the class where we should do most of our business logic.
* model : entities that are sent to our presentation layer.

**data**
* datasource : holds classes related to remote and local work. For example, remote data sources have our api client or firestore classes injected.
* repository ( implementation ) : implementation of our domain inside the data layer, that it is responsible to pass data from and to presentation from data.

**networking** - contains implementation details about our http client and interceptors.
**presentation** - contains a single activity with a navigation component. each screen is represented by a Compose Screen and a ViewModel class. Each viewModels exposes a state MutableStateFlow and/or event SharedFlow. We try to avoid having any kind of logic inside our fragments and everything is passed onto our viewModel which then decides what to do with the data.

## Things TODO:
* Modularization.
* Add text to image conversion when a story is created. This could be done by the Gemini API once that will be available again to the public.
* Add text to voice conversion. I noticed Eleven Labs has a very good API for this, but I'm not sure if it was allowed in the Gemini Competition
* Public library of stories created by the community.
* Add "Did you know?" learnings on story loading. ( This takes time and at least the user can learn something new while waiting )
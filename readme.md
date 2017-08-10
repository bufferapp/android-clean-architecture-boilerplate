# Android Clean Architecture Boilerplate

Welcome 👋 We hope this boilerplate is not only helpful to other developers, but also that it helps to enducate in the area of architecture.We created this boilerplate for a few reasons:

1. To experiment with modularisation
2. To share some approaches to clean architecture, especially as we've been [talking a lot about it](https://academy.realm.io/posts/converting-an-app-to-use-clean-architecture/)
3. To use as a starting point in future projects where clean architecture feels appropriate

It is written in 100% kotlin with both UI and Unit tests - we will also be keeping this up-to-date as libraries change!

### Disclaimer

Note: The use of clean architecture may seem over-complicated for this sample project. However, this allows us to keep the amount of boilerplate code to a minimum and also demonstrate the approach in a simpler form.

Clean Architecture will not be appropriate for every project, so it is down to you to decide whether or not it fits your needs 🙂

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
* [Dagger 2 (2.11)](https://github.com/google/dagger)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
* [Robolectric](http://robolectric.org/)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android O ([API 26](https://developer.android.com/preview/api-overview.html))
* Latest Android SDK Tools and build tools.

## Architecture

The architecture of the project follows the principles of Clean Archicture. Here's how the sample project implements it:

![architecture](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/architecture.png?raw=true)

The sample app when runs will show you a simple list of all the Bufferoos (Buffer team members!).

<img src="https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/device_screenshot.png" alt="Drawing" style="width: 10px;"/>

Let's look at each of teh architecture layers and the role each one plays :)

#### User Interface

This layer makes use of the Android Framework and is used to create all of our UI components to display inside of the [Browse Activity](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/browse/BrowseActivity.kt). The layer receives its data from the Presentation layer and when retrieved, the received models are mapped using the [Bufferoo Mapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/mapper/BufferooMapper.kt) so that the model can be mapped to this layers interpretation of the Bufferoo instance, which is the [BufferooViewModel](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/model/BufferooViewModel.kt).




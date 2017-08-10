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
<p align="center">
<img src="https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/device_screenshot.png" alt="Drawing" style="width: 10px;"/>
</p>

Let's look at each of teh architecture layers and the role each one plays :)

#### User Interface

This layer makes use of the Android Framework and is used to create all of our UI components to display inside of the [Browse Activity](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/browse/BrowseActivity.kt). The layer receives its data from the Presentation layer and when retrieved, the received models are mapped using the [Bufferoo Mapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/mapper/BufferooMapper.kt) so that the model can be mapped to this layers interpretation of the Bufferoo instance, which is the [BufferooViewModel](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/model/BufferooViewModel.kt). The Activity makes use of the [BrowseContract](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/browse/BrowseBufferoosContract.kt) to enable communication to and from the presenter

#### Presentation

This layers responsibilty is to handle the presentation of the User Interface, but at the same time knowing nothing about the user interface itself. This layer has no dependance on the Android Framework, it is a pure Kotlin module. Each Presenter class that is created implements the [Presenter](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/BasePresenter.kt) interface defined within an instance of a contract - in this case the BrowseContract(https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/browse/BrowseBufferoosContract.kt), which also contains an interface for the [View](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/BaseView.kt) interface.

When a Presenter is constructed an instance of this View is passed in, this view is then used and the presenter is set for it using the implemented [setPresenter()](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/browse/BrowseBufferoosPresenter.kt#L15) call.

The presenters use an instance of a [SingleUseCase](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/interactor/SingleUseCase.kt) from the Domain layer to retrieve data, note here that there is no direct name reference to the UseCase that we are using - we do inject an instance of the [GetBufferoos](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/interactor/browse/GetBufferoos.kt) UseCase however.

The presenter receives data from the Domain layer in the form of a [Bufferoo](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/model/BufferooView.kt). These instances are mapped to instance of this layers model, which is a BufferooView using the [BufferooMapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/mapper/BufferooMapper.kt).

#### Domain

The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the Presentation layer.

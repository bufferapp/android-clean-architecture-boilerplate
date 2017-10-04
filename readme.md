[![Build Status](https://travis-ci.org/bufferapp/android-clean-architecture-boilerplate.svg?branch=master)](https://travis-ci.org/bufferapp/android-clean-architecture-boilerplate) [![codecov](https://codecov.io/gh/bufferapp/android-clean-architecture-boilerplate/branch/master/graph/badge.svg)](https://codecov.io/gh/bufferapp/android-clean-architecture-boilerplate) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/278fa00f492d48a288ab64188d15fb61)](https://www.codacy.com/app/hitherejoe/android-clean-architecture-boilerplate?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bufferapp/android-clean-architecture-boilerplate&amp;utm_campaign=Badge_Grade) 

# Android Clean Architecture Components Boilerplate


Note: This is a fork of our original [Clean Architecture Boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate), except in this repo we have switched out the MVP approach found in the presentation layer to now use ViewModels from the Android Architecture Components Library.
The caching layer now also uses Room.


Welcome 👋 We hope this boilerplate is not only helpful to other developers, but also that it helps to educate in the area of architecture. We created this boilerplate for a few reasons:

1. To experiment with modularisation
2. To experiment with the Android Architecture Components
3. To share some approaches to clean architecture, especially as we've been [talking a lot about it](https://academy.realm.io/posts/converting-an-app-to-use-clean-architecture/)
4. To use as a starting point in future projects where clean architecture feels appropriate

It is written 100% in Kotlin with both UI and Unit tests - we will also be keeping this up-to-date as libraries change!

### Disclaimer

Note: The use of clean architecture may seem over-complicated for this sample project. However, this allows us to keep the amount of boilerplate code to a minimum and also demonstrate the approach in a simpler form.

Clean Architecture will not be appropriate for every project, so it is down to you to decide whether or not it fits your needs 🙂

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
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

The architecture of the project follows the principles of Clean Architecture. Here's how the sample project implements it:

![architecture](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/art/architecture.png?raw=true)

The sample app when run will show you a simple list of all the Bufferoos (Buffer team members!).
<p align="center">
<img src="https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/device_screenshot.png" alt="Drawing" style="width: 10px;"/>
</p>

Let's look at each of the architecture layers and the role each one plays :)

![architecture](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/ui.png?raw=true)

### User Interface

This layer makes use of the Android Framework and is used to create all of our UI components to display inside of the [Browse Activity](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/browse/BrowseActivity.kt). The layer receives its data from the Presentation layer and when retrieved, the received models are mapped using the [Bufferoo Mapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/mapper/BufferooMapper.kt) so that the model can be mapped to this layer's interpretation of the Bufferoo instance, which is the [BufferooViewModel](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/9a1308c42c0c882fc724a0e579ee1ce4d454f961/mobile-ui/src/main/java/org/buffer/android/boilerplate/ui/model/BufferooViewModel.kt). The Activity makes use of the [BrowseBufferoosViewModel](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/browse/BrowseBufferoosViewModel.kt) to retrieve data.

### Presentation

This layer's responsibility is to handle the presentation of the User Interface, but at the same time knows nothing about the user interface itself. This layer has no dependence on the Android Framework, it is a pure Kotlin module. Each ViewModel class that is created implements the ViewModel class found within the Architecture components library. This ViewModel can then be used by the UI layer to communicate with UseCases and retrieve data. The [BrowseBufferoosViewModel](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/browse/BrowseBufferoosViewModel.kt) returns an instance of a [Resource](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/data/Resource.kt) which contains data that can be used by the UI - this includes the [ResourceState](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/data/ResourceState.kt), data to be used by the UI and a message if required (for error states).

The ViewModels use an instance of a [FlowableUseCase](https://github.com/bufferapp/clean-architecture-components-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/interactor/FlowableUseCase.kt) from the Domain layer to retrieve data. Note here that there is no direct name reference to the UseCase that we are using - we do inject an instance of the [GetBufferoos](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/interactor/browse/GetBufferoos.kt) UseCase, however.

The ViewModel receives data from the Domain layer in the form of a [Bufferoo](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/model/BufferooView.kt). These instances are mapped to instance of this layers model, which is a BufferooView using the [BufferooMapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/presentation/src/main/java/org/buffer/android/boilerplate/presentation/mapper/BufferooMapper.kt).

### Domain

The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the Presentation layer. In our case, we define a [GetBufferoos](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/interactor/browse/GetBufferoos.kt) - this use case handles the subscribing and observing of our request for data from the BufferooRepository interface. This UseCase extends the [SingleUseCase](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/interactor/ObservableUseCase.kt) base class - therefore we can reference it from outer layers and avoid a direct reference to a specific implementation.

The layer defines the [Bufferoo](https://github.com/bufferapp/android-clean-architecture-boilerplate/tree/master/domain/src/main/java/org/buffer/android/boilerplate/domain/model) class but no mapper. This is because the Domain layer is our central layer, it knows nothing of the layers outside of it so has no need to map data to any other type of model.

The Domain layer defines the [BufferooRepository](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/domain/src/main/java/org/buffer/android/boilerplate/domain/repository/BufferooRepository.kt) interface which provides a set of methods for an external layer to implement as the UseCase classes use the interface when requesting data.

![architecture](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/art/data.png?raw=true)

### Data

The Data layer is our access point to external data layers and is used to fetch data from multiple sources (the cache and network in our case). It contains an implementation of the BufferooRepository, which is the [BufferooDataRepository](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/BufferooDataRepository.kt). To begin with, this class uses the [BufferooDataStoreFactory](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/source/BufferooDataStoreFactory.kt) to decide which data store class will be used when fetching data - this will be either the [BufferooRemoteDataStore](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/source/BufferooRemoteDataStore.kt) or the [BufferooCacheDataStore](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/source/BufferooCacheDataStore.kt) - both of these classes implement the [BufferooDataStore](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/repository/BufferooDataStore.kt) repository so that our DataStore classes are enforced.

Each of these DataStore classes also references a corresponding [BufferooCache](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/repository/BufferooCache.kt) and [BufferooRemote](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/repository/BufferooRemote.kt) interface, which is used when requesting data from an external data source module.

This layers data model is the [BufferooEntity](https://github.com/bufferapp/android-clean-architecture-boilerplate/tree/master/data/src/main/java/org/buffer/android/boilerplate/data/model). Here the [BufferooMapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/mapper/BufferooMapper.kt) is used to map data to and from a Bufferoo instance from the domain layer and BufferooEntity instance from this layer as required.

### Remote

The Remote layer handles all communications with remote sources, in our case it makes a simple API call using a Retrofit interface. The [BufferooRemoteImpl](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/remote/src/main/java/org/buffer/android/boilerplate/remote/BufferooRemoteImpl.kt) class implements the [BufferooRemote](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/data/src/main/java/org/buffer/android/boilerplate/data/repository/BufferooRemote.kt) interface from the Data layer and uses the [BufferooService](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/remote/src/main/java/org/buffer/android/boilerplate/remote/BufferooService.kt) to retrieve data from the API.

The API returns us instances of a [BufferooModel](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/remote/src/main/java/org/buffer/android/boilerplate/remote/model/BufferooModel.kt) and these are mapped to BufferooEntity instance from the Data layer using the [BufferooEntityMapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/remote/src/main/java/org/buffer/android/boilerplate/remote/mapper/BufferooEntityMapper.kt) class.

### Cache

The Cache layer handles all communication with the local database which is used to cache data. 

The data model for this layer is the [CachedBufferoo](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/cache/src/main/java/org/buffer/android/boilerplate/cache/model/CachedBufferoo.kt) and this is mapped  to and from a BufferooEntity instance from the Data layer using the [BufferooEntityMapper](https://github.com/bufferapp/android-clean-architecture-boilerplate/blob/master/cache/src/main/java/org/buffer/android/boilerplate/cache/mapper/BufferooEntityMapper.kt) class.

## Conclusion

We will be happy to answer any questions that you may have on this approach, and if you want to lend a hand with the boilerplate then please feel free to submit an issue and/or pull request 🙂

Again to note, use Clean Architecture where appropriate. This is example can appear as over-architectured for what it is - but it is an example only. The same can be said for individual models for each layer, this decision is down to you. In this example, the data used for every model is exactly the same, so some may argue that "hey, maybe we don't need to map between the presentation and user-interface layer". Or maybe you don't want to modularise your data layer into data/remote/cache and want to just have it in a single 'data' module. That decision is down to you and the project that you are working on 🙌🏻

## Thanks

A special thanks to the authors involved with these two repositories, they were a great resource during our learning!

- https://github.com/android10/Android-CleanArchitecture

- https://github.com/googlesamples/android-architecture

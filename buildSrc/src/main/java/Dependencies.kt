object Versions {
    //Android
    const val androidBuildToolsVersion = "27.0.0"
    const val androidMinSdkVersion = 15
    const val androidTargetSdkVersion = 26
    const val androidCompileSdkVersion = 26
    
    //Libraries
    const val kotlinVersion = "1.2.21"
    const val butterKnifeVersion = "7.0.1"
    const val recyclerViewVersion = "21.0.3"
    const val rxJavaVersion = "2.0.2"
    const val rxKotlinVersion = "2.1.0"
    const val rxAndroidVersion = "2.0.1"
    const val javaxAnnotationVersion = "1.0"
    const val javaxInjectVersion = "1"
    const val gsonVersion = "2.8.1"
    const val okHttpVersion = "3.8.1"
    const val androidAnnotationsVersion = "21.0.3"
    const val retrofitVersion = "2.3.0"
    const val roomVersion = "1.0.0-alpha6"
    const val supportLibraryVersion = "26.0.1"
    const val timberVersion = "4.5.1"
    const val glideVersion = "4.0.0"
    const val daggerVersion = "2.11"
    const val glassfishAnnotationVersion = "10.0-b28"
    
    //Testing
    const val robolectricVersion = "3.4.2"
    const val jUnitVersion = "4.12"
    const val assertJVersion = "3.8.0"
    const val mockitoVersion = "1.9.5"
    const val dexmakerVersion = "1.0"
    const val espressoVersion = "3.0.0"
    const val testingSupportLibVersion = "0.1"
    const val mockitoKotlinVersion = "1.5.0"
    const val mockitoAndroidVersion = "2.8.47"
    const val androidSupportRunnerVersion = "1.0.0"
    const val androidSupportRulesVersion = "1.0.0"
    const val dexmakerMockitoversion = "2.2.0"
    const val runnerVersion = "0.5"
}


object DomainDependencies {
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlinVersion}"
}

object DomainTestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    
}

object PresentationDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlinVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    val androidAnnotations = "com.android.support:support-annotations:${Versions.androidAnnotationsVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
}


object PresentationTestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
}

object DataDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlinVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    val androidAnnotations = "com.android.support:support-annotations:${Versions.androidAnnotationsVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
}


object DataTestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
}

object CacheDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlinVersion}"
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    val androidAnnotations = "com.android.support:support-annotations:${Versions.androidAnnotationsVersion}"
    val roomRuntime = "android.arch.persistence.room:runtime:${Versions.roomVersion}"
    val roomCompiler = "android.arch.persistence.room:compiler:${Versions.roomVersion}"
    val roomRxJava = "android.arch.persistence.room:rxjava2:${Versions.roomVersion}"
}


object CacheTestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
    val roomTesting = "android.arch.persistence.room:testing:${Versions.roomVersion}"
    val archTesting = "android.arch.core:core-testing:${Versions.roomVersion}"
    val supportRunner = "com.android.support.test:runner:${Versions.androidSupportRunnerVersion}"
    val supportRules = "com.android.support.test:rules:${Versions.androidSupportRulesVersion}"
}


object RemoteDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlinVersion}"
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    val androidAnnotations = "com.android.support:support-annotations:${Versions.androidAnnotationsVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
}


object RemoteTestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val supportRunner = "com.android.support.test:runner:${Versions.androidSupportRunnerVersion}"
    val supportRules = "com.android.support.test:rules:${Versions.androidSupportRulesVersion}"
}


object MobileUiDependencies {
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlinVersion}"
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    val androidAnnotations = "com.android.support:support-annotations:${Versions.supportLibraryVersion}"
    val androidSupportV4 = "com.android.support:support-v4:${Versions.supportLibraryVersion}"
    val androidSupportV13 = "com.android.support:support-v13:${Versions.supportLibraryVersion}"
    val appCompatV7 = "com.android.support:appcompat-v7:${Versions.supportLibraryVersion}"
    val supportRecyclerView = "com.android.support:recyclerview-v7:${Versions.supportLibraryVersion}"
    val supportDesign = "com.android.support:design:${Versions.supportLibraryVersion}"
    val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    val glassfishAnnotation = "org.glassfish:javax.annotation:${Versions.glassfishAnnotationVersion}"
}

object MobileUiTestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val supportRunner = "com.android.support.test:runner:${Versions.androidSupportRunnerVersion}"
    val supportRules = "com.android.support.test:rules:${Versions.androidSupportRulesVersion}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroidVersion}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espressoVersion}"
    val espressoIntents = "com.android.support.test.espresso:espresso-intents:${Versions.espressoVersion}"
    val espressoContrib = "com.android.support.test.espresso:espresso-contrib:${Versions.espressoVersion}"
    val androidRunner = "com.android.support.test:runner:${Versions.runnerVersion}"
    val androidRules = "com.android.support.test:rules:${Versions.runnerVersion}"
}

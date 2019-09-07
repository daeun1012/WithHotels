object ApplicationId {
    val id = "io.github.daeun1012.withhotels"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0.0"
}

object Versions {
    val gradle = "3.5.0"

    val compileSdk = 29
    val minSdk = 21
    val targetSdk = 29

    val appcompat = "1.0.2"
    val material = "1.1.0-alpha09"
    val constraintlayout = "1.1.3"
    val fragment = "1.1.0-alpha02"

    val junit = "4.12"
    val testRunner = "1.2.0"
    val espresso = "3.2.0"

    val kotlin = "1.3.41"
    val ktx = "1.0.2"
    val lifecycle = "2.2.0-alpha03"
    val navigation = "2.0.0"
    val viewPager2 = "1.0.0-beta03"
    val recyclerview = "1.1.0-alpha05"

    val room = "2.1.0"

    val retrofit = "2.3.0"
    val okhttpLogging = "3.9.0"

    val timber = "4.7.1"
    val stetho = "1.5.0"

    val paging = "2.1.0"
    val swipeRefreshLayout = "1.0.0"

    val glide = "4.9.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val lifecycle = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" //implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-alpha03'

    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    val room = "androidx.room:room-runtime:${Versions.room}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    val pagingRxJava = "androidx.paging:paging-rxjava2:${Versions.paging}"
    val pagingKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Compilers {
    val room = "androidx.room:room-compiler:${Versions.room}"
    val lifecycle = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val glide = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
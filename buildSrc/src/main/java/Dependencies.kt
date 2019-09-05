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
    val lifecycle = "2.0.0"
    val navigation = "2.0.0"
    val viewPager2 = "1.0.0-beta03"
    val recyclerview = "1.1.0-alpha05"

    val room = "2.1.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val lifecycleViewModel = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    val room = "androidx.room:room-runtime:${Versions.room}"
}

object Compilers {
    val room = "androidx.room:room-compiler:${Versions.room}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
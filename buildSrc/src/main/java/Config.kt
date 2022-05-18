object Config {

    object Core {
        private const val version = "1.7.0"

        const val core = "androidx.core:core-ktx:$version"
    }

    object Compose {
        const val version = "1.1.1"

        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material:material:$version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
    }

    object ML_Kit {
        private const val version = "18.0.0"

        const val textRecognition =
            "com.google.android.gms:play-services-mlkit-text-recognition:$version"
    }

    object LifeCycle {
        private const val version = "2.4.1"

        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
    }

    object Koin {
        private const val version = "3.1.6"

        const val android = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }

    object Coil {
        private const val version = "2.1.0"

        const val coil = "io.coil-kt:coil:$version"
        const val compose = "io.coil-kt:coil-compose:$version"
    }
}

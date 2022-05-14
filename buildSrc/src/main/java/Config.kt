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
    }

    object ML_Kit {
        private const val version = "18.0.0"

        const val textRecognition =
            "com.google.android.gms:play-services-mlkit-text-recognition:$version"
    }
}

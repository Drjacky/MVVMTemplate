# Retrofit
-keepattributes Signature
-keepattributes *Annotation*

# kotlinx-serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class app.web.drjackycv.core.**$$serializer { *; }
-keepclassmembers class app.web.drjackycv.core.** {
    *** Companion;
}
-keepclasseswithmembers class app.web.drjackycv.core.** {
    kotlinx.serialization.KSerializer serializer(...);
}

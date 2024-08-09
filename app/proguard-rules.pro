# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Print the mapping file
-printmapping mapping.txt

# Keep attributes
-keepattributes Signature
-keepattributes Annotation
-keepattributes *Annotation*

-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 # R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# Keep Firebase and Firestore classes
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Keep Firestore model classes and their fields
-keepclassmembers class com.bedtime.stories.kids.zentale.** {
    @com.google.firebase.firestore.PropertyName <fields>;
}
-keepnames class com.bedtime.stories.kids.zentale.** { *; }
-keepclassmembers class com.bedtime.stories.kids.zentale.** {
    <fields>;
    <methods>;
}

# Keep Retrofit classes
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keepattributes Signature
-keepattributes Exceptions

# Keep OkHttp classes
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Keep Okio classes
-dontwarn okio.**
-keep class okio.** { *; }
-keep interface okio.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Keep FirebaseAuth classes
-keep class com.google.firebase.auth.** { *; }
-dontwarn com.google.firebase.auth.**

# Keep Kotlin Serialization classes
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**

# Ensure that the serializers are preserved
-keepclassmembers class ** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Ensure that Kotlin metadata is preserved
-keepclasseswithmembers class kotlin.Metadata {
    public <methods>;
}
-keepattributes *Annotation*

# Keep data model classes and their fields for Kotlin Serialization
-keepclassmembers class com.bedtime.stories.kids.zentale.data.model.** {
    private <fields>;
    public <methods>;
}
-keepnames class com.bedtime.stories.kids.zentale.data.model.** { *; }

# Keep default implementation fields for Kotlin
-keepclassmembers class **Kt$DefaultImpls {
    <fields>;
}

# Keep constructors and private fields for data models
-keepclassmembers class com.bedtime.stories.kids.zentale.data.model.** {
    public <init>(...);
    private <fields>;
}

# Keep Retrofit HTTP annotations
-keepclassmembers class ** {
    @retrofit2.http.* <methods>;
}
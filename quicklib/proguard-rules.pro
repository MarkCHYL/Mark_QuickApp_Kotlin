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
#Retrofit会对泛型参数进行反思。InnerClasses需要使用Signature 和
#EnclosingMethod是使用InnerClasses所必需的。
-keepattributes Signature，InnerClasses，EnclosingMethod
#优化时保留服务方法参数。
-keepclassmembers，allowshrinking，allowobfuscation interface * {
    @ retrofit2.http 。*  < methods > ;
}
#忽略使用的标注为构建工具。
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#忽略JSR 305注释以嵌入可空性信息。
-dontwarn javax.annotation 。**

#由NoClassDefFoundError守护try / catch  ，仅在类路径上使用。
-dontwarn kotlin.Unit

#顶-只能由科特林使用级功能。
-dontwarn retrofit2。- KotlinExtensions


-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}
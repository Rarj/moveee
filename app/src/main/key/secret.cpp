#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_Secret_apiKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "33ef4526082667e26fd77c6773694d55";
    return env->NewStringUTF(api_key.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_Secret_youtubeKey(JNIEnv *env, jobject thiz) {
    std::string youtube_key = "AIzaSyCQGncohTCtCckNwTqmkZNdx35rhTNvE_0";
    return env->NewStringUTF(youtube_key.c_str());
}
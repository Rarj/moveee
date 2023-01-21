#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_core_Secret_apiKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "33ef4526082667e26fd77c6773694d55";
    return env->NewStringUTF(api_key.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_core_Secret_youtubeKey(JNIEnv *env, jobject thiz) {
    std::string youtube_key = "AIzaSyCQGncohTCtCckNwTqmkZNdx35rhTNvE_0";
    return env->NewStringUTF(youtube_key.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_Secret_youtubeKey(JNIEnv *env, jobject thiz) {
    std::string base_url = "https://api.themoviedb.org/3/";
    return env->NewStringUTF(base_url.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_Secret_baseURLImage(JNIEnv *env, jobject thiz) {
    std::string base_url_image = "https://image.tmdb.org/t/p/w500";
    return env->NewStringUTF(base_url_image.c_str());
}
package emperorfin.android.githubusers.ui.util

import androidx.annotation.DrawableRes
import emperorfin.android.githubusers.R


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 29th October, 2024.
 */




enum class ColoredLanguage(val title: String, @DrawableRes val icon: Int) {

    KOTLIN("Kotlin", R.drawable.ic_circle_lang_kotlin),

    JAVA("Java", R.drawable.ic_circle_lang_java),

    JAVA_SCRIPT("JavaScript", R.drawable.ic_circle_lang_java_script),

    PYTHON("Python", R.drawable.ic_circle_lang_python),

    CSS("CSS", R.drawable.ic_circle_lang_css),

    PHP("PHP", R.drawable.ic_circle_lang_php),

    RUBY("Ruby", R.drawable.ic_circle_lang_ruby),

    C_PLUS_PLUS("C++", R.drawable.ic_circle_lang_c_plus_plus),

    C("C", R.drawable.ic_circle_lang_c),

    GO("Go", R.drawable.ic_circle_lang_go),

    SWIFT("Swift", R.drawable.ic_circle_lang_swift),

    OTHER("Other", R.drawable.ic_circle_lang_other);

    companion object {

        fun of(name: String?) = when (name) {
            KOTLIN.title -> KOTLIN
            JAVA.title -> JAVA
            JAVA_SCRIPT.title -> JAVA_SCRIPT
            PYTHON.title -> PYTHON
            CSS.title -> CSS
            PHP.title -> PHP
            RUBY.title -> RUBY
            C_PLUS_PLUS.title -> C_PLUS_PLUS
            C.title -> C
            GO.title -> GO
            SWIFT.title -> SWIFT
            OTHER.title -> OTHER
            else -> OTHER
        }
    }
}
package com.example.daggerbikinpusing.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.daggerbikinpusing.di.AppComponent

fun Context.hideKeyboard(view: View) {
  val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.baseApplication() = application as BaseApplication

val Activity.appComponent: AppComponent
  get() = baseApplication().appComponent()

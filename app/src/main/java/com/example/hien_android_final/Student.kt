package com.example.hien_android_final

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Student(val username: String, val password: String) : Parcelable {
}
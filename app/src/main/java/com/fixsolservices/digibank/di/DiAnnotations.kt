package com.fixsolservices.digibank.di

import com.bumptech.glide.signature.AndroidResourceSignature
import javax.inject.Qualifier

//application name
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationNameString

//Activity name
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityNameString
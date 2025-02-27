package com.example.navigation.di

import com.example.navigation.api.AppNavigator
import com.example.navigation.api.AppNavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    single<AppNavigator> {
        AppNavigatorImpl()
    }
}
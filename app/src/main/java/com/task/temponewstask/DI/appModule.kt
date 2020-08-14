package com.task.temponewstask.DI

import com.task.temponewstask.model.repository.*
import com.task.temponewstask.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {

    factory<NewsRepository> { NewsRepositoryImpl() }
    viewModel { NewsViewModel(get()) }

}


@JvmField
val testModule = module {

    factory<NewsRepository> { NewsRepositoryDummy() }
    viewModel { NewsViewModel(get()) }

}
package ru.boronin.mvvmposts.base

import androidx.lifecycle.ViewModel
import ru.boronin.mvvmposts.injection.component.DaggerViewModelInjector
import ru.boronin.mvvmposts.injection.component.ViewModelInjector
import ru.boronin.mvvmposts.injection.module.NetworkModule
import ru.boronin.mvvmposts.ui.post.PostListViewModel

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}
package ru.boronin.mvvmposts.injection.component

import dagger.Component
import ru.boronin.mvvmposts.injection.module.NetworkModule
import ru.boronin.mvvmposts.ui.post.PostListViewModel
import javax.inject.Singleton

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}
package ru.boronin.mvvmposts.ui.post

import ru.boronin.mvvmposts.base.BaseViewModel
import ru.boronin.mvvmposts.network.PostApi
import javax.inject.Inject

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
class PostListViewModel : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
}
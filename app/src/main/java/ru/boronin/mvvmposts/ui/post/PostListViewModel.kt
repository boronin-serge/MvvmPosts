package ru.boronin.mvvmposts.ui.post

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.boronin.mvvmposts.base.BaseViewModel
import ru.boronin.mvvmposts.network.PostApi
import javax.inject.Inject

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
class PostListViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    // region private

    private fun loadPosts(){
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe({
                onRetrievePostListSuccess()
            }, {
                onRetrievePostListError()
            })
    }

    private fun onRetrievePostListStart() {

    }

    private fun onRetrievePostListFinish() {

    }

    private fun onRetrievePostListSuccess() {

    }

    private fun onRetrievePostListError() {

    }

    // endregion
}
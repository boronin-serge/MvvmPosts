package ru.boronin.mvvmposts.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.boronin.mvvmposts.R
import ru.boronin.mvvmposts.base.BaseViewModel
import ru.boronin.mvvmposts.model.Post
import ru.boronin.mvvmposts.model.PostDao
import ru.boronin.mvvmposts.network.PostApi
import javax.inject.Inject

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val postListAdapter: PostListAdapter = PostListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    // region private

    private fun loadPosts(){
        subscription = Observable.fromCallable { postDao.all }
            .concatMap { dbPostList ->
                if(dbPostList.isEmpty())
                    postApi.getPosts().concatMap { apiPostList ->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else {
                    Observable.just(dbPostList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe({ result ->
                onRetrievePostListSuccess(result)
            }, {
                onRetrievePostListError()
            })
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        postListAdapter.updatePostList(postList.toMutableList())
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    // endregion
}
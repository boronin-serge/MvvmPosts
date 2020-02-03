package ru.boronin.mvvmposts.ui.post

import androidx.lifecycle.MutableLiveData
import ru.boronin.mvvmposts.base.BaseViewModel
import ru.boronin.mvvmposts.model.Post

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
class PostViewModel : BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private var listener: (() -> Unit)? = null


    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun setRemoveListener(listener: () -> Unit) {
        this.listener = listener
    }

    fun getPostTitle(): MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String>{
        return postBody
    }

    fun onClick() {
        listener?.invoke()
    }
}
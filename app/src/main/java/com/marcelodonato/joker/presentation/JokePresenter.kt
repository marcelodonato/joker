package com.marcelodonato.joker.presentation


import com.marcelodonato.joker.data.JokeCallback
import com.marcelodonato.joker.data.JokeRemoteDataSource
import com.marcelodonato.joker.model.Joke
import com.marcelodonato.joker.view.JokeFragment


class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
) : JokeCallback {

    fun findBy(categoryName: String) {
        view.showProgress()
        dataSource.findBy(categoryName, this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}
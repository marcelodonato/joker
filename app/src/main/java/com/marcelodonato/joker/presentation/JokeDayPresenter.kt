package com.marcelodonato.joker.presentation


import com.marcelodonato.joker.data.JokeCallback
import com.marcelodonato.joker.data.JokeDayRemoteDataSource
import com.marcelodonato.joker.model.Joke
import com.marcelodonato.joker.view.JokeDayFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallback {

    fun findRandom() {
        view.showProgress()
        dataSource.findRandom(this)
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



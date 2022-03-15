package com.marcelodonato.joker.data

import com.marcelodonato.joker.model.Joke

interface JokeCallback {

    fun onSuccess(response: Joke)

    fun onError(response: String)

    fun onComplete()
}
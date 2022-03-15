package com.marcelodonato.joker.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar


import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.marcelodonato.joker.R
import com.marcelodonato.joker.model.Joke
import com.marcelodonato.joker.presentation.JokePresenter

class JokeFragment : Fragment() {

    companion object {
        const val CATEGORY_KEY = "category"
    }

    private lateinit var progressbar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imgView: ImageView

    private lateinit var presenter: JokePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_KEY)

        if (categoryName != null) {

            activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName
            progressbar = view.findViewById(R.id.progress_bar)
            textView = view.findViewById(R.id.txt_joke)

            view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
                presenter.findBy(categoryName)
            }

            presenter.findBy(categoryName)
        }
    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
    }

    fun showProgress() {
        progressbar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressbar.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
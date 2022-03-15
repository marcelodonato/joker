package com.marcelodonato.joker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelodonato.joker.R
import com.marcelodonato.joker.model.Category
import com.marcelodonato.joker.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment() {

    private lateinit var presenter: HomePresenter
    private val adapter = GroupieAdapter()
    private lateinit var progressbar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressbar = view.findViewById(R.id.progress_bar)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if(adapter.itemCount == 0){
            presenter.findAllCategories()
        }


        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(JokeFragment.CATEGORY_KEY, categoryName)

            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }
    }

    fun showCategories(response: List<Category>) {
        val categories = response.map { CategoryItem(it) }
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
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
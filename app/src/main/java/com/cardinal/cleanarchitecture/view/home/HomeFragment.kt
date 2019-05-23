package com.cardinal.cleanarchitecture.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cardinal.cleanarchitecture.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(this, Observer { stateChanged(it) })

        viewModel.loadData()

        recipeAdapter = RecipeAdapter {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        rvRecipes.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = recipeAdapter
        }
    }

    private fun stateChanged(state: HomeViewModel.HomeState) {
        when (state) {
            is HomeViewModel.HomeState.Success -> renderList(state.recipes)
            is HomeViewModel.HomeState.Loading -> pbLoading.visibility = View.VISIBLE
            is HomeViewModel.HomeState.Error -> showError()
        }
    }

    private fun showError() {
        pbLoading.visibility = View.GONE
        Snackbar.make(rootLayout, "ERROR!!!", Snackbar.LENGTH_LONG).show()
    }

    private fun renderList(recipes: List<RecipeUiItem>) {
        recipeAdapter.items = recipes
        pbLoading.visibility = View.GONE
    }

}
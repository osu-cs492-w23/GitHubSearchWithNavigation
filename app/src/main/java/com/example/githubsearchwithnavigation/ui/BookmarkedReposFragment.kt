package com.example.githubsearchwithnavigation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchwithnavigation.R
import com.example.githubsearchwithnavigation.data.GitHubRepo

class BookmarkedReposFragment : Fragment(R.layout.bookmarked_repos_fragment) {
    private val viewModel:BookmarkedReposViewModel by viewModels()
    private val repoListAdapter = GitHubRepoListAdapter(::onGitHubRepoClick)
    private lateinit var bookmarkedReposRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
         * Set up RecyclerView
         */
        bookmarkedReposRV = view.findViewById(R.id.rv_bookmarked_repos)
        bookmarkedReposRV.layoutManager = LinearLayoutManager(requireContext())
        bookmarkedReposRV.setHasFixedSize(true)
        bookmarkedReposRV.adapter = repoListAdapter

        viewModel.bookmarkedRepos.observe(viewLifecycleOwner) {
            repoListAdapter.updateRepoList(it)
        }
    }

    /**
     * This method is passed into the RecyclerView adapter to handle clicks on individual items
     * in the list of GitHub repos.  When a repo is clicked, a new activity is launched to view
     * details about that repo.
     */
    private fun onGitHubRepoClick(repo: GitHubRepo) {
        val directions = BookmarkedReposFragmentDirections.navigateToRepoDetail(repo)
        findNavController().navigate(directions)
    }
}
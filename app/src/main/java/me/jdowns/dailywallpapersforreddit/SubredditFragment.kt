package me.jdowns.dailywallpapersforreddit

import android.app.AlertDialog
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.experimental.launch

@UiThread
class SubredditFragment : Fragment() {
    private lateinit var presenter: SubredditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        presenter = SubredditPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.main_set_button).setOnClickListener {
            launch {
                presenter.requestPost(view.findViewById<EditText>(R.id.main_subreddit_edit_text).text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) = inflater.inflate(R.menu.menu_main, menu)

    fun showError() {
        AlertDialog.Builder(context)
            .setTitle(R.string.error)
            .setMessage(R.string.post_retrieval_error)
            .setPositiveButton(android.R.string.ok, { dialogInterface, _ -> dialogInterface.dismiss() })
            .show()
    }

    companion object {
        const val FRAGMENT_TAG = "mainFragment"
    }
}
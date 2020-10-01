package vn.thailam.challenge1.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_cheque_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.thailam.challenge1.R
import vn.thailam.challenge1.core.widget.ChequeSpaceItemDecoration
import vn.thailam.challenge1.features.detail.ChequeDetailFragment
import vn.thailam.challenge1.features.detail.ChequeDetailSharedViewModel
import vn.thailam.challenge1.features.list.adapter.ChequeListAdapter
import vn.thailam.challenge1.features.list.models.ChequeListItemUiModel

class ChequeListFragment : Fragment() {

    companion object {
        const val TAG = "ChequeListFragment"

        fun newInstance() = ChequeListFragment()
    }

    private val viewModel by viewModel<ChequeListViewModel>()

    private val detailSharedViewModel by sharedViewModel<ChequeDetailSharedViewModel>()

    private val chequesAdapter by lazy {
        ChequeListAdapter(object : ChequeListAdapter.ClickListener {
            override fun onItemClick(item: ChequeListItemUiModel, sharedElementView: View, position: Int) {
                val detailFragment = ChequeDetailFragment.newInstance()
                parentFragmentManager
                    .beginTransaction()
                    .addSharedElement(sharedElementView, ViewCompat.getTransitionName(sharedElementView).orEmpty())
                    .addToBackStack(TAG)
                    .setReorderingAllowed(true)
                    .setCustomAnimations(
                        R.anim.anim_fade_in_enter,
                        R.anim.anim_fade_out_exit,
                        R.anim.anim_fade_in_enter,
                        R.anim.anim_fade_out_exit
                    )
                    .replace(R.id.flContainer, detailFragment)
                    .commit()
                startExitAnimation(position)

                detailSharedViewModel.run {
                    setChequeItemId(item.id)
                    setSharedElementTransitionName(item.title)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        postponeEnterTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cheque_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.chequeUiModel.observe(viewLifecycleOwner, Observer {
            chequesAdapter.submitList(it.items)
        })

        viewModel.getCheques()
    }

    private fun setupUI() {
        setupSharedElementTransition()
        setupChequesRecyclerView()
    }

    private fun setupSharedElementTransition() {
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.transition_move)
    }

    private fun setupChequesRecyclerView() {
        rvCheques.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = chequesAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(ChequeSpaceItemDecoration())
            doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun startExitAnimation(sharedElementItemPosition: Int) {
        // Slide up toolbar
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide_out_to_top)
        tbChequeList.startAnimation(animation)
        vToolbarDivider.startAnimation(animation)
        // Animate shrink recycler items
        rvCheques.layoutAnimation = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.anim_layout_shrink)
        rvCheques.layoutAnimationListener = object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                rvCheques?.alpha = 0f
            }

            override fun onAnimationStart(animation: Animation?) {
                rvCheques.layoutManager?.findViewByPosition(sharedElementItemPosition)?.clearAnimation()
            }

        }
        rvCheques.startLayoutAnimation()
    }
}

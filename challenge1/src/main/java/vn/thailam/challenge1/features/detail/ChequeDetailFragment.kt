package vn.thailam.challenge1.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.SharedElementCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_cheque_detail.*
import kotlinx.android.synthetic.main.item_cheque_list.*
import kotlinx.android.synthetic.main.item_cheque_list.clChequeItemContainer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.thailam.challenge1.R
import vn.thailam.challenge1.core.utils.doDelay
import vn.thailam.challenge1.features.detail.adapter.ChequeItemsAdapter
import vn.thailam.challenge1.features.detail.models.ChequeDetailUiModel
import vn.thailam.challenge1.features.detail.models.ChequeItemUiModel


class ChequeDetailFragment : Fragment() {

    companion object {
        fun newInstance(): ChequeDetailFragment {
            return ChequeDetailFragment()
        }
    }

    private val viewModel by viewModel<ChequeDetailViewModel>()

    private val detailSharedViewModel by sharedViewModel<ChequeDetailSharedViewModel>()

    private val itemsAdapter by lazy {
        ChequeItemsAdapter(object : ChequeItemsAdapter.ItemClickListener {
            override fun onClick(item: ChequeItemUiModel) {
                Toast.makeText(requireContext(), "Clicked item ${item.title}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSharedElementTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cheque_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
    }

    private fun setupSharedElementTransition() {
        postponeEnterTransition()
        TransitionInflater.from(context).inflateTransition(R.transition.transition_move).let {
            sharedElementEnterTransition = it
            sharedElementReturnTransition = it
        }

        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
                // Why divide by 2? To make animation starts mid way through the transition, making the transition smoother
                doDelay(resources.getInteger(R.integer.Cheque_shared_element_transition_duration).toLong() / 2) {
                    viewModel.setTransitionCompleted()
                }
            }
        })
    }

    private fun setupUI() {
        setOnClickListeners()
        setupItemsRecycler()
    }

    private fun setOnClickListeners() {
        tbChequeDetail.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    private fun setupItemsRecycler() {
        rvChequeDetailItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = itemsAdapter
            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.anim_layout_fall_down)
        }
    }

    private fun setupViewModel() {
        viewModel.chequeUiModel.observe(viewLifecycleOwner, Observer {
            updateHeaderCard(it)
            startPostponedEnterTransition()
        })

        viewModel.chequeItems.observe(viewLifecycleOwner, Observer {
            itemsAdapter.submitList(it)
        })

        viewModel.onTransitionComplete.observe(viewLifecycleOwner, Observer {
            onTransitionCompleted()
        })

        detailSharedViewModel.chequeItemId.observe(viewLifecycleOwner, Observer {
            viewModel.getChequeById(it)
        })

        detailSharedViewModel.sharedElementTransitionName.observe(viewLifecycleOwner, Observer {
            clChequeItemContainer.transitionName = it
        })
    }

    private fun updateHeaderCard(uiModel: ChequeDetailUiModel) {
        Glide.with(requireContext())
            .load(uiModel.leadingIconUrl)
            .circleCrop()
            .into(ivChequeLeadingIcon)
        tvChequeTitle.text = uiModel.title
        tvChequeAmount.text = uiModel.displayAmount
        tvChequeDate.text = uiModel.displayDate
        tvChequeStatus.text = uiModel.displayStatus
        uiModel.statusIconRes?.let {
            ivChequeStatusIcon.setImageDrawable(ResourcesCompat.getDrawable(resources, it, null))
        }
    }

    private fun onTransitionCompleted() {
        val setAlphaToOriginal = {
            vTopBackGround.alpha = 1f
            tbChequeDetail.alpha = 1f
            fabGift.alpha = 1f
            fabMessage.alpha = 1f
        }

        val slideDownAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide_from_top)
        val slideUpAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_slide_from_bottom)

        slideDownAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) = Unit
            override fun onAnimationStart(animation: Animation?) {
                rvChequeDetailItems.scheduleLayoutAnimation()
                setAlphaToOriginal.invoke()
            }

            override fun onAnimationEnd(animation: Animation?) {
            }
        })

        tbChequeDetail.startAnimation(slideDownAnim)
        vTopBackGround.startAnimation(slideDownAnim)
        fabGift.startAnimation(slideUpAnim)
        fabMessage.startAnimation(slideUpAnim)
    }
}

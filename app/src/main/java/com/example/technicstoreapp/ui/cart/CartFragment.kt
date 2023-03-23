package com.example.technicstoreapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            getAllPrices()

            priceLiveData.observe(viewLifecycleOwner) {
                binding.order.text = it.toString()
            }
        }
        setupCatalogRecyclerView()
        observeCartTechnicLiveData()
    }

    private fun observeCartTechnicLiveData() {
        viewModel.technicCartLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.recyclerCart.adapter?.let { adapter ->
                if (adapter is CartAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = CartAdapter(
            ::onItemClick,
            ::onPlusClick,
            ::onMinusClick,
            ::onDeleteClick,
            ::updateOrder
        )

        binding.recyclerCart.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getTechnicCart()
    }

    private fun onItemClick(id: Int) {
        val action = CartFragmentDirections.actionNavigationCartToTechnicPageFragment(id)
        findNavController().navigate(action)
    }

    private fun onPlusClick(id: Int) {
        viewModel.plusUnitTechnic(id)
    }

    private fun onMinusClick(id: Int) {
        viewModel.minusUnitTechnic(id)
    }

    private fun onDeleteClick(id: Int, rowView: View) {
        val anim = AnimationUtils.loadAnimation(rowView.context, R.anim.recycler_remove_item)
        rowView.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                viewModel.deleteUnitTechnic(id)
            }
        })
    }

    private fun updateOrder() {
        putPrice()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun putPrice() {
        with(viewModel) {
            getAllPrices()

            priceLiveData.observe(viewLifecycleOwner) {
                binding.order.text = resources.getString(R.string.order) + it.toString() + " р."
            }
        }
    }
}
package com.example.technicstoreapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentCartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onStart() {
        super.onStart()

        with(viewModel) {
            checkListTechnicLiveData.observe(viewLifecycleOwner) {
                binding.cartGroup.isVisible = !it
                binding.emptyCart.isVisible = it
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkUser()

        viewModel.checkListTechnic()

        binding.comeToCatalog.setOnClickListener {
            val navController =
                requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
            navController.popBackStack(R.id.navigation_cart, false)

            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNavigationView.menu.findItem(R.id.navigation_catalog).let { menu ->
                menu.isChecked = true
            }
            bottomNavigationView.selectedItemId = R.id.navigation_catalog
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarCart.isVisible = it
            binding.cartGroup.isVisible = !it
        }

        viewModel.checkLiveData.observe(viewLifecycleOwner) {check ->
            binding.order.setOnClickListener {
                if (!check) {
                    val action = CartFragmentDirections.actionNavigationCartToOrderFragment()
                    findNavController().navigate(action)
                } else {
                    val navController =
                        requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
                    navController.popBackStack(R.id.navigation_cart, false)

                    val bottomNavigationView =
                        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
                    bottomNavigationView.menu.findItem(R.id.navigation_profile).let { menu ->
                        menu.isChecked = true
                    }
                    bottomNavigationView.selectedItemId = R.id.navigation_profile
                }
            }
        }

        setupPrice()
        setupCatalogRecyclerView()
        observeCartTechnicLiveData()
    }

    private fun setupPrice() {
        with(viewModel) {
            getAllPrices()
            priceLiveData.observe(viewLifecycleOwner) {
                binding.order.text = getString(R.string.order, it.toString())
            }
        }
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

    private fun onItemClick(id: Int, color: String) {
        val action = CartFragmentDirections.actionNavigationCartToTechnicPageFragment(id, color)
        findNavController().navigate(action)
    }

    private fun onPlusClick(id: Int, color: String) {
        viewModel.plusUnitTechnic(id, color)
    }

    private fun onMinusClick(id: Int, color: String) {
        viewModel.minusUnitTechnic(id, color)
    }

    private fun onDeleteClick(id: Int, rowView: View, color: String) {
        val anim = AnimationUtils.loadAnimation(rowView.context, R.anim.recycler_remove_item)
        rowView.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                viewModel.deleteUnitTechnic(id, color)
            }
        })
    }

    private fun updateOrder() {
        with(viewModel) {
            getAllPrices()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.technicstoreapp.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentCartBinding
import com.example.technicstoreapp.di.app.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class CartFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: CartViewModel by viewModels { factory }
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

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

        checkListTechnicLiveData()
        comeToCatalog()
        observeCheckLiveData()
        comeToOrder()
        observeCountLiveData()
        setupPrice()
        setupCatalogRecyclerView()
        observeCartTechnicLiveData()
        checkNetworkConnection()
        retry()
    }

    private fun retry() {
        binding.retryCart.setOnClickListener {
            viewModel.checkNetworkConnection()
        }
    }

    private fun checkNetworkConnection() {
        viewModel.checkNetworkConnection()
        viewModel.checkNetworkLiveData.observe(viewLifecycleOwner) {
            if (it) {
                with(binding) {
                    noInternetGroup.isVisible = false
                    viewCartGroup.isVisible = true
                }
                setupViewModel()
            } else {
                with(binding) {
                    noInternetGroup.isVisible = true
                    viewCartGroup.isVisible = false
                    emptyCart.isVisible = false
                    cartGroup.isVisible = false
                }
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getTechnicCart()
            checkListTechnic()
            getAllPrices()
        }
    }

    private fun observeCheckLiveData() {
        viewModel.checkUserLiveData.observe(viewLifecycleOwner) {
            if (!it) {
                val action = CartFragmentDirections.actionNavigationCartToOrderFragment()
                findNavController().navigate(action)
            } else {
                val action =
                    CartFragmentDirections.actionNavigationCartToNotAuthenticationFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun comeToOrder() {
        binding.orderCart.setOnClickListener {
            viewModel.checkUser()
        }
    }

    private fun comeToCatalog() {
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
    }

    private fun checkListTechnicLiveData() {
        with(viewModel) {
            val mediatorLiveData = MediatorLiveData<Boolean>()

            val loadingObserver = Observer<Boolean> { loading ->
                binding.progressBarCart.isVisible = loading
                binding.cartGroup.isVisible = !loading
                binding.orderCart.isVisible = !loading
            }

            mediatorLiveData.addSource(checkListTechnicLiveData) {
                if (!it) {
                    mediatorLiveData.addSource(loadingLiveData, loadingObserver)
                } else {
                    mediatorLiveData.removeSource(loadingLiveData)
                }
                binding.cartGroup.isVisible = !it
                binding.emptyCart.isVisible = it
            }

            mediatorLiveData.observe(viewLifecycleOwner) { }
        }
    }

    private fun setupPrice() {
        with(viewModel) {
            priceLiveData.observe(viewLifecycleOwner) {
                binding.orderCart.text = getString(R.string.order, it.toString())
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

    private fun observeCountLiveData() {
        viewModel.countLiveData.observe(viewLifecycleOwner) {
            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            val badge = bottomNavigationView.getOrCreateBadge(R.id.navigation_cart)
            badge.number = it
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
        viewModel.getAllPrices()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
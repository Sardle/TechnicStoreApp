package com.example.technicstoreapp.ui.technic_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentTechnicPageBinding
import com.example.technicstoreapp.domain.TechnicData
import com.example.technicstoreapp.ui.custom.CustomAlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechnicPageFragment : Fragment() {

    private var _binding: FragmentTechnicPageBinding? = null
    private val viewModel by viewModels<TechnicPageViewModel>()
    private val binding get() = _binding!!
    private val args: TechnicPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTechnicPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_catalog).let { menu ->
            menu.isChecked = true
        }

        viewModel.checkToFavourite(args.id)
        viewModel.checkIsFavouriteLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.toFavourite.isVisible = false
            } else {
                binding.toFavourite.isVisible = true
                binding.toFavourite.text =
                    if (it) getString(R.string.remove_from_favourite) else getString(R.string.add_to_favourite)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTechnicInfo(args.id)
        viewModel.checkToFavourite(args.id)

        binding.toFavourite.setOnClickListener {
            if (binding.toFavourite.text == getString(R.string.add_to_favourite)) {
                viewModel.addTechnicToFavourite(args.id, binding.toFavourite)
                binding.toFavourite.text = getString(R.string.remove_from_favourite)
            } else {
                viewModel.deleteFromFavourite(args.id, binding.toFavourite)
                binding.toFavourite.text = getString(R.string.add_to_favourite)
            }
        }
        setupBadgeCart()
        observeLoadingLiveData()
        observeCheckLiveData()
        observeTechnicLiveData()
        back()
    }

    private fun setupBadgeCart() {
        viewModel.countLiveData.observe(viewLifecycleOwner) {
            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            val badge = bottomNavigationView.getOrCreateBadge(R.id.navigation_cart)
            badge.number = it
        }
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.groupTechnicPage.isVisible = !it
            binding.progressBarTechnicPage.isVisible = it
        }
    }

    private fun observeCheckLiveData() {
        viewModel.checkLiveData.observe(viewLifecycleOwner) {
            binding.addToCart.isVisible = it
            binding.productInCart.isVisible = !it
        }
    }

    private fun observeTechnicLiveData() {
        viewModel.technicLiveData.observe(viewLifecycleOwner) { technic ->

            if (technic != null) {
                setupPage(technic)

                viewModel.checkIfElementExists(technic.name, getSelectedTabText())
                addTabSelectedListener(technic)
                addToCart(technic)
            }
        }
    }

    private fun addToCart(technic: TechnicData) {
        binding.addToCart.setOnClickListener {
            showDialogAccess(technic.name, getSelectedTabText())
            viewModel.insertTechnicToCart(technic, getSelectedTabText())
            viewModel.checkIfElementExists(technic.name, getSelectedTabText())
        }
    }

    private fun addTabSelectedListener(technic: TechnicData) {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewModel.checkIfElementExists(technic.name, tab.text.toString())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun getSelectedTabText(): String {
        val selectedTabPosition = binding.tabLayout.selectedTabPosition
        val selectedTab = binding.tabLayout.getTabAt(selectedTabPosition)
        return selectedTab?.text.toString()
    }

    private fun showDialogAccess(name: String, selectedColor: String) {
        val customAlertDialog = this@TechnicPageFragment.context?.let {
            CustomAlertDialog(it)
                .setImage(R.drawable.success)
                .setMessage(getString(R.string.to_cart_access))
                .setBackText(getString(R.string.back))
                .setOkText(getString(R.string.to_cart))
                .setOnOkClickListener { _, _ ->
                    val navController =
                        requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
                    navController.popBackStack(R.id.navigation_catalog, false)

                    val bottomNavigationView =
                        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
                    bottomNavigationView.menu.findItem(R.id.navigation_cart).let { menu ->
                        menu.isChecked = true
                    }
                    bottomNavigationView.selectedItemId = R.id.navigation_cart
                }
                .setOnBackClickListener { _, _ ->
                    viewModel.checkIfElementExists(
                        name,
                        selectedColor
                    )
                }
        }

        customAlertDialog?.show()
    }

    private fun back() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupPage(technic: TechnicData) {
        with(binding) {
            nameHeading.text = technic.name
            nameTechnic.text = technic.name
            description.text = technic.description

            val adapter = TechnicPhotoAdapter(requireActivity(), technic.colors.values.toList())
            imageTechnicPage.adapter = adapter

            val colors = technic.colors.keys.toList()
            TabLayoutMediator(tabLayout, imageTechnicPage) { tab, position ->
                tab.text = colors[position]

                if (args.defaultColor.isNotEmpty()) {
                    val imageIndex = colors.indexOf(args.defaultColor)
                    imageTechnicPage.setCurrentItem(imageIndex, false)
                }
            }.attach()

            addToCart.text = getString(R.string.add_to_cart, technic.price.toString())
        }
    }
}
package com.example.technicstoreapp.ui.profile.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.App
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentInfoUserBinding
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.example.technicstoreapp.ui.custom.CustomAlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class InfoUserFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: InfoUserViewModel by viewModels { factory }
    private var _binding: FragmentInfoUserBinding? = null
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
        _binding = FragmentInfoUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_profile).let { menu ->
            menu.isChecked = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPage()
        logOutUser()
        deleteAccount()
        binding.backInfoUser.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun logOutUser() {
        binding.logOutAccount.setOnClickListener {
            showDialogLogOut()
        }
    }

    private fun deleteAccount() {
        binding.removeAccount.setOnClickListener {
            showDialogDelete()
        }
    }

    private fun showDialogDelete() {
        val customAlertDialog =
            CustomAlertDialog(requireContext())
                .setImage(R.drawable.ic_delete)
                .setMessage(getString(R.string.delete_account))
                .setBackText(getString(R.string.no))
                .setOkText(getString(R.string.yes))
                .setOnOkClickListener { _, _ ->
                    viewModel.deleteUser()
                    comeToProfilePage()
                }

        customAlertDialog.show()
    }

    private fun showDialogLogOut() {
        val customAlertDialog =
            CustomAlertDialog(requireContext())
                .setImage(R.drawable.ic_log_out)
                .setMessage(getString(R.string.log_out))
                .setBackText(getString(R.string.no))
                .setOkText(getString(R.string.yes))
                .setOnOkClickListener { _, _ ->
                    viewModel.logOutUser()
                    comeToProfilePage()
                }

        customAlertDialog.show()
    }

    private fun comeToProfilePage() {
        val action = InfoUserFragmentDirections.actionInfoUserFragmentToNavigationProfile()
        findNavController().navigate(action)
    }

    private fun setupPage() {
        with(viewModel) {
            getUser()

            viewModel.loadingLiveData.observe(viewLifecycleOwner) { show ->
                checkLoading(show)
            }

            userLiveData.observe(viewLifecycleOwner) { user ->
                with(binding) {
                    nameUserPage.text = user.name
                    numberUser.text = user.number
                    emailUser.text = user.email
                    dateOfBirthUser.text = user.dateOfBirth
                    if (user.address.isNotEmpty()) {
                        actualAddress.isVisible = true
                        actualAddressUser.isVisible = true
                        actualAddressUser.text = user.address
                    }
                }
            }
        }
    }

    private fun checkLoading(exists: Boolean) {
        for (view in requireView().allViews) {
            if (view is ProgressBar) {
                view.isVisible = exists
            } else if (view !is ConstraintLayout) {
                view.isVisible = !exists
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
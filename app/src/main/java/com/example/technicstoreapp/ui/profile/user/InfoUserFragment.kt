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
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentInfoUserBinding
import com.example.technicstoreapp.di.app.App
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
        val customAlertDialog = this@InfoUserFragment.context?.let {
            CustomAlertDialog(it)
                .setImage(R.drawable.delete)
                .setMessage(getString(R.string.delete_account))
                .setBackText(getString(R.string.no))
                .setOkText(getString(R.string.yes))
                .setOnOkClickListener { _, _ ->
                    viewModel.deleteUser()
                    comeToProfilePage()
                }
        }

        customAlertDialog?.show()
    }

    private fun showDialogLogOut() {
        val customAlertDialog = this@InfoUserFragment.context?.let {
            CustomAlertDialog(it)
                .setImage(R.drawable.log_out)
                .setMessage(getString(R.string.log_out))
                .setBackText(getString(R.string.no))
                .setOkText(getString(R.string.yes))
                .setOnOkClickListener { _, _ ->
                    viewModel.logOutUser()
                    comeToProfilePage()
                }
        }

        customAlertDialog?.show()
    }

    private fun comeToProfilePage() {
        val action = InfoUserFragmentDirections.actionInfoUserFragmentToNavigationProfile()
        findNavController().navigate(action)
    }

    private fun setupPage() {
        with(viewModel) {
            getUser()

            viewModel.loadingLiveData.observe(viewLifecycleOwner) {
                checkLoading(it)
            }

            userLiveData.observe(viewLifecycleOwner) {
                binding.nameUserPage.text = it.name
                binding.numberUser.text = it.number
                binding.emailUser.text = it.email
                binding.dateOfBirthUser.text = it.dateOfBirth
                if (it.address.isNotEmpty()) {
                    binding.actualAddress.isVisible = true
                    binding.actualAddressUser.isVisible = true
                    binding.actualAddressUser.text = it.address
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
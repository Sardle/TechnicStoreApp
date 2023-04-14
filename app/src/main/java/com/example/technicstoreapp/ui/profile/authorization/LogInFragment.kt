package com.example.technicstoreapp.ui.profile.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentLogInBinding
import com.example.technicstoreapp.di.app.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import javax.inject.Inject

class LogInFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: LogInViewModel by viewModels { factory }
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
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

        checkDataEntry()
        removeStroke(binding.numberPhone)
        removeStroke(binding.password)
        comeToProfilePage()
        leadToBYNumberFormat()
        comeToRegisterPage()
    }

    private fun comeToProfilePage() {
        viewModel.checkLiveData.observe(viewLifecycleOwner) {
            if (it) {
                val action = LogInFragmentDirections.actionLogInFragmentToNavigationProfile()
                findNavController().navigate(action)
            } else {
                binding.progressBarProfileLogIn.isVisible = false
                binding.logInGroup.isVisible = true
                binding.incorrectNumberOrPassword.isVisible = true
            }
        }
    }

    private fun removeStroke(editText: EditText) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText.background = requireContext().getDrawable(R.drawable.bg_login_edittext)
                binding.incorrectNumberOrPassword.isVisible = false
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        editText.addTextChangedListener(textWatcher)
    }

    private fun checkPassword(): Boolean {
        if (!binding.password.text.toString().matches(REGEX_PASSWORD.toRegex())) {
            binding.password.error = getString(R.string.error_to_password)
            binding.password.background =
                this@LogInFragment.context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.bg_error_edittext
                    )
                }
            return false
        }
        return true
    }

    private fun checkDataEntry() {
        binding.logIn.setOnClickListener {
            val checkPassword = checkPassword()
            val areAllEditTextsFilled = areAllEditTextsFilled()
            val checkNumber = checkNumber()
            if (checkPassword && areAllEditTextsFilled && checkNumber) {
                binding.progressBarProfileLogIn.isVisible = true
                binding.logInGroup.isVisible = false
                viewModel.checkLogInUser(
                    binding.numberPhone.text.toString(),
                    binding.password.text.toString()
                )
            }
        }
    }

    private fun comeToRegisterPage() {
        binding.register.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun checkNumber(): Boolean {
        if (binding.numberPhone.text.toString().length != 19) {
            binding.numberPhone.error = getString(R.string.error_to_number)
            binding.numberPhone.background =
                this@LogInFragment.context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.bg_error_edittext
                    )
                }
            return false
        }
        return true
    }

    private fun leadToBYNumberFormat() {
        val slots = UnderscoreDigitSlotsParser().parseSlots(FORMAT_NUMBER)
        val mask = MaskImpl.createTerminated(slots)
        mask.isHideHardcodedHead = true
        val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.numberPhone)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun areAllEditTextsFilled(): Boolean {
        var allFilled = true
        for (view in requireView().allViews) {
            if (view is EditText) {
                val text = view.text.toString().trim()
                if (text.isEmpty()) {
                    allFilled = false
                    view.background = requireContext().getDrawable(R.drawable.bg_error_edittext)
                } else {
                    view.background = requireContext().getDrawable(R.drawable.bg_login_edittext)
                }
            }
        }
        return allFilled
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\w{8,}\$"

        private const val FORMAT_NUMBER = "+375 (__) ___-__-__"
    }
}
package com.example.technicstoreapp.ui.profile.authorization

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
import com.example.technicstoreapp.App
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentSignUpBinding
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.example.technicstoreapp.domain.models.UserData
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SignUpViewModel by viewModels { factory }
    private var _binding: FragmentSignUpBinding? = null
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
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkDataEntry()
        comeToProfilePage()
        leadToFormat(FORMAT_NUMBER, binding.phone)
        leadToFormat(FORMAT_DATE, binding.dateOfBirth)
        removeStrokeForAll()
        comeToBack()
    }

    private fun comeToBack() {
        binding.backRegister.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun checkDataEntry() {
        binding.registerBtn.setOnClickListener {
            val checkPassword = checkPassword()
            val checkEmail = checkEmail()
            val areAllEditTextsFilled = areAllEditTextsFilled()
            val checkDate = checkDate()
            val checkNumber = checkNumber()
            if (checkPassword && checkEmail && areAllEditTextsFilled && checkDate && checkNumber) {
                with(binding) {
                    progressBarRegister.isVisible = true
                    registerGroup.isVisible = false
                    viewModel.addUserToList(
                        UserData(
                            EMPTY_STRING,
                            name.text.toString(),
                            passwordReg.text.toString(),
                            phone.text.toString(),
                            EMPTY_STRING,
                            email.text.toString(),
                            ZERO,
                            emptyList(),
                            dateOfBirth.text.toString(),
                            emptyList()
                        )
                    )
                }
            }
        }
    }

    private fun comeToProfilePage() {
        viewModel.checkLiveData.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                binding.progressBarRegister.isVisible = false
                val action = SignUpFragmentDirections.actionRegisterFragmentToAuthSuccessDialog()
                findNavController().navigate(action)
            } else {
                with(binding) {
                    registerGroup.isVisible = true
                    progressBarRegister.isVisible = false
                    phone.error = getString(R.string.error_to_number_is_occupied)
                    phone.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_error_edittext
                        )
                }
            }
        }
    }

    private fun leadToFormat(format: String, editText: EditText) {
        val slots = UnderscoreDigitSlotsParser().parseSlots(format)
        val mask = MaskImpl.createTerminated(slots)
        mask.isHideHardcodedHead = true
        val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(editText)
    }

    private fun areAllEditTextsFilled(): Boolean {
        var allFilled = true
        for (view in requireView().allViews) {
            if (view is EditText) {
                val text = view.text.toString().trim()
                if (text.isEmpty()) {
                    allFilled = false
                    view.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.bg_error_edittext)
                } else {
                    view.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.bg_login_edittext)
                }
            }
        }
        return allFilled
    }

    private fun removeStrokeForAll() {
        for (view in requireView().allViews) {
            if (view is EditText) {

                val textWatcher = object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        view.background =
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.bg_login_edittext
                            )
                    }

                    override fun afterTextChanged(s: Editable?) {}
                }
                view.addTextChangedListener(textWatcher)
            }
        }
    }

    private fun checkNumber(): Boolean {
        if (binding.phone.text.toString().length != 19) {
            binding.phone.error = getString(R.string.error_to_number)
            binding.phone.background =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_error_edittext
                )
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        if (!binding.passwordReg.text.toString().matches(REGEX_PASSWORD.toRegex())) {
            binding.passwordReg.error = getString(R.string.error_to_password)
            binding.passwordReg.background =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_error_edittext
                )
            return false
        }
        return true
    }

    private fun checkEmail(): Boolean {
        if (!binding.email.text.toString().matches(REGEX_EMAIL.toRegex())) {
            binding.email.error = getString(R.string.error_to_password)
            binding.email.background =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_error_edittext
                )
            return false
        }
        return true
    }

    private fun checkDate(): Boolean {
        if (!binding.dateOfBirth.text.toString().matches(REGEX_DATE.toRegex())) {
            binding.dateOfBirth.error = getString(R.string.error_to_date)
            binding.dateOfBirth.background =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_error_edittext
                )
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\w{8,}\$"

        private const val REGEX_DATE = "^(0[1-9]|[12]\\d|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}\$"

        private const val REGEX_EMAIL = "^\\S+@\\S+\\.\\S+\$"

        private const val FORMAT_NUMBER = "+375 (__) ___-__-__"

        private const val FORMAT_DATE = "__.__.____"

        private const val EMPTY_STRING = ""

        private const val ZERO = 0
    }
}
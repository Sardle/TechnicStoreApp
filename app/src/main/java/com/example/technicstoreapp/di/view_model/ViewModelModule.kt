package com.example.technicstoreapp.di.view_model

import androidx.lifecycle.ViewModel
import com.example.technicstoreapp.ui.cart.CartViewModel
import com.example.technicstoreapp.ui.cart.order.OrderViewModel
import com.example.technicstoreapp.ui.catalog.CatalogViewModel
import com.example.technicstoreapp.ui.catalog.category_page.CategoryPageViewModel
import com.example.technicstoreapp.ui.home.HomeViewModel
import com.example.technicstoreapp.ui.profile.ProfileViewModel
import com.example.technicstoreapp.ui.profile.authorization.LogInViewModel
import com.example.technicstoreapp.ui.profile.authorization.SignUpViewModel
import com.example.technicstoreapp.ui.profile.favourite.FavouriteViewModel
import com.example.technicstoreapp.ui.profile.history_order.HistoryOrderViewModel
import com.example.technicstoreapp.ui.profile.user.InfoUserViewModel
import com.example.technicstoreapp.ui.search.SearchViewModel
import com.example.technicstoreapp.ui.technic_page.TechnicPageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    fun bindOrderViewModel(viewModel: OrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryPageViewModel::class)
    fun bindCategoryPageViewModel(viewModel: CategoryPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    fun bindCatalogViewModel(viewModel: CatalogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun bindLogInViewModel(viewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    fun bindFavouriteViewModel(viewModel: FavouriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryOrderViewModel::class)
    fun bindHistoryViewModel(viewModel: HistoryOrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoUserViewModel::class)
    fun bindInfoUserViewModel(viewModel: InfoUserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TechnicPageViewModel::class)
    fun bindTechnicPageViewModel(viewModel: TechnicPageViewModel): ViewModel
}
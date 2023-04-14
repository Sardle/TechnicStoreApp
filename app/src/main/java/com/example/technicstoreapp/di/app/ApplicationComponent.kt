package com.example.technicstoreapp.di.app

import android.content.Context
import com.example.technicstoreapp.di.*
import com.example.technicstoreapp.di.view_model.ViewModelModule
import com.example.technicstoreapp.ui.MainActivity
import com.example.technicstoreapp.ui.cart.CartFragment
import com.example.technicstoreapp.ui.cart.order.NotAuthenticationFragment
import com.example.technicstoreapp.ui.cart.order.OrderFragment
import com.example.technicstoreapp.ui.catalog.CatalogFragment
import com.example.technicstoreapp.ui.catalog.category_page.CategoryPageFragment
import com.example.technicstoreapp.ui.home.HomeFragment
import com.example.technicstoreapp.ui.profile.ProfileFragment
import com.example.technicstoreapp.ui.profile.authorization.AuthSuccessDialog
import com.example.technicstoreapp.ui.profile.authorization.LogInFragment
import com.example.technicstoreapp.ui.profile.authorization.SignUpFragment
import com.example.technicstoreapp.ui.profile.favourite.FavouriteFragment
import com.example.technicstoreapp.ui.profile.history_order.HistoryOrderFragment
import com.example.technicstoreapp.ui.profile.user.InfoUserFragment
import com.example.technicstoreapp.ui.search.SearchFragment
import com.example.technicstoreapp.ui.technic_page.TechnicPageFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataBaseModule::class, NewsModule::class, RepositoryModule::class, SourceModule::class, TechModule::class, UserModule::class, CheckNetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: NotAuthenticationFragment)
    fun inject(fragment: OrderFragment)
    fun inject(fragment: CategoryPageFragment)
    fun inject(fragment: CatalogFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: AuthSuccessDialog)
    fun inject(fragment: LogInFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: FavouriteFragment)
    fun inject(fragment: HistoryOrderFragment)
    fun inject(fragment: InfoUserFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: TechnicPageFragment)
    fun inject(fragment: CartFragment)
}
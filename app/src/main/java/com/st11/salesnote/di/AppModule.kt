package com.st11.salesnote.di


import com.st11.salesnote.data.local.AppDatabase
import com.st11.salesnote.repository.SingleProductRepository
import com.st11.salesnote.repository.SingleSaleRepository
import com.st11.salesnote.viewmodel.SingleProductSaleViewModel
import com.st11.salesnote.viewmodel.SingleSaleViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
//    // Define ViewModel injection
//    viewModel { OnboardingViewModel(get()) }
//
//    // Define PreferencesManager injection
//    single { OnboardingPreferencesManager(get()) }
//
//
//    // Define ViewModel injection
//    viewModel { CurrencyViewModel(get()) }
//
//    // Define PreferencesManager injection
//    single { CurrencyPreferences(get()) }
//
//
//    // Define ViewModel injection
//    viewModel { IntervalViewModel(get()) }
//
//    // Define PreferencesManager injection
//    single { IntervalPreferences(get()) }
//
//    // Provide ReminderScheduler
//    single { com.st11.expensetracker.domain.usecase.ReminderScheduler }
//
//    // Provide MainViewModel
//    viewModel { MainViewModel(get(), get()) } // get() for Application and ReminderScheduler
//
////    single { IdentityPreferences(get()) }
////
////    viewModel { CreateIdentityViewModel(get()) }
////
//    single { AppDatabase.getDatabase(get()).expenseDao() }
//    single { ExpenseRepository(get()) }
//    viewModel {  ExpenseViewModel(get()) }
//
//    single { AppDatabase.getDatabase(get()).wishDao() }
//    single { WishlistRepository(get()) }
//    viewModel {  WishlistViewModel(get()) }

//    single { AppDatabase.getDatabase(get()).watchListDao() }
//    single { WatchListRepository(get()) }
//    viewModel { WatchListViewModel(get()) }

    single{ AppDatabase.getDatabase(get()).singleSaleProductDao() }
    single { SingleProductRepository(get()) }
    viewModel { SingleProductSaleViewModel(get()) }


    single { AppDatabase.getDatabase(get()).singleSaleDao() }
    single { SingleSaleRepository(get()) }
    viewModel { SingleSaleViewModel(get()) }

}
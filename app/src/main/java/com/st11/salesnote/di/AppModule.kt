package com.st11.salesnote.di


import com.st11.salesnote.data.local.AppDatabase
import com.st11.salesnote.repository.SingleProductRepository
import com.st11.salesnote.repository.SingleSaleRepository
import com.st11.salesnote.viewmodel.SingleProductSaleViewModel
import com.st11.salesnote.viewmodel.SingleSaleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
//import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {

    single{ AppDatabase.manageDatabase(get()).singleSaleProductDao() }
    single { SingleProductRepository(get()) }
    viewModel { SingleProductSaleViewModel(get()) }


    single { AppDatabase.manageDatabase(get()).singleSaleDao() }
    single { SingleSaleRepository(get()) }
    viewModel { SingleSaleViewModel(get()) }

}
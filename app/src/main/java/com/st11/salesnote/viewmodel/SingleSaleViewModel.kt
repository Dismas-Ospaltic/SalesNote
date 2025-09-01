package com.st11.salesnote.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st11.salesnote.model.SingleProductEntity
import com.st11.salesnote.model.SingleSaleEntity
import com.st11.salesnote.repository.SingleSaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SingleSaleViewModel(private val singleSaleRepository: SingleSaleRepository) : ViewModel() {
    private val _singleSale = MutableStateFlow<List<SingleSaleEntity>>(emptyList())
    val singleSale: StateFlow<List<SingleSaleEntity>> = _singleSale




    //hold list of single product
    private val _allSingleSale = MutableStateFlow<List<SingleSaleEntity>>(emptyList())
    val allSingleSale: StateFlow<List<SingleSaleEntity>> = _allSingleSale





    fun insertSingleSale(singleSaleEntity: SingleSaleEntity) {
        viewModelScope.launch {
//            singleProductRepository.insertSoldProduct(singleProductEntity)
            singleSaleRepository.insertSingleSale(singleSaleEntity)
        }
    }

    fun updateSingleSale(singleSaleEntity: SingleSaleEntity) {
        viewModelScope.launch {
//            singleProductRepository.updateSoldProduct(singleProductEntity)
            singleSaleRepository.updateSingleSale(singleSaleEntity)
        }
    }


//    init {
//        getSingleSalesByDate()
//    }

    /**
     * Fetch all watchlist
     */
    private fun getAllSingleSale() {
        viewModelScope.launch {
           singleSaleRepository.getAllSingleSale().collectLatest { products ->
                _allSingleSale.value = products
            }
        }
    }




    private fun getSalesByDate(saleDate: String) {
        viewModelScope.launch {
           singleSaleRepository.getSalesByDate(saleDate).collectLatest { products ->
                _singleSale.value = products
            }
        }
    }
}



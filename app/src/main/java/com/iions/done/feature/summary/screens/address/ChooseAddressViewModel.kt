package com.iions.done.feature.summary.screens.address

import android.app.Application
import androidx.lifecycle.*
import com.iions.done.feature.auth.data.model.AddressResponse
import com.iions.done.feature.main.data.model.DistrictResponse
import com.iions.done.feature.main.data.model.StreetResponse
import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse
import com.iions.done.utils.archcomponents.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseAddressViewModel @Inject constructor(
    var app: Application,
    private val repository: SummaryRepository
) : AndroidViewModel(app), LifecycleObserver {

    private val addressUseCase = MutableLiveData<Response<List<AddressResponse>>>()
    val addressResponse: LiveData<Response<List<AddressResponse>>> =
        addressUseCase

    private val districtUseCase = MutableLiveData<Response<List<DistrictResponse>>>()
    val districtResponse: LiveData<Response<List<DistrictResponse>>> =
        districtUseCase

    private val streetUseCase = MutableLiveData<Response<List<StreetResponse>>>()
    val streetResponse: LiveData<Response<List<StreetResponse>>> =
        streetUseCase

    private val createOrderUseCase = MutableLiveData<Response<CreateOrderBaseResponse>>()
    val createOrderResponse: LiveData<Response<CreateOrderBaseResponse>> =
        createOrderUseCase

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    fun fetchAddressList() {
        viewModelScope.launch {
            addressUseCase.value = Response.loading()
            try {
                addressUseCase.value = Response.complete(
                    repository.fetchAddressList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                addressUseCase.value = Response.error(error)
            }
        }
    }

    fun fetchDistrictList() {
        viewModelScope.launch {
            districtUseCase.value = Response.loading()
            try {
                districtUseCase.value = Response.complete(
                    repository.fetchDistrictList()
                )
            } catch (error: Exception) {
                error.printStackTrace()
                districtUseCase.value = Response.error(error)
            }
        }
    }

    fun fetchStreetList(districtId: Int) {
        viewModelScope.launch {
            streetUseCase.value = Response.loading()
            try {
                streetUseCase.value = Response.complete(
                    repository.fetchStreetList(districtId)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                streetUseCase.value = Response.error(error)
            }
        }
    }

    fun createOrder(cod: String, districtId: Int, streetId: Int, localAddress: String) {
        viewModelScope.launch {
            createOrderUseCase.value = Response.loading()
            try {
                createOrderUseCase.value = Response.complete(
                    repository.createOrder(cod, districtId, streetId, localAddress)
                )
            } catch (error: Exception) {
                error.printStackTrace()
                createOrderUseCase.value = Response.error(error)
            }
        }
    }

}
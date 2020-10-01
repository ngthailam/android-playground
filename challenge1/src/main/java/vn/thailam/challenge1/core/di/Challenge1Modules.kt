package vn.thailam.challenge1.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.thailam.challenge1.Challenge1ViewModel
import vn.thailam.challenge1.core.data.repository.ChequeRepository
import vn.thailam.challenge1.core.data.repository.ChequeRepositoryImpl
import vn.thailam.challenge1.core.usecase.GetChequeByIdUseCase
import vn.thailam.challenge1.core.usecase.GetChequesUseCase
import vn.thailam.challenge1.features.detail.ChequeDetailSharedViewModel
import vn.thailam.challenge1.features.detail.ChequeDetailViewModel
import vn.thailam.challenge1.features.detail.mapper.ChequeDetailUiModelMapper
import vn.thailam.challenge1.features.detail.mapper.ChequeItemUiModelMapper
import vn.thailam.challenge1.features.list.ChequeListViewModel
import vn.thailam.challenge1.features.list.mapper.ChequeListItemStatusIconProvider
import vn.thailam.challenge1.features.list.mapper.ChequeListItemUiModelMapper
import vn.thailam.challenge1.features.list.mapper.ChequeListUiModelMapper

/**
 * Move to seperate files if big enough
 */
object Challenge1Modules {

    private val repositoryModules = module(override = true) {
        single<ChequeRepository> { ChequeRepositoryImpl() }
    }

    private val useCaseModules = module(override = true) {
        factory { GetChequesUseCase(get()) }
        factory { GetChequeByIdUseCase(get()) }
    }

    private val provider = module(override = true) {
        factory { ChequeListItemStatusIconProvider() }
    }

    private val mapper = module(override = true) {
        factory { ChequeListItemUiModelMapper(get()) }
        factory { ChequeListUiModelMapper(get()) }
        factory { ChequeItemUiModelMapper() }
        factory { ChequeDetailUiModelMapper(get(), get()) }
    }

    private val viewModelModules = module(override = true) {
        viewModel { ChequeListViewModel(get(), get()) }
        viewModel { ChequeDetailViewModel(get(), get()) }
        viewModel { Challenge1ViewModel() }
        viewModel { ChequeDetailSharedViewModel() }
    }

    val challenge1Modules = listOf(
        useCaseModules,
        repositoryModules,
        provider,
        mapper,
        viewModelModules
    )
}

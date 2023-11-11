package william.LETRAS_jogo_da_velha.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import william.LETRAS_jogo_da_velha.data.AppDatabase
import william.LETRAS_jogo_da_velha.data.Repository
import william.LETRAS_jogo_da_velha.viewModels.MainActivityViewModel
import william.LETRAS_jogo_da_velha.viewModels.Tabuleiro3x3ViewModel

val mainModule = module {

    single { AppDatabase.getDatabase(androidContext()).jogadoresDao() }
    single { AppDatabase.getDatabase(androidContext()).historicoDao() }
    single { Repository(jogadoresDao = get(), historicoDao = get()) }

    single { MainActivityViewModel(repository = get()) }
    single { Tabuleiro3x3ViewModel(repository = get()) }
}
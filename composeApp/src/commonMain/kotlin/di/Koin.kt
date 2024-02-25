package di

import data.api.API
import data.api.APIImpl
import data.remote.ArticleRemoteDataSource
import data.remote.BoardRemoteDataSource
import data.repository.ArticleRepositoryImpl
import data.repository.BoardRepositoryImpl
import domain.repository.ArticleRepository
import domain.repository.BoardRepository
import domain.usecase.GetArticleUseCase
import domain.usecase.GetBoardMinimumUseCase
import ui.home.HomeBoardViewModel
import org.koin.dsl.module
import ui.article.ArticleViewModel

fun appModule() = module {
    single { BoardRemoteDataSource(get()) }
    single { ArticleRemoteDataSource(get()) }

    single<BoardRepository> { BoardRepositoryImpl(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get())}

    single { GetBoardMinimumUseCase(get()) }
    single { GetArticleUseCase(get()) }

    factory {
        HomeBoardViewModel(
            getBoardMinimumUseCase = get(),
        )
    }
    factory {
        ArticleViewModel(
            getArticleUseCase = get(),
        )
    }
}

fun networkModule() = module {
    single<API> { APIImpl() }
}
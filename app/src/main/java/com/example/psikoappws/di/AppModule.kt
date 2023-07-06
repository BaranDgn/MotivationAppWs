package com.example.psikoappws.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.psikoappws.data.dataSource.DiaryDatabase
import com.example.psikoappws.data.repository.AuthRepositoryImpl
import com.example.psikoappws.data.repository.DiaryRepositoryImpl
import com.example.psikoappws.data.repository.QuoteRepository
import com.example.psikoappws.data.repository.StoreQuoteRepoImpl
import com.example.psikoappws.data.service.ChatBotService
import com.example.psikoappws.data.service.QuoteService
import com.example.psikoappws.domain.repository.AuthRepository
import com.example.psikoappws.domain.repository.DiaryRepository
import com.example.psikoappws.domain.repository.StoreQuoteRepository
import com.example.psikoappws.domain.useCase.*
import com.example.psikoappws.domain.util.Constants.BASE_URL
import com.example.psikoappws.presenter.opening.DataStoreRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideAuthRepository(impl : AuthRepositoryImpl) : AuthRepository = impl

    @Singleton
    @Provides
    fun provideFireStoreRepository(impl : StoreQuoteRepoImpl) : StoreQuoteRepository = impl

    @Provides
    @Singleton
    fun provideDiaryDatabase(app: Application): DiaryDatabase{
        return Room.databaseBuilder(
            app,
            DiaryDatabase::class.java,
            DiaryDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(db: DiaryDatabase): DiaryRepository{
        return DiaryRepositoryImpl(db.diaryDao)
    }

    @Provides
    @Singleton
    fun provideDiaryUseCases(repository: DiaryRepository): DiaryUseCases{
        return DiaryUseCases(
            getDiary = GetDiary(repository),
            deleteDiary = DeleteDiary(repository),
            addDiary = AddDiary(repository),
            loadDiaryById = LoadDiaryById(repository)

        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Singleton
    @Provides
    fun provideQuoteRepository(
        api : QuoteService
    ) = QuoteRepository(api)



    @Singleton
    @Provides
    fun provideQuoteApi(): QuoteService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(QuoteService::class.java)
    }
}
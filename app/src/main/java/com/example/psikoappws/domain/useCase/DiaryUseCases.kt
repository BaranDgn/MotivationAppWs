package com.example.psikoappws.domain.useCase

data class DiaryUseCases(
    val getDiary: GetDiary,
    val deleteDiary: DeleteDiary,
    val addDiary: AddDiary,
    val loadDiaryById: LoadDiaryById
)
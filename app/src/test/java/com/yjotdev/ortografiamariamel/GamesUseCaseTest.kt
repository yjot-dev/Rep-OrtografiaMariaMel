package com.yjotdev.ortografiamariamel

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.yjotdev.ortografiamariamel.domain.model.Game1Model
import com.yjotdev.ortografiamariamel.domain.model.Game2Model
import com.yjotdev.ortografiamariamel.domain.model.Game3Model
import com.yjotdev.ortografiamariamel.domain.repository.GamesRepository
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame1DataUseCase
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame2DataUseCase
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame3DataUseCase

/**
 * Pruebas unitarias para los casos de uso relacionados con la obtención de datos de los juegos.
 */
class GamesUseCaseTest {

    private lateinit var gamesRepository: GamesRepository
    private lateinit var getGame1DataUseCase: GetGame1DataUseCase
    private lateinit var getGame2DataUseCase: GetGame2DataUseCase
    private lateinit var getGame3DataUseCase: GetGame3DataUseCase

    @Before
    fun setUp() {
        // Inicializamos el mock del puerto y los casos de uso antes de cada prueba.
        gamesRepository = mockk()
        getGame1DataUseCase = GetGame1DataUseCase(gamesRepository)
        getGame2DataUseCase = GetGame2DataUseCase(gamesRepository)
        getGame3DataUseCase = GetGame3DataUseCase(gamesRepository)
    }

    @Test
    fun whenGetGame1DataUseCaseIsInvokedSuccessfullyThenItReturnsGame1Data() = runTest {
        // Given (Dado)
        val unit = 1
        val fakeGame1Data = listOf(
            Game1Model(idCard = 1, textCard = "Text Card 1"),
            Game1Model(idCard = 2, textCard = "Text Card 2")
        )
        // Configuramos el mock para que devuelva datos falsos cuando se llame al método del puerto.
        coEvery { gamesRepository.getGame1Data(unit) } returns fakeGame1Data

        // When (Cuando)
        // Invocamos el caso de uso.
        val result = getGame1DataUseCase(unit)

        // Then (Entonces)
        // Verificamos que el resultado sea el esperado.
        assertEquals(fakeGame1Data, result)
        // Verificamos que el método del puerto fue llamado exactamente una vez.
        coVerify(exactly = 1) { gamesRepository.getGame1Data(unit) }
    }

    @Test
    fun whenGetGame2DataUseCaseIsInvokedSuccessfullyThenItReturnsGame2Data() = runTest {
        // Given (Dado)
        val unit = 1
        val fakeGame2Data = listOf(
            Game2Model(
                listOf("Pregunta 1"),
                listOf("A"),
                listOf("Respuesta A","Respuesta B")
            ),
            Game2Model(
                listOf("Pregunta 2"),
                listOf("C"),
                listOf("Respuesta B","Respuesta C")
            )
        )
        // Configuramos el mock para que devuelva datos falsos.
        coEvery { gamesRepository.getGame2Data(unit) } returns fakeGame2Data

        // When (Cuando)
        // Invocamos el caso de uso.
        val result = getGame2DataUseCase(unit)

        // Then (Entonces)
        // Verificamos que el resultado sea el esperado.
        assertEquals(fakeGame2Data, result)
        // Verificamos que el método del puerto fue llamado exactamente una vez.
        coVerify(exactly = 1) { gamesRepository.getGame2Data(unit) }
    }

    @Test
    fun whenGetGame3DataUseCaseIsInvokedSuccessfullyThenItReturnsGame3Data() = runTest {
        // Given (Dado)
        val unit = 1
        val fakeGame3Data = listOf(
            Game3Model(question = "Question A", correctAnswer = "Answer C"),
            Game3Model(question = "Question B", correctAnswer = "Answer A")
        )
        // Configuramos el mock para que devuelva datos falsos.
        coEvery { gamesRepository.getGame3Data(unit) } returns fakeGame3Data

        // When (Cuando)
        // Invocamos el caso de uso.
        val result = getGame3DataUseCase(unit)

        // Then (Entonces)
        // Verificamos que el resultado sea el esperado.
        assertEquals(fakeGame3Data, result)
        // Verificamos que el método del puerto fue llamado exactamente una vez.
        coVerify(exactly = 1) { gamesRepository.getGame3Data(unit) }
    }
}

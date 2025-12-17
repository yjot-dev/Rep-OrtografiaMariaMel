package com.yjotdev.ortografiamariamel

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame1DataUseCase
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame2DataUseCase
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame3DataUseCase
import com.yjotdev.ortografiamariamel.application.mvvm.viewmodel.PlayerViewModel
import com.yjotdev.ortografiamariamel.infrastructure.datasource.DataSourceGame1
import com.yjotdev.ortografiamariamel.infrastructure.datasource.DataSourceGame2
import com.yjotdev.ortografiamariamel.infrastructure.datasource.DataSourceGame3

@OptIn(ExperimentalCoroutinesApi::class)
class PlayerViewModelTest {

    // 1. Mock de los casos de uso (dependencias)
    private val getGame1DataUseCase: GetGame1DataUseCase = mockk()
    private val getGame2DataUseCase: GetGame2DataUseCase = mockk()
    private val getGame3DataUseCase: GetGame3DataUseCase = mockk()

    // 2. ViewModel a probar
    private lateinit var viewModel: PlayerViewModel

    // 3. Dispatcher para pruebas de corrutinas
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Configuramos el hilo principal para pruebas
        Dispatchers.setMain(testDispatcher)

        viewModel = PlayerViewModel(
            getGame1DataUseCase,
            getGame2DataUseCase,
            getGame3DataUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialStateIsDefault() {
        val state = viewModel.uiState.value

        // Verifica valores por defecto del PlayerModel
        assertEquals("", state.name)
        assertEquals(11, state.age)
        assertEquals(0, state.score)
        assertEquals(0, state.success)
        assertEquals(0, state.error)
        assertFalse(state.finishGame)
    }

    @Test
    fun setNameUpdatesPlayerName() {
        // Given
        val expectedName = "Maria"

        // When
        viewModel.setName(expectedName)

        // Then
        assertEquals(expectedName, viewModel.uiState.value.name)
    }

    @Test
    fun setAgeUpdatesPlayerAge() {
        // Given
        val expectedAge = 10

        // When
        viewModel.setAge(expectedAge)

        // Then
        assertEquals(expectedAge, viewModel.uiState.value.age)
    }

    @Test
    fun setSuccessUpdatesCountAndCalculatesScorePositive() {
        // Lógica: Score = abs(success - error) * 10
        // Caso: Success = 5, Error = 0 -> (5-0)*10 = 50

        // When
        viewModel.setSuccess(5)

        // Then
        val state = viewModel.uiState.value
        assertEquals(5, state.success)
        assertEquals(50, state.score)
    }

    @Test
    fun setErrorUpdatesCountAndCalculatesScoreReduced() {
        // Lógica: Score = abs(success - error) * 10
        // Caso previo: Success 5. Nuevo Error: 2 -> (5-2)*10 = 30

        // Given
        viewModel.setSuccess(5) // Score base 50

        // When
        viewModel.setError(2)

        // Then
        val state = viewModel.uiState.value
        assertEquals(2, state.error)
        assertEquals(30, state.score)
    }

    @Test
    fun scoreIsAlwaysPositiveUsingAbsoluteValue() {
        // Lógica: Score = abs(success - error) * 10
        // Caso extremo: Success 2, Error 5 -> (2-5) = -3 -> abs(-3) = 3 -> Score 30

        // Given
        viewModel.setSuccess(2)

        // When
        viewModel.setError(5)

        // Then
        val state = viewModel.uiState.value
        assertEquals(30, state.score) // Verifica el uso de abs()
    }

    @Test
    fun setFinishGameUpdatesFlag() {
        // When
        viewModel.setFinishGame(true)

        // Then
        assertTrue(viewModel.uiState.value.finishGame)
    }

    @Test
    fun loadGamesCallsUseCasesAndUpdatesState() = runTest {
        // Given
        // Simulamos datos de retorno de los casos de uso
        val mockGame1Data = DataSourceGame1.listPairOfCardsUnit1
        val mockGame2Data = DataSourceGame2.listQuestionAndAnswersUnit1
        val mockGame3Data = DataSourceGame3.listQuestionAndAnswersUnit1

        // CoEvery se usa porque loadGames lanza corrutina que llama a estos metodos
        coEvery { getGame1DataUseCase(any()) } returns mockGame1Data
        coEvery { getGame2DataUseCase(any()) } returns mockGame2Data
        coEvery { getGame3DataUseCase(any()) } returns mockGame3Data

        // When
        viewModel.loadGames(unit = 1)

        // Avanzamos el despachador para que se ejecute la corrutina launch
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertEquals(mockGame1Data, state.game1data)
        assertEquals(mockGame2Data, state.game2data)
        assertEquals(mockGame3Data, state.game3data)
    }

    @Test
    fun resetPlayerRestoresDefaultState() {
        // Given: Un estado modificado
        viewModel.setName("Test")
        viewModel.setSuccess(10)

        // When
        viewModel.resetPlayer()

        // Then
        val state = viewModel.uiState.value
        assertEquals("", state.name)
        assertEquals(0, state.score)
    }
}

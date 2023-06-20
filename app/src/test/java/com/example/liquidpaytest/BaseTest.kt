package com.example.liquidpaytest

import com.example.liquidpaytest.domain.util.Dispatchers
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import org.koin.test.KoinTestRule

abstract class BaseTest<SUT : Any> {
    private lateinit var _sut: SUT
    protected val sut get() = _sut

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<Dispatchers> { TestDispatchers() }
                declareModule().invoke(this)
            }
        )
    }

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
        _sut = setupSut()
    }

    protected abstract fun setupSut(): SUT

    protected abstract fun declareModule(): ModuleDeclaration
}

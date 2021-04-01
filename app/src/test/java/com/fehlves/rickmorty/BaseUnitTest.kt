package com.fehlves.rickmorty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseUnitTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

}

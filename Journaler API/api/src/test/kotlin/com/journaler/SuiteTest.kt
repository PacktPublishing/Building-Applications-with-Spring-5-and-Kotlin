package com.journaler

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(NoteTest::class, TodoTest::class)
class SuiteTest
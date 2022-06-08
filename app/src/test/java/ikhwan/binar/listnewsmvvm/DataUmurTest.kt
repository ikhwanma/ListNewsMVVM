package ikhwan.binar.listnewsmvvm

import org.junit.Before
import org.junit.Test

class DataUmurTest {

    private lateinit var dataUmur: DataUmur

    @Before
    fun setUp(){
        dataUmur = DataUmur()
    }

    @Test
    fun getDataUmur() {
        dataUmur.getDataUmur(2000, 2022)
    }
}
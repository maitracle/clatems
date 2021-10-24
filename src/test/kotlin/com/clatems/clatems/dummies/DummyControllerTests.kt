package com.clatems.clatems.dummies

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class DummyControllerTests(@Autowired private var mockMvc: MockMvc) {

    @Test
    fun `should get Hello string`() {
        val actions = mockMvc.perform(get("/dummies/hello"))

        actions.andExpect(status().isOk)
            .andExpect(content().string("Hello"))
    }

    @Test
    fun `should get dummy list`() {
        val actions = mockMvc.perform(get("/dummies"))

        actions.andExpect(status().isOk)
            .andExpect(content().json("[]"))
    }
}

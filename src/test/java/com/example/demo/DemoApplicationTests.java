package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void checkAccountBuildsDebtListFromDaysAndAccountFieldsFromAcc() throws Exception {
		mockMvc.perform(get("/v2/checkAccount")
						.param("acc", "1234567891")
						.param("days", "2"))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.account").value("1234567891"))
				.andExpect(jsonPath("$.vip-client").value(true))
				.andExpect(jsonPath("$.inn").value("1234567891111"))
				.andExpect(jsonPath("$.debt", hasSize(2)));
	}

	@Test
	void confirmPaymentReturnsTransactionAndProcessingHeader() throws Exception {
		mockMvc.perform(post("/v2/payment/")
						.header("BankCode", "22")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"transaction_id":"T-342-67777","sum":133.12,"need_processing":true}
								"""))
				.andExpect(status().isOk())
				.andExpect(header().exists("ProcessingTIme"))
				.andExpect(jsonPath("$.transaction_id").value("T-342-67777"))
				.andExpect(jsonPath("$.bank_bik").value("2345678997"))
				.andExpect(jsonPath("$.contact[0].telecom", hasSize(4)));
	}

	@Test
	void clearTransactionReturnsRequiredStatusAndBody() throws Exception {
		mockMvc.perform(delete("/v1/transactions/cleare/123"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("deleted success"));
	}
}

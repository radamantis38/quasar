package com.felipe.quasar.fire.apirest.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class DecodeMessageTest {

	@Test
	public void testPrintMessage() {
		String[] message1 = { "", "", "es", "un", "" };
		String[] message2 = { "este", "", "un", "" };
		String[] message3 = { "", "", "es", "", "mensaje" };
		DecodeMessage decodeMessage = new DecodeMessage(500, 200, 600, message1, message2, message3);
		decodeMessage.getLocation();
		assertEquals(-25, decodeMessage.getX(), 0.5);
		assertEquals(-150, decodeMessage.getY(), 0.5);
		assertTrue(decodeMessage.getMessage().equals("este es un mensaje"));
	}

	@Test
	public void testPrintMessage2() {
		String[] message1 = { "este", "", "", "mensaje", "" };
		String[] message2 = { "", "es", "", "", "secreto" };
		String[] message3 = { "este", "", "un", "", "" };
		DecodeMessage decodeMessage = new DecodeMessage(500, 200, 600, message1, message2, message3);
		decodeMessage.getLocation();
		assertEquals(-25, decodeMessage.getX(), 0.5);
		assertEquals(-150, decodeMessage.getY(), 0.5);
		assertTrue(decodeMessage.getMessage().equals("este es un mensaje secreto"));
	}
	
	@Test
	public void testPrintMessage3() {
		String[] message1 = { "", "este" };
		String[] message2 = { "", "es" };
		String[] message3 = { "", "" };
		DecodeMessage decodeMessage = new DecodeMessage(500, 200, 600, message1, message2, message3);
		decodeMessage.getLocation();
		assertEquals(-25, decodeMessage.getX(), 0.5);
		assertEquals(-150, decodeMessage.getY(), 0.5);
		assertTrue(decodeMessage.getMessage().equals(""));
	}
}

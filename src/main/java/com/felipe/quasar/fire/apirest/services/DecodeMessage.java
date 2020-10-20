package com.felipe.quasar.fire.apirest.services;

import java.util.Arrays;

/**
 * Class whit methods for find the Quasar position and decode his message
 * 
 * @author Felipe Parra
 *
 */
public class DecodeMessage {

	private double distance1, distance2, distance3;
	private String[] message1, message2, message3;

	private double x1 = -500, x2 = 100, x3 = 500, x;
	private double y1 = -200, y2 = -100, y3 = 100, y;

	/**
	 * Constructor
	 * 
	 * @param distance1 distance of Kenobi
	 * @param distance2 distance of Skywalker
	 * @param distance3 distance of Sato
	 * @param message1  message part of Kenobi
	 * @param message2  message part of Skywalker
	 * @param message3  message part of Sato
	 */
	public DecodeMessage(double distance1, double distance2, double distance3, String[] message1, String[] message2,
			String[] message3) {
		this.distance1 = distance1;
		this.distance2 = distance2;
		this.distance3 = distance3;
		this.message1 = message1;
		this.message2 = message2;
		this.message3 = message3;
	}

	/**
	 * Funcion that find the Quasar Location
	 */
	public void getLocation() {
		x = (((Math.pow(distance1, 2) - Math.pow(distance2, 2)) + (Math.pow(x2, 2) - Math.pow(x1, 2))
				+ (Math.pow(y2, 2) - Math.pow(y1, 2))) * ((2 * y3) - (2 * y2))
				- ((Math.pow(distance2, 2) - Math.pow(distance3, 2)) + (Math.pow(x3, 2) - Math.pow(x2, 2))
						+ (Math.pow(y3, 2) - Math.pow(y2, 2))) * ((2 * y2) - (2 * y1)))
				/ (((2 * x2) - (2 * x3)) * ((2 * y2) - (2 * y1)) - ((2 * x1) - (2 * x2)) * ((2 * y3) - (2 * y2)));

		y = ((Math.pow(distance1, 2) - Math.pow(distance2, 2)) + (Math.pow(x2, 2) - Math.pow(x1, 2))
				+ (Math.pow(y2, 2) - Math.pow(y1, 2)) + x * ((2 * x1) - (2 * x2))) / ((2 * y2) - (2 * y1));
	}

	/**
	 * Function for decode Quasar message
	 * 
	 * @return decoded message
	 */
	public String getMessage() {
		String messagePart;
		String message = "";
		int[] lenghts = { message1.length, message2.length, message3.length };
		int minimun;

		int[] lenghtsSort = lenghts.clone();
		Arrays.sort(lenghtsSort);
		minimun = lenghtsSort[0];

		int[] gaps = { lenghts[0] - minimun, lenghts[1] - minimun, lenghts[2] - minimun };

		for (int i = 0; i < minimun; ++i) {

			messagePart = message1[i + gaps[0]];
			messagePart = validateMessagePart(messagePart, message2[i + gaps[1]]);
			messagePart = validateMessagePart(messagePart, message3[i + gaps[2]]);

			if (!messagePart.equals("")) {
				message += messagePart + " ";
			}

		}

		if (message.length() > 0) {
			return message.substring(0, message.length() - 1);
		} else {
			return message;
		}
	}

	/**
	 * @param messagePart       message part decoded
	 * @param messagePartEncode message part from satellite
	 * @return mesagePart message part decoded
	 */
	private String validateMessagePart(String messagePart, String messagePartEncode) {
		if (!messagePartEncode.equals("") && messagePart.equals("")) {
			return messagePartEncode;
		} else if (!messagePartEncode.equals("") && !messagePart.equals("") && !messagePart.equals(messagePartEncode)) {
			return "";
		}
		return messagePart;
	}

	/**
	 * Return position x of Quasar
	 */
	public double getX() {
		return x;
	}

	/**
	 * Return position y of Quasar
	 */
	public double getY() {
		return y;
	}

}

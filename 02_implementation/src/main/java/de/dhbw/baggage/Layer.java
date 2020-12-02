package de.dhbw.baggage;

public class Layer {

	private HandBaggage handBaggage;
	private char[] content;

	public Layer() {
		this.content = new char[10000];
	}
	
	public String getContent() {
		return new String(this.content);
	}
}

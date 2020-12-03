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

	public void setContent(String content) {
		char[] cs = content.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			this.content[i] = cs[i];
		}
	}
}

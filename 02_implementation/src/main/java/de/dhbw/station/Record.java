package de.dhbw.station;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.dhbw.station.result.ScanResult;

public class Record {
	
	private static int idCounter = 0;

	private int id;
	private String timestamp;
	private ScanResult result;

	public Record(ScanResult result) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy hh:mm:ss,SSS");

		// assign current id and then increase by one
		this.id = idCounter++;
		this.timestamp = LocalDateTime.now().format(formatter);
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public ScanResult getResult() {
		return result;
	}
}

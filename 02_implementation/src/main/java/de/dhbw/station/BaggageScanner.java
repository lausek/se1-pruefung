package de.dhbw.station;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.police.FederalPoliceOfficer;

public class BaggageScanner {

	private Tray tray;
	private RollerConveyor rollerConveyor;
	private Belt belt;
	private Scanner scanner;
	private OperatingStation operatingStation;
	private ManualPostControl manualPostControl;
	private Supervision supervision;
	private FederalPoliceOfficer federalPoliceOfficer;
	private Status status;

	public BaggageScanner() {
		this.tray = new Tray();
		this.rollerConveyor = new RollerConveyor();
		this.belt = new Belt();
		this.scanner = new Scanner();
		this.operatingStation = new OperatingStation();
		this.manualPostControl = new ManualPostControl();
		this.supervision = new Supervision();
	}

	public void moveBeltForward() {}
	public void moveBeltBackwards() {}
	public void scan(HandBaggage baggae) {}
	public void alarm() {}
	public void report() {}
	public void maintenance() {}
	public void start() {}
	public void shutdown() {}
}

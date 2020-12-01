package de.dhbw.station;

import de.dhbw.baggage.HandBaggage;
import de.dhbw.employee.Employee;
import de.dhbw.employee.Supervisor;
import de.dhbw.police.FederalPoliceOfficer;
import de.dhbw.station.result.Clean;
import de.dhbw.station.result.ScanResult;

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
	private boolean alarmActive;
	private boolean locked;

	public BaggageScanner() {
		this.tray = new Tray();
		this.rollerConveyor = new RollerConveyor();
		this.belt = new Belt();
		this.scanner = new Scanner();
		this.operatingStation = new OperatingStation();
		this.manualPostControl = new ManualPostControl();
		this.supervision = new Supervision();
		
		this.alarmActive = false;
		this.locked = true;
	}

	public void moveBeltForward() {}

	public void moveBeltBackwards() {}

	public ScanResult scan(HandBaggage baggage) {
		return new Clean();
	}

	public void alarm() {}

	public void report() {}

	public void maintenance() {}

	public void start() {}

	public void shutdown() {}
	
	public boolean unlock(Employee employee) {
		return false;
	}

	public RollerConveyor getRollerConveyor() {
		return rollerConveyor;
	}

	public OperatingStation getOperatingStation() {
		return operatingStation;
	}

	public Supervision getSupervision() {
		return supervision;
	}

	public FederalPoliceOfficer getFederalPoliceOfficer() {
		return federalPoliceOfficer;
	}

	public ManualPostControl getManualPostControl() {
		return manualPostControl;
	}

	public Belt getBelt() {
		return belt;
	}

	public Status getStatus() {
		return status;
	}

	public boolean isAlarmActive() {
		return alarmActive;
	}

	public boolean isLocked() {
		return locked;
	}
}

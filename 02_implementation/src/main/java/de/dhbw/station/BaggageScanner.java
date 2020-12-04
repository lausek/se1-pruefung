package de.dhbw.station;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.dhbw.Configuration;
import de.dhbw.baggage.HandBaggage;
import de.dhbw.baggage.Layer;
import de.dhbw.baggage.ProhibitedItem;
import de.dhbw.employee.Employee;
import de.dhbw.employee.HouseKeeper;
import de.dhbw.employee.Inspector;
import de.dhbw.employee.Supervisor;
import de.dhbw.employee.Technician;
import de.dhbw.police.FederalPoliceOfficer;
import de.dhbw.search.BoyerMoore;
import de.dhbw.search.IStringMatching;
import de.dhbw.search.KnuthMorrisPratt;
import de.dhbw.station.result.Clean;
import de.dhbw.station.result.ScanResult;

public class BaggageScanner {

	public final int DEFAULT_TRAY_AMOUNT = 30;

	private Stack<Tray> trays;
	private RollerConveyor rollerConveyor;
	private Belt belt;
	private Scanner scanner;
	private ExplosiveTraceDetector explosiveTraceDetector;
	private OperatingStation operatingStation;
	private ManualPostControl manualPostControl;
	private Supervision supervision;
	private FederalPoliceOfficer federalPoliceOfficer;
	private Status status;
	private boolean alarmActive;
	private List<Record> scanLog;
	private Belt[] postBelts;

	private Technician technician;
	private HouseKeeper houseKeeper;

	public BaggageScanner() {
		this.trays = new Stack<>();
		for (int i = 0; i < DEFAULT_TRAY_AMOUNT; i++) {
			this.addTray(new Tray());
		}

		this.rollerConveyor = new RollerConveyor(this);
		this.belt = new Belt();
		this.explosiveTraceDetector = new ExplosiveTraceDetector();
		this.scanner = new Scanner(this);
		this.operatingStation = new OperatingStation(this);
		this.manualPostControl = new ManualPostControl(this);
		this.supervision = new Supervision(this);

		this.alarmActive = false;
		this.status = Status.SHUTDOWN;
		this.scanLog = new ArrayList<>(1500);
		this.postBelts = new Belt[] { new Belt(), new Belt() };
	}

	private void authenticationCheck(Employee employee, Class<?>... classes) throws UnauthorizedException {
		for (Class<?> cls : classes) {
			if (cls.isInstance(employee)) {
				return;
			}
		}
		throw new UnauthorizedException();
	}

	public void addTray(Tray tray) {
		this.trays.add(tray);
	}

	public Tray takeTray() {
		return this.trays.pop();
	}

	public void moveBeltForward(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Inspector.class);
	}

	public void moveBeltBackwards(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Inspector.class);
	}

	public ScanResult scan(Employee employee, HandBaggage handBaggage) throws UnauthorizedException {
		this.authenticationCheck(employee, Inspector.class);
		assert (this.status == Status.ACTIVE);
		this.status = Status.IN_USE;

		ScanResult scanResult = new Clean();
		@SuppressWarnings("unused")
		IStringMatching searcher = Configuration.SEARCH_ALGORITHM == "BoyerMoore" ? new BoyerMoore()
				: new KnuthMorrisPratt();

		for (Layer layer : handBaggage.getLayers()) {
			for (ProhibitedItem prohibitedItem : Configuration.PROHIBITED_ITEMS) {
				int position = searcher.search(layer.getContent(), prohibitedItem.getPattern());
				if (0 <= position) {
					scanResult = prohibitedItem.createResult(position);
					break;
				}
			}
		}

		Record record = new Record(scanResult);

		scanLog.add(record);

		this.status = Status.ACTIVE;

		return scanResult;
	}

	public void alarm(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Inspector.class);
		this.alarmActive = true;
		this.status = Status.LOCKED;
	}

	public void report(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Supervisor.class);
	}

	public void maintenance(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Technician.class);
	}

	public void start(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Supervisor.class);
		this.status = Status.ACTIVE;
	}

	public void shutdown(Employee employee) throws UnauthorizedException {
		this.alarmActive = false;
		this.status = Status.SHUTDOWN;
	}

	public void unlock(Employee employee) throws UnauthorizedException {
		this.authenticationCheck(employee, Supervisor.class);
		this.alarmActive = false;
		this.status = Status.ACTIVE;
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

	public void setFederalPoliceOfficer(FederalPoliceOfficer federalPoliceOfficer) {
		this.federalPoliceOfficer = federalPoliceOfficer;
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
		return Status.LOCKED == this.status;
	}

	public Belt getTrack1() {
		return this.postBelts[0];
	}

	public Belt getTrack2() {
		return this.postBelts[1];
	}

	public ExplosiveTraceDetector getExplosiveTraceDetector() {
		return explosiveTraceDetector;
	}

	public Technician getTechnician() {
		return this.technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public HouseKeeper getHouseKeeper() {
		return this.houseKeeper;
	}

	public void setHouseKeeper(HouseKeeper houseKeeper) {
		this.houseKeeper = houseKeeper;
	}
	
	public List<Record> getScanLog() {
		return this.scanLog;
	}

	public Scanner getScanner() {
		return scanner;
	}
}

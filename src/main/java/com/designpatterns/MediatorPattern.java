package com.designpatterns;

/**
 * • A set of objects communicate in well-defined but complex ways. The resulting interdependencies are unstructured and
 * difficult to understand.
 * 
 * • Reusing an object is difficult because it refers to and communicates with many other objects.
 * 
 * • A behavior that’s distributed between several classes should be customizable without a lot of sub-classing.
 *
 */
public class MediatorPattern {

	public interface MachineMediator {
		public void start();

		public void wash();

		public void open();

		public void closed();

		public void on();

		public void off();

		public boolean checkTemperature(int temp);
	}

	public interface Colleague {
		public void setMediator(MachineMediator mediator);
	}

	public class Button implements Colleague {
		private MachineMediator mediator;

		@Override
		public void setMediator(MachineMediator mediator) {
			this.mediator = mediator;
		}

		public void press() {
			System.out.println("Button pressed.");
			mediator.start();
		}
	}

	public class Machine implements Colleague {
		private MachineMediator mediator;

		@Override
		public void setMediator(MachineMediator mediator) {
			this.mediator = mediator;
		}

		public void start() {
			mediator.open();
		}

		public void wash() {
			mediator.wash();
		}
	}

	public class Valve implements Colleague {
		private MachineMediator mediator;

		@Override
		public void setMediator(MachineMediator mediator) {
			this.mediator = mediator;
		}

		public void open() {
			System.out.println("Valve is opened...");

			System.out.println("Filling water...");
			mediator.closed();
		}

		public void closed() {
			System.out.println("Valve is closed...");
			mediator.on();
		}
	}

	public class Heater implements Colleague {
		private MachineMediator mediator;

		@Override
		public void setMediator(MachineMediator mediator) {
			this.mediator = mediator;
		}

		public void on(int temp) {
			System.out.println("Heater is on...");
			if (mediator.checkTemperature(temp)) {
				System.out.println("Temperature is set to " + temp);
				mediator.off();
			}
		}

		public void off() {
			System.out.println("Heater is off...");
			mediator.wash();
		}
	}

	public class CottonMediator implements MachineMediator {
		private final Machine machine;
		private final Heater heater;
		private final Motor motor;
		private final Sensor sensor;
		private final SoilRemoval soilRemoval;
		private final Valve valve;

		public CottonMediator(Machine machine, Heater heater, Motor motor, Sensor sensor, SoilRemoval soilRemoval,
				Valve valve) {
			this.machine = machine;
			this.heater = heater;
			this.motor = motor;
			this.sensor = sensor;
			this.soilRemoval = soilRemoval;
			this.valve = valve;
			System.out.println("Setting up for COTTON program");
		}

		@Override
		public void start() {
			machine.start();
		}

		@Override
		public void wash() {
			motor.startMotor();
			motor.rotateDrum(700);
			System.out.println("Adding detergent");
			soilRemoval.low();
			System.out.println("Adding softener");
		}

		@Override
		public void open() {
			valve.open();
		}

		@Override
		public void closed() {
			valve.closed();
		}

		@Override
		public void on() {
			heater.on(40);
		}

		@Override
		public void off() {
			heater.off();
		}

		@Override
		public boolean checkTemperature(int temp) {
			return sensor.checkTemperature(temp);
		}
	}

	public class Sensor {
		public boolean checkTemperature(int temp) {
			System.out.println("Temperature reached " + temp + " *C");
			return true;
		}
	}

	public class SoilRemoval {
		public void low() {
			System.out.println("Setting Soil Removal to low");
		}

		public void medium() {
			System.out.println("Setting Soil Removal to medium");
		}

		public void high() {
			System.out.println("Setting Soil Removal to high");
		}
	}

	public class DenimMediator implements MachineMediator {
		private final Machine machine;
		private final Heater heater;
		private final Motor motor;
		private final Sensor sensor;
		private final SoilRemoval soilRemoval;
		private final Valve valve;

		public DenimMediator(Machine machine, Heater heater, Motor motor, Sensor sensor, SoilRemoval soilRemoval,
				Valve valve) {
			this.machine = machine;
			this.heater = heater;
			this.motor = motor;
			this.sensor = sensor;
			this.soilRemoval = soilRemoval;
			this.valve = valve;
			System.out.println("Setting up for DENIM program");
		}

		@Override
		public void start() {
			machine.start();
		}

		@Override
		public void wash() {
			motor.startMotor();
			motor.rotateDrum(1400);
			System.out.println("Adding detergent");
			soilRemoval.medium();
			System.out.println("Adding softener");
		}

		@Override
		public void open() {
			valve.open();
		}

		@Override
		public void closed() {
			valve.closed();
		}

		@Override
		public void on() {
			heater.on(30);
		}

		@Override
		public void off() {
			heater.off();
		}

		@Override
		public boolean checkTemperature(int temp) {
			return sensor.checkTemperature(temp);
		}
	}

	public class Motor {

		public void startMotor() {
			System.out.println("Start motor...");
		}

		public void rotateDrum(int rpm) {
			System.out.println("Rotating drum at " + rpm + " rpm.");
		}
	}

}

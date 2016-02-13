package util;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

//immainPort edu.wpi.first.wpilibj.Jaguar;

public class Sender {
	SerialPort mainPort;

	public Sender() {
		mainPort = initializePort();
	}

	public SerialPort initializePort() {
		String[] ports = SerialPortList.getPortNames();
		if (ports.length > 0) {
			return new SerialPort(ports[0]);
		} else {
			System.out.println("No ports detected.");
			System.exit(1);
			return null;
		}
	}

	public void sendData(byte[] bytebuffer) {
		try {
			mainPort.openPort();
			mainPort.setParams(9600, 8, 1, 0);
			mainPort.writeBytes(bytebuffer);
			mainPort.closePort();
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	public void sendDoubles(double[] doubles) {
		sendData(Converter.doublesToBytes(doubles));
	}
}
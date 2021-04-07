package service;

public class BancaServiceFactory {
	public static BancaService getBancaService() {
		return new BancaServiceImpl();
	}
}

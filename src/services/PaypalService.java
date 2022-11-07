package services;

public class PaypalService implements OnlinePaymentService{

	@Override
	public double paymentfee(double amount) {//taxa de 2%
		return amount * 0.02;
	}

	@Override
	public double interest(double amount, int months) {//juros de 1% a cada mês
		return amount * 0.01 * months;
	}

}

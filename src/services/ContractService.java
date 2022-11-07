package services;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		//valor / meses
		double basicQuota = contract.getTotalValue() / months;
		
		for (int i=1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);//vai add os meses
			
			double interest = onlinePaymentService.interest(basicQuota, i);//juro
			double fee = onlinePaymentService.paymentfee(basicQuota + interest);//taxa
			double quota = basicQuota + interest + fee;//valor de cada mes somando (juro + taxa) 
			
			contract.getInstalments().add(new Installment(dueDate, quota));//instanciando e colocando na lista
		}
	}
	
}

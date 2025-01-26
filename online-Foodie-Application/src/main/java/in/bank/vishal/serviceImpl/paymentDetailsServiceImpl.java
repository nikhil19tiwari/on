package in.bank.vishal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bank.vishal.model.paymentDetails;
import in.bank.vishal.repository.PaymentDetailsRepo;
import in.bank.vishal.service.paymentDetailsService;


@Service
public class paymentDetailsServiceImpl implements paymentDetailsService {

	@Autowired
      private PaymentDetailsRepo  pDetails; 
	@Override
	public void save(paymentDetails details) {
pDetails.save(details);


	}

	@Override
	public List<paymentDetails> getPayment() {

		return pDetails.findAll();
	}

}

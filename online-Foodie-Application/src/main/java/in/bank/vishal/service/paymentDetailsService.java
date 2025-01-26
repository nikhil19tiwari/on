package in.bank.vishal.service;

import java.util.List;

import in.bank.vishal.model.paymentDetails;

public interface paymentDetailsService {
public void save(paymentDetails details);
public List<paymentDetails> getPayment();
}

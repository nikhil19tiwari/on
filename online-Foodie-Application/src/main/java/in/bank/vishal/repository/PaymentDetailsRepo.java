package in.bank.vishal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bank.vishal.model.paymentDetails;

@Repository
public interface PaymentDetailsRepo extends JpaRepository<paymentDetails, Integer> {

}

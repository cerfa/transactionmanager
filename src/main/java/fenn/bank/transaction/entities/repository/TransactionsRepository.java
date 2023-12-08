package fenn.bank.transaction.entities.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fenn.bank.transaction.entities.Transactions;


 
@Repository
public interface TransactionsRepository extends CrudRepository<Transactions, Integer>{
	List<Transactions> findAllByCustomerId(String customerId);
}
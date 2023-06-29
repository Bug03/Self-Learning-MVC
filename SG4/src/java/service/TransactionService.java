package service;

import java.util.List;

import model.Transactions;

public interface TransactionService {
	void insert(Transactions transaction);
	 
	void edit(Transactions transaction); 

	void delete(String id); 
 
	Transactions get(int id); 
	 
	List<Transactions> get(String username); 
 
	List<Transactions> getAll(); 

        int getTotalTransactions();
        
        List<Transactions> paging(int index);
}

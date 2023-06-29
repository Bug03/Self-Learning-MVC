package dao;
import java.util.List;

import model.Transactions;


public interface TransactionDao {
	void insert(Transactions transaction); 
	 
	void edit(Transactions admin); 
	
	void delete(String id); 
 
	Transactions get(int id); 
	 
	List<Transactions> get(String username); 
 
	List<Transactions> getAll(); 
	
	int getTotalTransactions();
        
        List<Transactions> paging(int index);
}

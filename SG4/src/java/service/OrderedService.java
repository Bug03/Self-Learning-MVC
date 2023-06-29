package service;

import java.util.List;

import model.Ordered;

public interface OrderedService {
	void insert(Ordered ordered); 
	 
	void edit(Ordered ordered); 

	void delete(String id); 
 
	Ordered get(int id); 
	 
	Ordered get(String name); 
 
	List<Ordered> getAll(); 
 
        int getTotalOrdered(String departmentId);
        
        List<Ordered> paging(int index, String departmentId);
}

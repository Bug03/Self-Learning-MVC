package dao;

import java.util.List;

import model.Catalog;

public interface CategoryDao {
	void insert(Catalog category);

	void edit(Catalog category);

	void delete(String id);

	Catalog get(int id);
	
	Catalog get(String name);

	List<Catalog> getAll();
	
	List<Catalog> getCateByProduct(int id);
        
        int getToltal();
        
        List<Catalog> paging(int index);

}
